package com.ues21

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional
import com.ues21.enums.*

@Transactional(readOnly = true)
class StudentController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Student.list(params), model:[studentInstanceCount: Student.count()]
    }

    def show(Student studentInstance) {
        respond studentInstance
    }

    def create() {
        def model = [
            identification_types: IdentificationType.list(),
            careers: Career.list()
        ]
    }

    @Transactional
    def save() {
        Student student = new Student()

        bindData(student, params)

        IdentificationType idType = IdentificationType.findByType(params.identificationType)
        
        println "======================================================================"
        println params.dump()
        println "======================================================================"

        Identification identification = new Identification()
        identification.type = idType
        identification.number = params.identificationNumber

        student.identification = identification

        student.username = params.identificationNumber.toString()
        student.password = params.identificationNumber.toString()

        if(!student.validate()) {
            respond student.errors, view:'create'
            return
        }

        student.save(flush: true, failOnError: true)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'student.label', default: 'Student'), student.id])
                redirect student
            }
            '*' { respond student, [status: CREATED] }
        }
    }

    def edit(Student studentInstance) {
        respond studentInstance
    }

    @Transactional
    def update(Student studentInstance) {
        if (studentInstance == null) {
            notFound()
            return
        }

        if (studentInstance.hasErrors()) {
            respond studentInstance.errors, view:'edit'
            return
        }

        studentInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Student.label', default: 'Student'), studentInstance.id])
                redirect studentInstance
            }
            '*'{ respond studentInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Student studentInstance) {

        if (studentInstance == null) {
            notFound()
            return
        }

        studentInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Student.label', default: 'Student'), studentInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'student.label', default: 'Student'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
