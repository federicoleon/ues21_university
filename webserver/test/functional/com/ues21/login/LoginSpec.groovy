package com.ues21.login

import geb.spock.GebReportingSpec
import spock.lang.*

import com.ues21.gebPages.*

@Stepwise
class LoginSpec extends GebReportingSpec {

    def "check login page"() {
        given: "Go to the Login page"
            to LoginPage

        expect: "Check elements in the screen"
            at LoginPage

            title == "Ingreso a Autogestión"
            
            form.username == ""
            form.password == ""

            loginButton != null

            loginButton.attr("value") == "Ingresar"
    }

    def "invalid login"() {
        given: "Go to the Login page"
            to LoginPage

        when: "Try to access with wrong data"
            form.username = "somemockeduser"
            form.password = "thisIsThePass"
            loginButton.click()

        then: "Check there is a login error on the screen"
            at LoginPage

            title == "Ingreso a Autogestión"

            form.username == ""
            form.password == ""

            errors[0].text() == "Usuario o contraseña incorrectos"
    }

    def "login ok"() {
        given: "Go to the Login page"
            to LoginPage

        when: "Try to access with wrong data"
            form.username = "fede"
            form.password = "leon"
            loginButton.click()

        then: "Check we are inside management panel"
            at MainPage
    }
}
