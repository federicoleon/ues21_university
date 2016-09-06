package com.ues21

class Person {
    String firstName
    String lastName
    Identification identification
    static hasMany = [emails: Email, phones: Phone]

    Date creationDate = new Date()
    Date lastUpdate = new Date()

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        identification nullable: false
        creationDate nullable: false
        lastUpdate nullable: false
    }
}
