class UrlMappings {

    static mappings = {

        "/"(controller: "login", action: "login")

        "/management/logout"(controller: "login", action: "logout")

        "/$controller/$action?/$id?(.$format)?" {
            constraints { }
        }
    }
}
