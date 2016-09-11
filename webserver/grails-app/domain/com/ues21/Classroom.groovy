package com.ues21

class Classroom {
    String name
    Integer center
    Integer building
    Integer floor

    static constraints = {
        name nullable: false, blank: false
        center nullable: false, blank: false
        building nullable: false, blank: false
        floor nullable: false, blank: false
    }
}
