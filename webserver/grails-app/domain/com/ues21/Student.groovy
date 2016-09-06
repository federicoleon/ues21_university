package com.ues21

class Student extends Person {

    String fileNumber
    String username
    String password
    
    static hasMany = [careers: Career]

    static constraints = {
        fileNumber nullable: false, blank: false
        username nullable: false, blank: false
        password nullable: false, blank: false
    }
}
