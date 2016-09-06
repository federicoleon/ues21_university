package com.ues21

class UserService {

    public boolean validateUserLogin(User user) {
        if(!user) {
            return false
        }
        user.role = "student"
        return user.username == "fede" && user.password == "leon"
    }
}
