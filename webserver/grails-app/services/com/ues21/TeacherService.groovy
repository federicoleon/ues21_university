package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject

class TeacherService extends PersonService {

    public Teacher create(JSONObject data) {
        return super.createFromGeneric(data)
    }
}
