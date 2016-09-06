package com.ues21

class Subject {

    String name
    int points

    static hasMany = [correlatives: Subject]
}
