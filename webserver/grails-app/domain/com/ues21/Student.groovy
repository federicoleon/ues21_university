package com.ues21

import com.ues21.enums.UserTypeEnum

class Student extends Person implements Serializable {

    String fileNumber
    String username
    String password

    static mapping = {
        tablePerHierarchy false
    }
    
    static hasMany = [careers: Career]

    static constraints = {
        fileNumber nullable: false, blank: false
        username nullable: false, blank: false
        password nullable: false, blank: false
    }

    public String getRole() {
        return UserTypeEnum.STUDENT.role()
    }

    public String getFullName() {
        return lastName.toUpperCase() + ", " + firstName.toUpperCase()
    }
}
