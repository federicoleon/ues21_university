package com.ues21

import com.ues21.enums.UserRoleEnum

class Director extends Person implements Serializable {

    String fileNumber

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
        fileNumber nullable: false, blank: false
    }

    public String getRole() {
        return UserRoleEnum.DIRECTOR.role()
    }
}
