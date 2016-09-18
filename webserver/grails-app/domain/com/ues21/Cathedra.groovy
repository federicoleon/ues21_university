package com.ues21

class Cathedra implements Serializable {

    String name

    Classroom classroom
    Subject subject
    CathedraPeriod period

    int maxAbsences = 0

    static hasMany = [timeTable: CathedraTime]

    static mapping = {
        subject(sort: "semester", order: "asc")
    }
}
