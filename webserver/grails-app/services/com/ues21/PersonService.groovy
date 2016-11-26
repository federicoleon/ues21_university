package com.ues21

import com.ues21.enums.*
import com.ues21.utils.*
import com.ues21.exceptions.UserLockedException

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray
import grails.gsp.PageRenderer

import grails.transaction.Transactional

@Transactional
class PersonService {

    def grailsApplication
    def groovyPageRenderer
    def mailService

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
            case UserTypeEnum.TEACHER.role():
                p = new Teacher()
                break
            case UserTypeEnum.STUDENT.role():
                p = new Student()
                break
            case UserTypeEnum.DIRECTOR.role():
                p = new Director()
                break
            case UserTypeEnum.SECRETARY.role():
                p = new Secretary()
                break
            default:
                throw new Exception("Error mocking new user: Unrecognized user role ${user.role}")
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
        p.username = data.username

        UserPassword pass = new UserPassword()
        pass.passwordHash = StringUtils.getMD5(data.password)
        pass.failedLoginAttempts = 0
        pass.status = 1
        pass.person = p
        p.addToPasswords(pass)

        if(!p.validate()) {
            return null
        }
        p.save(flush: true, failOnError: true)
        return p
    }

    public Person getByUsername(String username) {
        Person result
        
        result = Student.findByUsername(username)
        if(result) { return result }

        result = Teacher.findByUsername(username)
        if(result) { return result }

        result = Secretary.findByUsername(username)
        if(result) { return result }

        result = Director.findByUsername(username)
        if(result) { return result }

        return null
    }

    public Person validateLogin(String username, String password) {
        Person p = getByUsername(username)
        if(!p) { return null }

        String pHash = getPasswordHash(password)
        
        boolean validLogin = false
        boolean hasActivePassword = false
        
        UserPassword pass
        p.passwords.each {
            if(it.status == 1) {
                pass = it
                hasActivePassword = true
            }
            if(hasActivePassword && pHash.equals(it.passwordHash)) {
                pass = it
                validLogin = true
            }
        }

        // El usuario no tiene ninuna contraseña activa. Se encuentra bloqueado.
        if(!hasActivePassword) {
            return null
        }

        if(validLogin) {
            pass.failedLoginAttempts = 0
            pass.save(flush: true)
            return p
        }

        pass.failedLoginAttempts ++
        if(pass.failedLoginAttempts >= grailsApplication.config.university.login.maxAttempts) {
            UserPassword.withTransaction {
                pass.status = 0
                pass.save(flush: true)
            }

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

                mailService.sendMail {
                    to emails.toArray()
                    subject "UES22 - Cuenta bloqueada!"
                    html(content)
                }
            }
            throw new UserLockedException("User ${p.id} has been locked")
        }

        return null
    }

    private String getPasswordHash(String password) {
        return StringUtils.getMD5(password)
    }
}
