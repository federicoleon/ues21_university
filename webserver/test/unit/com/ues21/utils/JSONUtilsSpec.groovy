package com.ues21.utils

import spock.lang.Specification
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class JSONUtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test parse invalid json"() {
        given:
            String string = ''

        when:
            def json = JSONUtils.parseString(string)

        then:
            null == json
    }

    void "test parse correct jsonObject"() {
        given:
            String string = '{"first_name":"Adriana","last_name":"Perez"}'

        when:
            def json = JSONUtils.parseString(string)

        then:
            JSONObject == json.getClass()
            2 == json.size()
            "Adriana" == json.first_name
            "Perez" == json.last_name
    }

    void "test parse correct jsonArray"() {
        given:
            String string = '[{"first_name":"Adriana","last_name":"Perez"},{"first_name":"Ana Carolina","last_name":"Ferreyra"}]'

        when:
            def json = JSONUtils.parseString(string)

        then:
            JSONArray == json.getClass()
            
            2 == json.size()
            
            JSONObject first = json.get(0)
            "Adriana" == first.first_name
            "Perez" == first.last_name

            JSONObject second = json.get(1)
            "Ana Carolina" == second.first_name
            "Ferreyra" == second.last_name
    }
}
