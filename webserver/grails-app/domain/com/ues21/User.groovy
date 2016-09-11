
package com.ues21

class User {
    String profile
    String username
    String password

    static constraints = {
        profile nullable: true, blank: false
        username nullable: true, blank: false
        password nullable: true, blank: false
    }
}
