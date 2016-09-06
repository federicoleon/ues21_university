package com.ues21

class Teacher extends Person {

    String fileNumber
    String username
    String password

    static constraints = {
        fileNumber nullable: false, blank: false
        username nullable: false, blank: false
        password nullable: false, blank: false
    }
}
