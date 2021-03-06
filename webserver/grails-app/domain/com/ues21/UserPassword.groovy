package com.ues21

class UserPassword implements Serializable {

    public transient final static int STATUS_ACTIVE = 0
    public transient final static int STATUS_LOCKED = 1
    public transient final static int STATUS_INACTIVE = 2

    static belongsTo = [user: User]

    Date date = new Date()
    String passwordHash
    int failedLoginAttempts = 0
    int status = STATUS_ACTIVE
}

