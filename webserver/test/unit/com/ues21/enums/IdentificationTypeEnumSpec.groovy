package com.ues21.enums

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class IdentificationTypeEnumSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }

    void "test only 1 identification type"() {
        when:
            def known = IdentificationTypeEnum.values()

        then:
            1 == known.size()

            [
                IdentificationTypeEnum.DNI
            ].containsAll(known)
    }

    void "test identification type DNI"() {
        when:
            def identification = IdentificationTypeEnum.DNI

        then:
            1 == identification.id()
            "DNI" == identification.type()
            "Documento Nacional de Identidad" == identification.value()
    }

    void "test identification type DNI by id"() {
        when:
            def identification = IdentificationTypeEnum.byId(1)

        then:
            1 == identification.id()
            "DNI" == identification.type()
            "Documento Nacional de Identidad" == identification.value()
    }

    void "test identification unkown by id"() {
        when:
            def identification = IdentificationTypeEnum.byId(2)

        then:
            null == identification
    }
}
