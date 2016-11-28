package com.ues21

import com.ues21.utils.*
import com.ues21.exceptions.UserLockedException

class LoginController {

    def personService

    def auth() {
        String username = params.username
        String password = params.password

        if(username == null || password == null) {
            return [invalidLoginData: false]
        }
        session.person = null
        
        User user = personService.validateLogin(username, password)
        
        if(!user) {
            return [invalidLoginData: true]
        }

        if(user.accountLocked) {
            redirect(action: "locked")
            return false
        }
        
        redirect(controller: "management", action: "main")
        return true
    }

    def logout() {
        if(session.person != null) {
            session.person = null
        }
        redirect(action:'auth')
        return false
    }

    def locked() {

    }

    def accountRecovery() {
        
    }
}
