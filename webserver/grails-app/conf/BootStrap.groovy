import com.ues21.*
import com.ues21.enums.*
import com.ues21.exceptions.*
import org.codehaus.groovy.grails.web.json.JSONObject
import com.ues21.utils.JSONUtils

import org.codehaus.groovy.grails.web.context.ServletContextHolder

class BootStrap {

    def grailsApplication

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

            }
        }

        grailsApplication.getMainContext().getBean("customObjectMarshallers").register()
    }

    def destroy = {
    }

    private void loadDatabaseMocks() {
        loadIdentificationTypes()
        loadExamTypes()
        loadCareers()
    }

    private void loadIdentificationTypes() {
        IdentificationTypeEnum.values().each { identification ->
            IdentificationType type = new IdentificationType()
            type.type = identification.type()
            type.name = identification.value()
            type.save(flush: true, failOnError: true)
        }
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
            }
        }
    }

    private void loadCareerFromJson(JSONObject json) {
        Career career = new Career()
        career.name = json.name

        def plans = json.plans
        if(plans) {
            plans.each { pl ->
                CareerPlan plan = new CareerPlan()
                plan.name = pl.name
                def subjects = pl.subjects
                if(subjects) {
                    subjects.each { sbj ->
                        Subject subject = new Subject()
                        subject.name = sbj.name
                        subject.points = sbj.points
                        plan.addToSubjects(subject)
                    }
                }
                career.addToPlans(plan)
            }
        }
        if(career.validate()) {
            career.save(flush: true, failOnError: true)
        }else{
            String message = "Error when trying to create career '${career.name}': ${career.errors}".toString()
            throw new Exception(message)
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
