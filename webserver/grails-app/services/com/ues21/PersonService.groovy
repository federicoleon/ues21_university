package com.ues21

import com.ues21.enums.*
import com.ues21.utils.*

import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray

class PersonService {

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
        p.password = StringUtils.getMD5(data.password)

        if(p.validate()) {
            p.save(flush: true, failOnError: true)
            return p
        }
        return null
    }

    public Person validateAuthentication(String username, String password) {
        Person result
        password = StringUtils.getMD5(password)
        result = Student.findByUsernameAndPassword(username, password)
        if(result) { return result }

        result = Teacher.findByUsernameAndPassword(username, password)
        if(result) { return result }

        result = Secretary.findByUsernameAndPassword(username, password)
        if(result) { return result }

        result = Director.findByUsernameAndPassword(username, password)
        if(result) { return result }

        return null
    }
}
