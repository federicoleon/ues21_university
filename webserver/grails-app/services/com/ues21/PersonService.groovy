package com.ues21

import com.ues21.enums.*
import com.ues21.utils.*
import com.ues21.exceptions.UserLockedException

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
    def springSecurityService
    def passwordEncoder

    public boolean isValidDataForCreation(JSONObject data) {
        try {
            if(!data) { return false }

            if(!data.role) { return false }

            if(!data.file_number) { return false }
            if(!data.username) { return false }
            if(!data.password) { return false }

            JSONObject personData = data.person_data

            if(!personData.first_name) { return false }
            if(!personData.last_name) { return false }

            JSONObject identification = personData.identification
            if(!identification.type) { return false }
            if(!identification.number) { return false }

            JSONArray emails = personData.emails
            emails.each { email ->
                if(!email.address || !email.status) { return false}
            }

            JSONArray phones = personData.phones
            phones.each { phone ->
                if(!phone.type || !phone.areaCode || !phone.number) { return false}
            }
        } catch(Exception e) {
            return false
        }
    }
    
    public Person createFromGeneric(JSONObject data) {
        if(!isValidDataForCreation(data)) {
            return null
        }
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
            email.status = it.status
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
                phone.company = it.company
                phone.person = p
                p.addToPhones(phone)
            }
        }

        p.fileNumber = data.file_number

        if(!p.validate()) {
            return null
        }
        p.save(flush: true, failOnError: true)

        User user = new User()
        user.person = p
        user.username = data.username
        user.password = data.password
        user.enabled = true
        user.accountExpired = false
        user.accountLocked = false
        user.passwordExpired = false

        user.save(flush: true, failOnError: true)

        UserPassword pass = new UserPassword()
        pass.passwordHash = user.password
        pass.failedLoginAttempts = 0
        pass.status = UserPassword.STATUS_ACTIVE
        pass.person = p
        p.addToPasswords(pass)

        p.save(flush: true, failOnError: true)

        def role = new UserRole(user: user, role: Role.findWhere(authority: data.role)).save(flush: true, failOnError: true)

        return p
    }

    private boolean isValidPassword(User user, String password) {
        if(!user || !password) {
            return false
        }
        return passwordEncoder.isPasswordValid(user.password, password, null)
    }

    public User validateLogin(String username, String password) {
        User user = User.findByUsername(username)
        if(!user) {
            return null
        }
        Person p = user.person
        if(!p) { return null }

        boolean isValidPassword = isValidPassword(user, password)

        boolean hasActivePassword = false
        
        UserPassword pass
        p.passwords.each {
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
            
            // Store loggued person in Session:
            WebUtils.retrieveGrailsWebRequest().session?.person = p
            return user
        }

        pass.failedLoginAttempts ++
        if(pass.failedLoginAttempts >= grailsApplication.config.university.login.maxAttempts) {

            lockUser(user, pass)

            List emails = []
            p.emails.each {
                if(it.status == 1) {
                    emails << it.address
                }
            }
            if(emails.size() > 0) {
                def content = groovyPageRenderer.render(
                    template: "/mails/userLocked",
                    model: [
                        firstName: p.firstName,
                        recoverURL: "22.edu.ar:8080/account_recovery/${pass.id}"
                    ]
                )
                /*
                mailService.sendMail {
                    to emails.toArray()
                    subject "UES22 - Cuenta bloqueada!"
                    html(content)
                }
                */
            }
            return user
        }

        return null
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

    private String getPasswordHash(String password) {
        return springSecurityService?.passwordEncoder ? springSecurityService.encodePassword(password) : password
    }
}
