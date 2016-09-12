package com.ues21

abstract class Person implements Serializable {
    
    String firstName
    String lastName
    Identification identification
    static hasMany = [emails: Email, phones: Phone]

    Date creationDate = new Date()
    Date lastUpdate = new Date()

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
        firstName nullable: false, blank: false
        lastName nullable: false, blank: false
        identification nullable: false
        creationDate nullable: false
        lastUpdate nullable: false
    }

    public abstract String getRole()
}
