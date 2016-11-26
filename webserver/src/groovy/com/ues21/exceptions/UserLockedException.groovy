package com.ues21.exceptions

import javax.servlet.http.HttpServletResponse

class UserLockedException extends APIException {

    private static final int STATUS = HttpServletResponse.SC_BAD_REQUEST

    def UserLockedException(message, error = "forbidden", cause = []) {
        super(STATUS, message, error, cause)
    }
}
