package com.ues21.career

import grails.test.spock.IntegrationSpec
import grails.test.mixin.TestFor

import javax.servlet.http.HttpServletResponse
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray

@TestFor(CareerController)
class CareerControllerIntegrationSpec extends IntegrationSpec {

    def setup() {
    }

    def cleanup() {
    }

    void "test list all careers"() {
        when:
            def result = controller.list()

        then:
            HttpServletResponse.SC_OK == controller.response.status
            
            JSONArray json = controller.response.getJson()
            
            // We only know Software engineering for now.
            1 == json.size()
            
            JSONObject career = json.get(0)
            "Ingeniería en Software" == career.name

            JSONArray plans = career.plans
            1 == plans.size()

            JSONObject plan = plans.get(0)
            "2008" == plan.name

            JSONArray subjects = plan.subjects
            46 == subjects.size()
    }

    void "test get career id 1 software engineering"() {
        given:
            controller.params.id = 1L

        when:
            def result = controller.getCareer()

        then:
            HttpServletResponse.SC_OK == controller.response.status
            JSONObject json = controller.response.getJson()

            // We only know Software engineering for now.
            "Ingeniería en Software" == json.name

            JSONArray plans = json.plans
            1 == plans.size()

            JSONObject plan = plans.get(0)
            "2008" == plan.name

            JSONArray subjects = plan.subjects
            46 == subjects.size()
    }
}
