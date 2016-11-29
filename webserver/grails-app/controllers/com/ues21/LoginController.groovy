package com.ues21

import com.ues21.utils.*

class LoginController {

    def personService
    def messageSource

    def auth() {
        String username = params.username
        String password = params.password
        boolean passwordChanged = params.boolean("password_changed", false)

        def model = [
            invalidLoginData: false,
            passwordChanged: passwordChanged
        ]

        if(username == null || password == null) {
            return model
        }

        session.user = null

        User user = personService.validateLogin(username, password)

        if(!user) {
            model.invalidLoginData = true
            return model
        }

        if(user.accountLocked) {
            redirect(action: "locked")
            return false
        }
        
        redirect(controller: "management", action: "main")
        return true
    }

    def logout() {
        if(session.user != null) {
            session.user = null
        }
        redirect(action:'auth')
        return false
    }

    def locked() {

    }

    def forgotPassword() {
        def model = [
            success: true
        ]
        def username = params.username
        if(!username) {
            return model
        }

        personService.sendRetrievePasswordEmail(username)

        return model
    }

    def newPassword() {
        def tokenId = params.token
        String newPassword = params.newPassword
        String confirmPassword = params.confirmPassword

        boolean success = personService.validateRetrievePasswordToken(tokenId)
        if(!success) {
            redirect(action: "auth")
            return false
        }

        PasswordResetToken token = PasswordResetToken.findByToken(tokenId)

        def model = [
            tokenId: tokenId,
            user: token.user
        ]

        if(!newPassword || !confirmPassword) {
            return model
        }

        User user = personService.processPasswordChangeByToken(tokenId, newPassword, confirmPassword)
        if(!user) {
            redirect(action: "auth")
            return true
        }

        model.user = user
        if(!user.hasErrors()) {
            session.user = null
            redirect(action: "auth", params: [password_changed: true])
            return true
        }

        return model
    }

    def changePassword() {
        if(!session.user) {
            redirect(action: "auth")
            return false
        }

        session.user.clearErrors()

        String password = params.password
        String newPassword = params.newPassword
        String confirmPassword = params.confirmPassword

        def model = [ error_message: null ]

        if(password == null && newPassword == null && confirmPassword == null) {
            return [user: session.user]
        }

        User user = personService.changePassword(session.user, password, newPassword, confirmPassword, false)
        if(user.hasErrors()) {
            return [ user: user ]
        }
        session.user = null
        redirect(action: "auth", params: [password_changed: true])
        return true
    }

    private String getMessage(String key) {
        if(!key) { return "" }
        return messageSource.getMessage(key, null, request.locale)
    }
}
