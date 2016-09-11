package com.ues21

class ManagementController {

    def main() {
        render(view: "main", model: [person: session.person])
    }
}
