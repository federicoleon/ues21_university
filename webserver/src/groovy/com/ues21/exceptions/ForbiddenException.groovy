package com.ues21.exceptions

import javax.servlet.http.HttpServletResponse

class ForbiddenException extends APIException {

    private static final int STATUS = HttpServletResponse.SC_FORBIDDEN

    def ForbiddenException(message, error = "forbidden", cause = []) {
        super(STATUS, message, error, cause)
    }
}
