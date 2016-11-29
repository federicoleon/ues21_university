class UrlMappings {

    static mappings = {

        "/"(controller: "login", action: "auth")

        "/management/logout"(controller: "login", action: "logout")

        "/login/forgot_password"(view: "login/forgotPassword")

        "/login/change_password"(controller: "login", action: "changePassword")

        "/account_recovery/$token"(controller: "login", action: "newPassword")

        "/$controller/$action?/$id?(.$format)?" {
            constraints { }
        }
    }
}
