package com.ues21

class CathedrasXStudents implements Serializable {

    int status = 1
    Cathedra cathedra
    Student student
    Date registrationDate = new Date()

    static constraints = {
        cathedra(nullable: false, unique: ["status", "student"])
        student nullable: false
        registrationDate nullable: false
    }
}
