package com.ues21

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class CareerController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Career.list(params), model:[careerInstanceCount: Career.count()]
    }

    def show(Career careerInstance) {
        respond careerInstance
    }

    def create() {
        respond new Career(params)
    }

    @Transactional
    def save(Career careerInstance) {
        if (careerInstance == null) {
            notFound()
            return
        }

        if (careerInstance.hasErrors()) {
            respond careerInstance.errors, view:'create'
            return
        }

        careerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'career.label', default: 'Career'), careerInstance.id])
                redirect careerInstance
            }
            '*' { respond careerInstance, [status: CREATED] }
        }
    }

    def edit(Career careerInstance) {
        respond careerInstance
    }

    @Transactional
    def update(Career careerInstance) {
        if (careerInstance == null) {
            notFound()
            return
        }

        if (careerInstance.hasErrors()) {
            respond careerInstance.errors, view:'edit'
            return
        }

        careerInstance.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'Career.label', default: 'Career'), careerInstance.id])
                redirect careerInstance
            }
            '*'{ respond careerInstance, [status: OK] }
        }
    }

    @Transactional
    def delete(Career careerInstance) {

        if (careerInstance == null) {
            notFound()
            return
        }

        careerInstance.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'Career.label', default: 'Career'), careerInstance.id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'career.label', default: 'Career'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
