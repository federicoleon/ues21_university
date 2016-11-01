package com.ues21

class Career implements Serializable {

    String name
    static hasMany = [plans: CareerPlan]

    public String toString() {
        return name
    }

    @Override
    public boolean equals (Object other) {
        if(!other) {
            return false
        }
        Career aux = (Career)other
        return this.name == other.name
    }
}
