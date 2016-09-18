package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject
import com.ues21.enums.ExamStatusEnum

import grails.transaction.Transactional

@Transactional
class StudentService extends PersonService {

    def subjectService
    def careerService
    
    public Student create(JSONObject data) {
        return super.createFromGeneric(data)
    }

    public List<Subject> getOnGoingSubjectsForStudent(Long studentId) {
        List<Subject> result = []
        if(!studentId) {
            return result
        }

        Student student = Student.get(studentId)
        if(!student) {
            return result
        }

        def cathedrasXStudent = CathedrasXStudents.withCriteria {
            eq("student", student)
            eq("status", 1)
        }
        cathedrasXStudent?.each { cxe ->
            Subject s = cxe.cathedra?.subject
            if(s) {
                result << s
            }
        }
        return result
    }

    public List<Subject> getAvailableSubjectsForStudent(Long careerId, Long studentId) {
        List <Subject> result = []
        
        def allSubjects = careerService.getAllSubjects(careerId)
        
        def approvedSubjects = getApprovedSubjectsForStudent(studentId)

        def onGoingSubjects = getOnGoingSubjectsForStudent(studentId)

        allSubjects.each { subject ->
            boolean canRegister = subjectService.canRegisterForSubject(subject, approvedSubjects, onGoingSubjects)
            if(canRegister) {
                result << subject
            }
        }
        return result
    }

    public List<Subject> getApprovedSubjectsForStudent(Long studentId) {
        List<Subject> result = []

        Student student = Student.get(studentId)
        if(!student) {
            return result
        }

        def approvedExams = ExamXStudent.withCriteria {
            "eq"("student", student)
            "in"("status", ExamStatusEnum.getApprovedStatus())
        }

        if(approvedExams) {
            approvedExams.each { ae ->
                Subject subject = ae.exam?.cathedra?.subject
                if(subject) {
                    result << subject
                }
            }
        }
        return result 
    }
}
