package com.ues21.users

class User implements Serializable {

    String username
    String password
    String role

    static constraints = {
        username size: 5..20, blank: false
        password size: 5..20, blank: false
        role blank: false
    }

    public String getRole() {
        return role
    }
}
