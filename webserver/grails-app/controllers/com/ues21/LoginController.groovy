package com.ues21

import com.ues21.utils.*
import com.ues21.exceptions.UserLockedException

class LoginController {

    def personService

    def login() {
        String username = params.username
        String password = StringUtils.getMD5(params.password)

        if(username == null || password == null) {
            return [invalidLoginData: false]
        }
        session.person = null
        
        Person person
        try {
            person = personService.validateLogin(username, password)
        } catch(UserLockedException e) {
            redirect(action: "locked")
            return false
        }
        if(!person) {
            return [invalidLoginData: true]
        }
        session.person = person
        redirect(controller: "management", action: "main")
        return true
    }

    def logout() {
        if(session.person != null) {
            session.person = null
        }
        redirect(action:'login')
        return false
    }

    def locked() {

    }

    def accountRecovery() {
        
    }
}
