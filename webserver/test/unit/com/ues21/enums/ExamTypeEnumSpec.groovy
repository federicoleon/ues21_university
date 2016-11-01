package com.ues21.enums

import spock.lang.Specification

/**
 * See the API for {@link grails.test.mixin.web.ControllerUnitTestMixin} for usage instructions
 */
class ExamTypeEnumSpec extends Specification {

    def setup() {
    }

    def cleanup() {
    }
    
    void "test only 5 exam types"() {
        when:
            def known = ExamTypeEnum.values()

        then:
            4 == known.size()

            [
                ExamTypeEnum.FIRST,
                ExamTypeEnum.SECOND,
                ExamTypeEnum.RETRY,
                ExamTypeEnum.FINAL
            ].containsAll(known)
    }

    void "test exam type first"() {
        when:
            def examType = ExamTypeEnum.FIRST

        then:
            1 == examType.id()
            "Primer parcial" == examType.type()
    }

    void "test exam type first by id"() {
        when:
            def examType = ExamTypeEnum.byId(1)

        then:
            1 == examType.id()
            "Primer parcial" == examType.type()
    }

    void "test exam type second"() {
        when:
            def examType = ExamTypeEnum.SECOND

        then:
            2 == examType.id()
            "Segundo parcial" == examType.type()
    }

    void "test exam type second by id"() {
        when:
            def examType = ExamTypeEnum.byId(2)

        then:
            2 == examType.id()
            "Segundo parcial" == examType.type()
    }

    void "test exam type retry"() {
        when:
            def examType = ExamTypeEnum.RETRY

        then:
            3 == examType.id()
            "Recuperatorio" == examType.type()
    }

    void "test exam type retry by id"() {
        when:
            def examType = ExamTypeEnum.byId(3)

        then:
            3 == examType.id()
            "Recuperatorio" == examType.type()
    }

    void "test exam type final"() {
        when:
            def examType = ExamTypeEnum.FINAL

        then:
            4 == examType.id()
            "Final" == examType.type()
    }

    void "test exam type final by id"() {
        when:
            def examType = ExamTypeEnum.byId(4)

        then:
            4 == examType.id()
            "Final" == examType.type()
    }

    void "test exam type unknown by id"() {
        when:
            def examType = ExamTypeEnum.byId(5)

        then:
            null == examType
    }
}
