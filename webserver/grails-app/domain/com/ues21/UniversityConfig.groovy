package com.ues21

class UniversityConfig {

    Career career
    int currentExamPeriod = 0
    int status = 0
    Date lastUpdate = new Date()

    static constraints = {
        career(nullable: false, unique: true)
        currentExamPeriod nullable: false
    }
}
