package com.ues21.gebPages

import geb.Page

class LoginPage extends Page {

    static url = "/"

    static at = { title == "Ingreso a Autogestión" }

    static content = {
        form{ $("#login-container #loginForm") }
        loginButton{ $("#login-container #loginForm #loginButton") }
        errors{ $("#login-container .error") }
    }
}
