package com.ues21

class LoginController {

    def personService

    def login() {
        String username = params.username
        String password = params.password

        boolean invalidLoginData = false

        if(username != null && password != null) {
            Person person = personService.validateAuthentication(username, password)
            if(person) {
                session.person = person
                redirect(controller: "management", action: "main")
                return true
            }else{
                invalidLoginData = true
            }
        }
        return [invalidLoginData: invalidLoginData]
    }

    def logout() {
        if(session.person != null) {
            session.person = null
        }
        redirect(action:'login')
        return false
    }
}
