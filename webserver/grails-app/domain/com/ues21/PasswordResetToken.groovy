package com.ues21

import com.ues21.utils.DateUtils

public class PasswordResetToken {

    // Forgot password token will be available only for one day.
    private transient final static int EXPIRATION_MINUTES = 60 * 24

    static belongsTo = [user: User]

    Date expirationDate = getExpiration()
    String token

    static constraints = {
        user(unique: true)
        token(nullable: false, blank: false)
    }

    def beforeInsert() {
        expirationDate = getExpiration()
    }

    def beforeUpdate() {
        expirationDate = getExpiration()
    }

    private transient Date getExpiration() {
        return DateUtils.addMinutesToCurrent(EXPIRATION_MINUTES)
    }

    public transient boolean isExpired() {
        return expirationDate < new Date()
    }
}
