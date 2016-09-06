package com.ues21.enums

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class PhoneTypeEnumSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test only 2 phone types"() {
        when:
            def known = PhoneTypeEnum.values()

        then:
            2 == known.size()

            [
                PhoneTypeEnum.LINE,
                PhoneTypeEnum.MOBILE
            ].containsAll(known)
    }

    void "test phone type line"() {
        when:
            def phoneType = PhoneTypeEnum.LINE

        then:
            1 == phoneType.id()
            "Fijo" == phoneType.type()
    }

    void "test phone type line by id"() {
        when:
            def phoneType = PhoneTypeEnum.byId(1)

        then:
            1 == phoneType.id()
            "Fijo" == phoneType.type()
    }

    void "test phone type mobile"() {
        when:
            def phoneType = PhoneTypeEnum.MOBILE

        then:
            2 == phoneType.id()
            "Celular" == phoneType.type()
    }

    void "test phone type mobile by id"() {
        when:
            def phoneType = PhoneTypeEnum.byId(2)

        then:
            2 == phoneType.id()
            "Celular" == phoneType.type()
    }

    void "test phone type unknown by id"() {
        when:
            def phoneType = PhoneTypeEnum.byId(3)

        then:
            null == phoneType
    }
}
