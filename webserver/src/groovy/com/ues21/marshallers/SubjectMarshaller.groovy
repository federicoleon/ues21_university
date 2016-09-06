package com.ues21.marshallers

import com.ues21.*
import grails.converters.JSON

class SubjectMarshaller {
    
    public void register() {
        JSON.registerObjectMarshaller(Subject) { sbj ->
            Map result = [:]
            result["id"] = sbj.id
            result["name"] = sbj.name
            result["points"] = sbj.points
            return result
        }
    }
}
