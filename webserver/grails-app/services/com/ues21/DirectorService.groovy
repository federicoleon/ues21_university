package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject

import grails.transaction.Transactional

@Transactional
class DirectorService extends PersonService {
    
    public Director create(JSONObject data) {
        return super.createFromGeneric(data)
    }
}
