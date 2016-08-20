package com.ues21

import com.ues21.users.User

class UserService {

    public boolean validateUserLogin(User user) {
        if(!user) {
            return false
        }
        user.role = "student"
        return user.username == "fede" && user.password == "leon"
    }
}
