package com.ues21.exceptions

import javax.servlet.http.HttpServletResponse

class UnauthorizedException extends APIException {

    private static final int STATUS = HttpServletResponse.SC_UNAUTHORIZED

    def UnauthorizedException(message, error = "unauthorized", cause = []) {
        super(STATUS, message, error, cause)
    }
}
