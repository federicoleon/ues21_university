package com.ues21

class ManagementController {

    def userService

    def login() {
        String username = params.username
        String password = params.password

        if(username && password) {
            User user = new User()
            user.username = username
            user.password = password

            boolean isValidUserLogin = userService.validateUserLogin(user)

            if(isValidUserLogin) {
                session.user = user
                redirect(action:'main')
                return false
            }
        }
    }

    def logout() {
        if(session.user != null) {
            session.user = null
        }
        redirect(action:'login')
        return false
    }

    def main() {
        render(view: "main", model: getModel())
    }

    def rates() {
        render(view: "rates", model: getModel())
    }

    def assistance() {
        render(view: "assistance", model: getModel())
    }

    private Map getModel() {
        return [
            user: session.user
        ]
    }
}
