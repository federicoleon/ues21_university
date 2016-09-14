package com.ues21

class Career implements Serializable {

    String name
    static hasMany = [plans: CareerPlan]

    public String toString() {
        return name
    }
}
