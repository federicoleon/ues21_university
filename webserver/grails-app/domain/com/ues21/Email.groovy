package com.ues21

class Email implements Serializable {

    public transient final static int STATUS_ACTIVE = 1

    String address
    int status = STATUS_ACTIVE

    static belongsTo = [person: Person]

    static constraints = {
        address(blank: false, email: true, unique: true)
    }
}
