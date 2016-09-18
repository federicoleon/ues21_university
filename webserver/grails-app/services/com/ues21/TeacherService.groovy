package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject

import grails.transaction.Transactional

@Transactional
class TeacherService extends PersonService {

    public Teacher create(JSONObject data) {
        return super.createFromGeneric(data)
    }
}
