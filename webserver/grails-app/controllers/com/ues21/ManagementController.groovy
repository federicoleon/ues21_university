package com.ues21

import org.springframework.security.access.annotation.Secured

class ManagementController {

    @Secured(["IS_AUTHENTICATED_ANONYMOUSLY"])
    def main() {
        render(view: "main", model: [person: session.person])
    }
}
