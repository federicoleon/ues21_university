package com.ues21

import com.ues21.enums.ExamStatusEnum

class ExamXStudent {

    Date date = new Date()
    Student student
    ExamXCathedra exam
    float grade = -1F
    int status

    static constraints = {
        date nullable: false
        student nullable: false
        exam nullable: false
        status inList: ExamStatusEnum.values()*.id()
    }

    public String getExamStatusValue() {
        return ExamStatusEnum.getExamStatusValue(grade)
    }
}
