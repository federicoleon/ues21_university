package com.ues21

import grails.transaction.Transactional
import com.ues21.enums.*

@Transactional
class CareerService {

    def cathedraService

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

    public Map getExamPeriodToOpen(Long careerId) {
        Career career = Career.get(careerId)
        if(!career) {
            return null
        }

        UniversityConfig config = UniversityConfig.findByCareer(career)
        if(!config) {
            return [
                career: career.id,
                type: ExamTypeEnum.FIRST.id(),
                name: ExamTypeEnum.FIRST.type()
            ]
        }

        switch(config.currentExamPeriod) {
            case ExamTypeEnum.FIRST.id():
                return [
                    career: career.id,
                    type: ExamTypeEnum.SECOND.id(),
                    name: ExamTypeEnum.SECOND.type()
                ]

            case ExamTypeEnum.SECOND.id():
                return [
                    career: career.id,
                    type: ExamTypeEnum.RETRY.id(),
                    name: ExamTypeEnum.RETRY.type()
                ]

            case ExamTypeEnum.RETRY.id():
                return [
                    career: career.id,
                    type: ExamTypeEnum.FINAL.id(),
                    name: ExamTypeEnum.FINAL.type()
                ]

            case ExamTypeEnum.FINAL.id():
                return null

            default:
                return null
        }
    }

    public boolean openExamPeriod(Long careerId) {
        Career career = Career.get(careerId)
        if(!career) {
            return false
        }

        Map examPeriodToOpen = getExamPeriodToOpen(careerId)
        if(!examPeriodToOpen) {
            return false
        }

        UniversityConfig config = UniversityConfig.findByCareer(career)
        if(!config) {
            config = new UniversityConfig()
            config.career = career
        }
        config.currentExamPeriod = examPeriodToOpen.type
        config.status = 1
        if(!config.validate()) {
            config.save(flush: true, failOnError: true)
            return false
        }

        List cathedras = cathedraService.getCathedrasForCareer(careerId)
        if(!cathedras) {
            return false
        }

        ExamType examType = ExamType.get(examPeriodToOpen.type)
        if(!examType) {
            return false
        }

        cathedras.each { cathedra ->
            ExamXCathedra exam = new ExamXCathedra()
            exam.cathedra = cathedra
            exam.examType = examType
            exam.save(flush: true, failOnError: true)
        }
    }

    public List getAvailableExamsMV(Long careerId, Long studentId) {
        Career career = Career.get(careerId)
        if(!career) {
            return []
        }

        List cathedras = cathedraService.getActiveCathedrasFromStudent(studentId)
        if(!cathedras) {
            return []
        }

        Student student = Student.get(studentId)
        if(!student) {
            return []
        }

        List result = []
        cathedras.each { cathedra ->
            def exams = ExamXCathedra.withCriteria {
                eq("cathedra", cathedra)
                eq("status", 1)
            }
            exams.each { exam ->
                result << [
                    exam_id: exam.id,
                    exam_type: exam.examType.type,
                    subject: cathedra.subject.name
                ]
            }
        }
        return result

    }
}
