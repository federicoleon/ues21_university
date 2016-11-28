class AuthenticationFilters {
    
    def filters = {
        checkAuthentication(controller: "login", invert: true) {
            before = {
                if (!session.person) {
                    redirect(controller: "login", action: "auth")
                    return false
                }
            }
        }
    }
}
