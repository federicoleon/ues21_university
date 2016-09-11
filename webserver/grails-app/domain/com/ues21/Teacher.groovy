package com.ues21

import com.ues21.enums.UserTypeEnum

class Teacher extends Person {

    String fileNumber
    String username
    String password

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
        fileNumber nullable: false, blank: false
        username nullable: false, blank: false
        password nullable: false, blank: false
    }

    public String getRole() {
        return UserTypeEnum.TEACHER.role()
    }
}
