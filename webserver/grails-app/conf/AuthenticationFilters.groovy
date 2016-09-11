class AuthenticationFilters {
    
    def filters = {
        checkAuthentication(controller: "login", invert: true) {
            before = {
                if (!session.user && !actionName.equals("login")) {
                    redirect(action: "login")
                    return false
                }
            }
        }
    }
}
