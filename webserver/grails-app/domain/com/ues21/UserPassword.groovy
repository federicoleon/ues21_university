package com.ues21

class UserPassword implements Serializable {

    static belongsTo = [person: Person]

    Date date = new Date()
    String passwordHash
    int failedLoginAttempts = 0
    int status
}

