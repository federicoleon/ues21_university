package com.ues21

import com.ues21.enums.*
import com.ues21.utils.*

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray
import org.codehaus.groovy.grails.web.util.WebUtils
import grails.gsp.PageRenderer

import grails.transaction.Transactional

@Transactional
class PersonService {

    def grailsApplication
    def groovyPageRenderer
    def mailService
    
    public Person createFromGeneric(JSONObject data) {
        Person p

        switch(data.role) {
            case UserRoleEnum.TEACHER.role():
                p = new Teacher()
                break
            case UserRoleEnum.STUDENT.role():
                p = new Student()
                break
            case UserRoleEnum.DIRECTOR.role():
                p = new Director()
                break
            case UserRoleEnum.SECRETARY.role():
                p = new Secretary()
                break
            default:
                throw new Exception("Error mocking new user: Unrecognized user role '${data.role}'")
                break
        }
        p.firstName = data.person_data.first_name
        p.lastName = data.person_data.last_name

        Identification identification = new Identification()
        identification.type = IdentificationTypeEnum.byType(data.person_data.identification.type)?.id()
        identification.number = data.person_data.identification.number
        identification.person = p

        p.identification = identification

        data.person_data.emails?.each {
            Email email = new Email()
            email.address = it.address
            email.person = p
            p.addToEmails(email)
        }

        data.person_data.phones?.each {
            PhoneTypeEnum phoneType = PhoneTypeEnum.byType(it.type)
            if(phoneType) {
                Phone phone = new Phone()
                phone.type = phoneType.id()
                phone.areaCode = it.area_code
                phone.number = it.number
                phone.company = it.company ?: ""
                phone.person = p
                p.addToPhones(phone)
            }
        }

        p.fileNumber = data.file_number

        try {
            if(!p.save(flush: true, failOnError: true)) {
                return p
            }
        } catch(Exception e) {
            println e
            return p
        }

        User user = new User()
        user.person = p
        user.username = data.username
        user.password = getPasswordHash(data.password)
        user.enabled = true
        user.accountExpired = false
        user.accountLocked = false
        user.passwordExpired = false

        Role role = Role.findByAuthority(data.role)
        user.addToRoles(role)

        UserPassword pass = new UserPassword()
        pass.passwordHash = user.password
        pass.failedLoginAttempts = 0
        pass.status = UserPassword.STATUS_ACTIVE
        pass.user = user
        user.addToPasswords(pass)

        user.save(flush:true, failOnError: true)
        
        return p
    }

    public User validateLogin(String username, String password) {
        User user = User.findByUsername(username)
        if(!user) {
            return null
        }

        boolean isValidPassword = isValidPassword(user, password)

        boolean hasActivePassword = false
        
        UserPassword pass
        user.passwords.each {
            if(it.status == UserPassword.STATUS_ACTIVE) {
                pass = it
                hasActivePassword = true
            }
        }

        // El usuario no tiene ninuna contraseña activa. Se encuentra bloqueado.
        if(!hasActivePassword) {
            return null
        }

        if(isValidPassword) {
            pass.failedLoginAttempts = 0
            pass.save(flush: true)
            // Guardamos la persona logueada en la sesión actual.
            WebUtils.retrieveGrailsWebRequest().session?.user = user
            return user
        }

        pass.failedLoginAttempts ++
        if(pass.failedLoginAttempts >= grailsApplication.config.university.login.maxAttempts) {

            lockUser(user, pass)

            PasswordResetToken token = PasswordResetToken.findByUser(user)
            if(!token) {
                token = new PasswordResetToken()
                token.user = user
            }
            token.token = StringUtils.getMD5(DateUtils.getCurrentDateMV().concat(user.username)).toString()

            try {
                token.save(flush: true, failOnError: true)
            } catch(Exception e) {
                println e
                return null
            }

            List emails = getEmails(user.person)
            if(emails.size() == 0) {
                return null
            }

            def content = groovyPageRenderer.render(
                template: "/mails/userLocked",
                model: [
                    firstName: user.person.firstName,
                    recoverURL: "http://22.edu.ar:8080/account_recovery/${token.token}".toString()
                ]
            )
            mailService.sendMail {
                to emails.toArray()
                subject "UES22 - Cuenta bloqueada!"
                html(content)
            }
            return user
        }

        return null
    }

    public boolean isValidPassword(User user, String password) {
        if(!user || !password) {
            return false
        }
        return StringUtils.getMD5(password).equals(user.password)
    }

    public User changePassword(User user, String password, String newPassword, String confirmPassword, boolean fromToken) {
        if(!user) {
            return null
        }

        user.clearErrors()

        if(!password || !newPassword || !confirmPassword) {
            user.errors.rejectValue("password", "change.password.missing_information")
            return user
        }

        if(!password) {
            user.errors.rejectValue("password", "change.password.currentPassword.empty")
            return user
        }

        if(!fromToken && !isValidPassword(user, password)) {
            user.errors.rejectValue("password", "change.password.currentPassword.wrong")
            return user
        }

        if(!newPassword) {
            user.errors.rejectValue("password", "change.password.newPassword.empty")
            return user
        }

        if(!confirmPassword) {
            user.errors.rejectValue("password", "change.password.confirmPassword.empty")
            return user
        }

        if(newPassword != confirmPassword) {
            user.errors.rejectValue("password", "change.password.newPasswordsDontMatch")
            return user
        }

        String newPasswordHash = getPasswordHash(newPassword)

        List oldPasswords = UserPassword.withCriteria {
            eq("user", user)
        }

        boolean passwordUsed = false
        oldPasswords.each { old ->
            if(old.passwordHash == newPasswordHash) {
                passwordUsed = true
            }
        }
        if(passwordUsed) {
            user.errors.rejectValue("password", "change.password.newPasswordsUsedBefore")
            return user
        }

        user = User.get(user.id)
        if(!user) {
            return null
        }

        user.password = newPasswordHash
        if(!user.validate()) {
            // Si hay errores, no hay necesidad de seguir procesando.
            return user
        }

        // Desbloqueo el usuario.
        user.accountLocked = false

        user.save(flush: true, failOnError: true)

        oldPasswords.each { old ->
            if(old.status in [UserPassword.STATUS_ACTIVE, UserPassword.STATUS_LOCKED]) {
                old.status = UserPassword.STATUS_INACTIVE
                old.save(flush: true, failOnError: true)
            }
        }

        UserPassword newPass = new UserPassword()
        newPass.user = user
        newPass.passwordHash = user.password
        newPass.save(flush: true, failOnError: true)

        return user
    }

    private List getEmails(Person person) {
        List emails = []
        if(!person) { return emails }
        person.emails.each {
            if(it.status == Email.STATUS_ACTIVE) {
                emails << it.address
            }
        }
        return emails
    }

    private void lockUser(User user, UserPassword userPassword) {
        if(!user || !userPassword) {
            return
        }

        User.withTransaction {
            user.accountLocked = true
            user.save(flush: true)
        }

        UserPassword.withTransaction {
            userPassword.status = UserPassword.STATUS_LOCKED
            userPassword.save(flush: true)
        }
    }

    public boolean unlockAccount(String tokenId) {
        return false
    }

    private String getPasswordHash(String password) {
        return StringUtils.getMD5(password)
    }

    public void sendRetrievePasswordEmail(String username) {
        User user = User.findByUsername(username)
        if(!user) { return }

        PasswordResetToken token = PasswordResetToken.findByUser(user)
        if(!token) {
            token = new PasswordResetToken()
            token.user = user
        }

        token.token = StringUtils.getMD5(DateUtils.getCurrentDateMV().concat(user.username)).toString()

        try {
            token.save(flush: true, failOnError: true)
        } catch(Exception e) {
            return
        }

        List emails = getEmails(user.person)
        if(emails.size() == 0) {
            return
        }

        def email = groovyPageRenderer.render(
            template: "/mails/forgotPassword",
            model: [
                firstName: user.person.firstName,
                recoverURL: "http://22.edu.ar:8080/account_recovery/${token.token}".toString()
            ]
        )

        mailService.sendMail {
            to emails.toArray()
            subject "UES22 - Recuperar contraseña"
            html(email)
        }
    }

    public boolean validateRetrievePasswordToken(String tokenId) {
        if(!tokenId) { return false }
        PasswordResetToken token = PasswordResetToken.findByToken(tokenId)
        if(!token) {
            return false
        }
        return !token.isExpired()
    }

    public User processPasswordChangeByToken(String tokenId, String newPassword, String confirmPassword) {
        if(!tokenId || !newPassword || !confirmPassword) { return null }
        
        PasswordResetToken token = PasswordResetToken.findByToken(tokenId)
        if(!token || token.isExpired() || !token.user) { return null }

        User user = changePassword(token.user, token.user.password, newPassword, confirmPassword, true)
        if(!user?.hasErrors()) {
            token.delete(flush: true)
        }
        return user
    }
}
