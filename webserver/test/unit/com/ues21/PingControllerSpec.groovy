package com.ues21

import grails.test.mixin.TestFor
import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
@TestFor(PingController)
class PingControllerSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test ping response"() {
        when:
            controller.ping()

        then:
            "pong" == response.text
    }
}