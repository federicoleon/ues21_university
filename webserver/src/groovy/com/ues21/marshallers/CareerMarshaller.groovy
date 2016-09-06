package com.ues21.marshallers

import com.ues21.*
import grails.converters.JSON

class CareerMarshaller {
    
    public void register() {
        JSON.registerObjectMarshaller(Career) { career ->
            Map result = [:]
            result["id"] = career.id
            result["name"] = career.name
            result["plans"] = career.plans
            return result
        }
    }
}
