package com.ues21

class ManagementController {

    def main() {
        render(view: "main", model: [user: session.user])
    }
}
