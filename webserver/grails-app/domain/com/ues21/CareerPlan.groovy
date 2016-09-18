package com.ues21

class CareerPlan {

    String name
    int status = 1

    static belongsTo = [career: Career]

    static hasMany = [subjects: Subject]
}
