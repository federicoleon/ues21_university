class AuthenticationFilters {
    
    def filters = {
        checkAuthentication(controller: "login", invert: true) {
            before = {
                if (!session.user) {
                    redirect(controller: "login", action: "auth")
                    return false
                }
            }
        }
    }
}
