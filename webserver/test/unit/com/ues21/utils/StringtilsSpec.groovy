package com.ues21.utils

import spock.lang.Specification
/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class StringtilsSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test get MD5 null string"() {
        given:
            String string = null

        when:
            def md5 = StringUtils.getMD5(string)

        then:
            "" == md5
    }

    void "test get MD5 empty string"() {
        given:
            String string = ""

        when:
            def md5 = StringUtils.getMD5(string)

        then:
            "" == md5
    }

    void "test get MD5 no error"() {
        given:
            String string = "super testing string"

        when:
            def md5 = StringUtils.getMD5(string)

        then:
            "216b71af47663213eb64024e675e772a" == md5
    }
}
