package com.ues21

class ExamXCathedra implements Serializable {
    
    Cathedra cathedra
    ExamType examType
    int status = 1
    Date creationDate = new Date()

    static constraints = {
        cathedra nullable: false
        examType nullable: false
        status nullable: false
    }
}
