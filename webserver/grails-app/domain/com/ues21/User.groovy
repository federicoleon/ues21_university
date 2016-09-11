package com.ues21

import com.ues21.constants.Constants

class User implements Serializable {

    String username
    String password
    String role

    static constraints = {
        username size: 5..20, blank: false
        password size: 5..20, blank: false
        role blank: false
    }

    public boolean isStudent() {
        return Constants.STUDENT.equalsIgnoreCase(role)
    }

    public boolean isTeacher() {
        return Constants.TEACHER.equalsIgnoreCase(role)
    }

    public boolean isDirector() {
        return Constants.DIRECTOR.equalsIgnoreCase(role)
    }
}
