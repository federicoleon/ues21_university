class AuthenticationFilters {
    
    def filters = {
        checkAuthentication(controller: "management", action: "*") {
            before = {
                if (!session.user && !actionName.equals("login")) {
                    redirect(action: "login")
                    return false
                }
            }
        }
    }
}
