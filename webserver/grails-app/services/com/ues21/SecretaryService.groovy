package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject

import grails.transaction.Transactional

@Transactional
class SecretaryService extends PersonService {
    
    public Secretary create(JSONObject data) {
        return super.createFromGeneric(data)
    }
}
