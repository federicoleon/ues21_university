package com.ues21

class LoginController {

    def userService

    def login() {
        String username = params.username
        String password = params.password

        boolean invalidLoginData = false

        if(username != null && password != null) {
            User user = new User()
            user.username = username
            user.password = password

            boolean isValidUserLogin = userService.validateUserLogin(user)

            if(isValidUserLogin) {
                session.user = user
                redirect(controller: "management", action: "main")
                return true
            }else{
                invalidLoginData = true
            }
        }

        return [invalidLoginData: invalidLoginData]
    }

    def logout() {
        if(session.user != null) {
            session.user = null
        }
        redirect(action:'login')
        return false
    }
}
