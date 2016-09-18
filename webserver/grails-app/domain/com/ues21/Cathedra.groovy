package com.ues21

class Cathedra {
    
    Classroom classroom
    Subject subject
    CathedraPeriod period

    static mapping = {
        subject(sort: "semester", order: "asc")
    }
}
