package com.ues21.utils

import grails.converters.JSON

class JSONUtils {

    public static def parseString(String json) {
        try {
            return JSON.parse(json)
        } catch(Exception e) {
            return  null
        }
    }
}
