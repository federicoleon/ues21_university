package com.ues21

import com.ues21.enums.*

import grails.transaction.Transactional

@Transactional
class CathedraService {

    def careerService
    def studentService
    
    public List getCathedraModelView(List<Cathedra> cathedras) {
        List result = []
        cathedras?.each { cathedra ->
            result << [
                id: cathedra.id,
                subject_name: cathedra.subject.name,
                subject_academic_year: cathedra.subject.getAcademicYear(),
                subject_semester: cathedra.subject.semester,
                classroom: cathedra.classroom.name,
                center: UniversityCentersEnum.byId(cathedra.classroom.center).type(),
                building: UniversityBuildingsEnum.byId(cathedra.classroom.building).type()
            ]
        }
        return result
    }

    public List getCathedrasForCareer(Long careerId) {
        List<Subject> subjects = careerService.getAllSubjects(careerId)
        def cathedras = Cathedra.withCriteria {
            "in" ("subject", subjects)
        }
        List<Cathedra> result = []
        cathedras?.each { cathedra ->
            if(cathedra) {
                result << cathedra
            }
        }
        return result
    }

    public List getAvailableCathedrasForStudent(Long careerId, Long studentId) {
        List<Subject> availableSubjects = studentService.getAvailableSubjectsForStudent(careerId, studentId)
        return getAvailableCathedrasForSubjects(availableSubjects)
    }

    public List getAvailableCathedrasForSubjects(List<Subject> subjects) {
        List<Cathedra> result = []
        if(!subjects) {
            return result
        }
        def cathedras = Cathedra.withCriteria {
            "in" ("subject", subjects)
        }
        cathedras?.each { cathedra ->
            if(cathedra) {
                result << cathedra
            }
        }
        return result
    }

    public boolean processRegistrationInCathedras(Long studentId, List<Long> cathedraIds) {
        if(!studentId || !cathedraIds) {
            return false
        }

        Student student = Student.get(studentId)
        if(!student) {
            return error()
        }

        List cathedras = Cathedra.getAll(cathedraIds)
        if(!cathedras) {
            return false
        }

        Person person = Person.get(session.person.id)
        if(!person) {
            return false
        }

        cathedras.each { cathedra ->
            try {
                CathedrasXStudents cxe = new CathedrasXStudents()
                cxe.cathedra = cathedra
                cxe.student = student
                cxe.taskExecutor = person
                if(!cxe.validate()) {
                    return false
                }
                cxe.save(flush: true, failOnError: true)
            } catch(Exception e) {
                return false
            }
        }

        return true
    }
}
