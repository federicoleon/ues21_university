package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject

class StudentService extends PersonService {
    
    public Student create(JSONObject data) {
        return super.createFromGeneric(data)
    }
}
