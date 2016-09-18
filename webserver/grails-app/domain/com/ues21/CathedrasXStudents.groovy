package com.ues21

class CathedrasXStudents {

    int status = 1
    Cathedra cathedra
    Student student
    Date registrationDate = new Date()
    Person taskExecutor

    static constraints = {
        cathedra nullable: false
        student nullable: false
        registrationDate nullable: false
        taskExecutor nullable: false
    }
}
