package com.ues21

class Career {

    String name
    static hasMany = [plans: CareerPlan]

    public String toString() {
        return name
    }
}
