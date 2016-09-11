package com.ues21

class Email {

    String address
    int status

    static belongsTo = [person: Person]
}
