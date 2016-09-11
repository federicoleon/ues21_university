package com.ues21

import com.ues21.utils.StringUtils

class UserService {

    public Person validateUserLogin(String username, String password) {
        if(!username || !password) {
            return false
        }
        password = StringUtils.getMD5(password)
        def person = Person.findByUsernameAndPassword(username, password)
        return null
    }
}
