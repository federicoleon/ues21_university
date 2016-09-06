package com.ues21.marshallers

import com.ues21.*
import grails.converters.JSON

class CareerPlanMarshaller {
    
    public void register() {
        JSON.registerObjectMarshaller(CareerPlan) { pl ->
            Map result = [:]
            result["id"] = pl.id
            result["name"] = pl.name
            result["subjects"] = pl.subjects
            return result
        }
    }
}
