package com.ues21

class Email implements Serializable {

    String address
    int status = 1

    static belongsTo = [person: Person]

    static constraints = {
        address email: true
    }
}
