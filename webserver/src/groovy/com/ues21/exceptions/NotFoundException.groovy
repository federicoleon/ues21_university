package com.ues21.exceptions

import javax.servlet.http.HttpServletResponse

class NotFoundException extends APIException {

    private static final int STATUS = HttpServletResponse.SC_NOT_FOUND

    def NotFoundException(message, error = "not_found", cause = []) {
        super(STATUS, message, error, cause)
    }
}
