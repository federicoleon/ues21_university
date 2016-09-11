class UrlMappings {

    static mappings = {

        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/management/main" (controller: "management") {
            action = [ GET:"main" ]
        }

        "/"(controller:"login", action:"login")
    }
}
