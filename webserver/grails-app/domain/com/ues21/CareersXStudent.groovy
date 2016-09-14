package com.ues21

class CareersXStudent implements Serializable {

    int status = 1
    Date registrationDate = new Date()
    Career career
    Student student

    static constraints = {
        status nullable: false
        registrationDate nullable: false
        career nullable: false
        student nullable: false
    }
}
