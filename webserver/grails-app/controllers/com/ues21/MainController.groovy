package com.ues21

class MainController {

    private static final String SECTION_HOME = "home"
    private static final String SECTION_UNIVERSITY = "university"
    private static final String SECTION_CONTACT = "contact"

    def home() {
        def model = getDefaultModel(SECTION_HOME)
        render(view: "index", model: model)
    }

    def university() {
        def model = getDefaultModel(SECTION_UNIVERSITY)
        render(view: "university", model: model)
    }

    def contact() {
        def model = getDefaultModel(SECTION_CONTACT)
        render(view: "contact", model: model)
    }

    private Map getDefaultModel(String section) {
        return [
            year: new Date().format("yyyy"),
            section: section
        ]
    }
}
