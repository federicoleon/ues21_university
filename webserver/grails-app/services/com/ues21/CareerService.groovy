package com.ues21

import grails.transaction.Transactional

@Transactional
class CareerService {

    public List getCareersModelView() {
        def careers = listAllCareers()
        def result = []
        careers?.each { career ->
            result << [
                id: career.id,
                name: career.name
            ]
        }
        return result
    }

    public List <Career> listAllCareers() {
        return Career.list(sort: "name", order: "asc")
    }

    public List<Subject> getAllSubjects(Long careerId) {
        List<Subject> result = []

        Career career = Career.get(careerId)
        if(!career) {
            return result
        }
        
        CareerPlan plan = CareerPlan.findByCareerAndStatus(career, 1)
        if(!plan) {
            return result
        }
        
        plan.subjects?.each { subject ->
            result << subject
        }
        return result
    }

    private List<Student> getStudentsFromCareer(Long careerId) {
        List result = []
        
        Career career = Career.get(careerId)
        if(!career) {
            return result
        }

        def careersXStudent = CareersXStudent.withCriteria {
            eq("career", career)
        }

        if(!careersXStudent) {
            return result
        }

        careersXStudent.each { careerXStudent ->
            result << careerXStudent.student
        }

        return result
    }

    public List getStudentsFromCareerMV(Long careerId) {
        List<Student> students = getStudentsFromCareer(careerId)
        List result = []
        students.each { student ->
            result << [
                id: student.id,
                full_name:  student.getFullName()
            ]
        }
        return result
    }
}
