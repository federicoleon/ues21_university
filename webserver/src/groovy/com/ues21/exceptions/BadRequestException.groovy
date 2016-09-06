package com.ues21.exceptions

import javax.servlet.http.HttpServletResponse

class BadRequestException extends APIException {

    private static final int STATUS = HttpServletResponse.SC_BAD_REQUEST

    def BadRequestException(message, error = "bad_request", cause = []) {
        super(STATUS, message, error, cause)
    }

}
