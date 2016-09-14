package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject

class StudentService extends PersonService {
    
    public Student create(JSONObject data) {
        return super.createFromGeneric(data)
    }

    public List<Cathedra> getAvailableCathedras(Student student) {
        //TODO: Retornar lista de cátedras disponibles según correlativas y finales.
        return Cathedra.list()
    }
}
