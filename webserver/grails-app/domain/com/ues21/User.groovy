
package com.ues21

class User implements Serializable {
    String profile
    String username
    String password

    static constraints = {
        profile nullable: true, blank: false
        username nullable: true, blank: false, size: 5..15
        password nullable: true, blank: false, size: 5..15
    }
}
