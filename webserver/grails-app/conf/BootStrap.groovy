import com.ues21.*
import com.ues21.enums.*
import com.ues21.utils.*
import com.ues21.exceptions.*
import org.codehaus.groovy.grails.web.json.JSONObject
import org.codehaus.groovy.grails.web.json.JSONArray

import org.codehaus.groovy.grails.web.context.ServletContextHolder

class BootStrap {

    def grailsApplication
    def personService

    def init = { servletContext ->

        grailsApplication.controllerClasses.each { controllerClass ->
            injectValidationMethods(controllerClass)
        }

        grailsApplication.serviceClasses.each { serviceClass ->
            injectValidationMethods(serviceClass)
        }

        environments {
            development {
                loadDatabaseMocks()
            }
            test {
                loadDatabaseMocks()
            }
            production {
                loadDatabaseMocks()
            }
        }

        grailsApplication.getMainContext().getBean("customObjectMarshallers").register()
    }

    def destroy = {
    }

    private void loadDatabaseMocks() {
        loadExamTypes()
        loadClassRooms()
        loadCathedraPeriods()
        loadCareers()
        loadCathedras()
        loadMockedUsers()
    }

    private void loadExamTypes() {
        ExamTypeEnum.values().each { examType ->
            ExamType type = new ExamType()
            type.id = examType.id()
            type.type = examType.type()
            type.save(flush: true, failOnError: true)
        }
    }

    private void loadCareers() {
        def files = new File(ServletContextHolder.servletContext.getRealPath('resources/mocks/careers'))
        files.eachFile { file ->
            def json = JSONUtils.parseString(file.getText())
            if(json) {
                loadCareerFromJson(json)
                loadCorrelativesFromJson(json)
            }
        }
    }

    private void loadCareerFromJson(JSONObject json) {
        Career career = new Career()
        career.name = json.name

        def plans = json.plans
        if(!plans) {
            return
        }
        plans.each { pl ->
            CareerPlan plan = new CareerPlan()
            plan.name = pl.name
            plan.career = career
            
            def subjects = pl.subjects
            if(!subjects) {
                return
            }
            subjects.each { sbj ->
                Subject subject = new Subject()
                subject.id = sbj.id
                subject.name = sbj.name
                subject.points = sbj.points
                subject.type = SubjectTypeEnum.REGULAR.id()
                subject.semester = sbj.semester[0]
                plan.addToSubjects(subject)
            }
            career.addToPlans(plan)
        }
        if(!career.validate()) {
            String message = "Error when trying to create career '${career.name}': ${career.errors}".toString()
            throw new Exception(message)
        }
        career.save(flush: true, failOnError: true)
    }

    private void loadCorrelativesFromJson(json) {
        def plans = json.plans
        if(!plans) {
            return
        }
        plans.each { pl ->
            def subjects = pl.subjects
            if(!subjects) {
                return
            }
            subjects.each { sbj ->
                if(sbj.correlatives) {
                    Subject current = Subject.get(sbj.id)
                    def correlatives = Subject.getAll(sbj.correlatives)
                    correlatives?.each { correlative ->
                        current.addToCorrelatives(correlative)
                    }
                    current.save(flush: true, failOnError: true)
                }
            }
        }
    }

    private void loadClassRooms() {
        def files = new File(ServletContextHolder.servletContext.getRealPath('resources/mocks/classrooms'))
        files.eachFile { file ->
            def json = JSONUtils.parseString(file.getText())
            if(json) {
                loadClassRoom(json)
            }
        }
    }

    private void loadClassRoom(JSONObject json) {
        if(!json) {
            return
        }

        def center = json.university_center
        def buildings = center?.buildings
        buildings?.each { b ->
            def classrooms = b?.classrooms
            classrooms?.each { cr ->
                Classroom classroom = new Classroom()
                classroom.center = center.id
                classroom.building = b.id
                classroom.name = cr.name
                classroom.floor = cr.floor
                if(classroom.validate()) {
                    classroom.save(flush: true, failOnError: true)
                }
            }
        }
    }

    private void loadCathedraPeriods() {
        Date date = new Date()
        int year = date[Calendar.YEAR]

        CathedraPeriodEnum.values().each { period ->
            CathedraPeriod cPeriod = new CathedraPeriod()
            cPeriod.id = period.id()
            cPeriod.year = year
            cPeriod.period = period.id()
            if(cPeriod.validate()) {
                cPeriod.save(flush: true, failOnError: true)
            }
        }
    }

    private void loadCathedras() {
        Date date = new Date()
        int year = date[Calendar.YEAR]
        List <Subject> subjects = Subject.list()
        subjects?.each { subject ->
            Cathedra cathedra = new Cathedra()
            cathedra.subject = subject
            cathedra.name = "A"
            cathedra.maxAbsences = 10
            
            int floor = subject.getAcademicYear()
            def classroom = Classroom.findByFloor(floor)
            cathedra.classroom = classroom

            def period = CathedraPeriod.findByPeriodAndYear(subject.getPeriod(), year)
            cathedra.period = period
            
            if(cathedra.validate()) {
                cathedra.save(flush:true, failOnError: true)
            }
        }
    }

    private void loadMockedUsers() {
        def files = new File(ServletContextHolder.servletContext.getRealPath('resources/mocks/users'))
        files.eachFile { file ->
            def json = JSONUtils.parseString(file.getText())
            if(json) {
                loadMockedUser(json)
            }
        }
    }

    private void loadMockedUser(JSONArray mockedUsers) {
        if(!mockedUsers) {
            return
        }
        mockedUsers.each { userData ->
            personService.createFromGeneric(userData)
        }
    }

    private void injectValidationMethods(genericClass) {
        if(genericClass == null) {
            return
        }

        genericClass.metaClass.checkParameter = { def condition, def msg, def code ->
            if(!condition) {
                throw new BadRequestException(msg.toString(), code.toString())
            }
        }

        genericClass.metaClass.checkFound = { def condition, def msg, def code ->
            if(!condition) {
                throw new NotFoundException(msg.toString(), code.toString())
            }
        }

        genericClass.metaClass.checkAuth = { def condition, def msg, def code ->
            if(!condition) {
                throw new UnauthorizedException(msg.toString(), code.toString())
            }
        }

        genericClass.metaClass.checkForbidden = { def condition, def msg, def code ->
            if(!condition) {
                throw new ForbiddenException(msg.toString(), code.toString())
            }
        }

        genericClass.metaClass.checkError = { def condition, def msg, def code ->
            if(!condition) {
                throw new APIException(msg.toString(), code.toString(), [])
            }
        }
    }
}
