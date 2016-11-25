package com.ues21

class UserPasswords implements Serializable {

    Date date = new Date()
    Long userId
    String passwordHash
    int failedLoginAttempts
    int status
}

