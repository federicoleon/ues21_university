package com.ues21

class ExamXCathedra {
    
    Cathedra cathedra
    Exam exam
    String status

    static constraints = {
        cathedra nullable: false
        exam nullable: false
        status nullable: false
    }
}
