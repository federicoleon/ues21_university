package com.ues21.exceptions

import javax.servlet.http.HttpServletResponse

class APIException extends RuntimeException {
    
    def status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
    def error
    def internalCause = []

    APIException(status, message, error, cause) {
        super(message.toString(), (cause in Throwable) ? cause : null)
        this.status = status
        this.error = error
        this.internalCause = cause
    }

    APIException(message){
        super(message)
    }

    APIException() {
        super("internal_error")
    }

    public Map getMap() {
        return [
            message: super.message,
            error: error
        ]
    }
}
