class UrlMappings {

    static mappings = {

        "/"(controller: "login", action: "login")

        "/management/logout"(controller: "login", action: "logout")

        "/account_recovery"(controller: "login", action: "accountRecovery")

        "/$controller/$action?/$id?(.$format)?" {
            constraints { }
        }
    }
}
