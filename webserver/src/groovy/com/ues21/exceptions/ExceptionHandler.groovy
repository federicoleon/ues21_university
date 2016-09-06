package com.ues21.exceptions

import grails.converters.JSON

trait ExceptionHandler {

    def exceptionHandler(Exception e) {
        response.status = e.status
        render e.getMap() as JSON
    }
}
