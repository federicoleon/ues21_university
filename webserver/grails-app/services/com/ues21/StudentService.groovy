package com.ues21

import org.codehaus.groovy.grails.web.json.JSONObject
import com.ues21.enums.ExamStatusEnum
import com.ues21.utils.DateUtils
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

    public Map getStudentPublicMV(Long studentId) {
        if(!studentId) {
            return error()
        }
        Student student = Student.get(2)
        if(!student) {
            return error()
        }

        def allCareers = CareersXStudent.withCriteria {
            eq("status", 1)
            eq("student", student)
        }

        def careers = []
        allCareers?.each { cxe ->
            careers << [
                id: cxe.career.id,
                name: cxe.career.name
            ]
        }

        def model = [
            creationDate: DateUtils.getOnlyDateMV(student.creationDate),
            fileNumber: student.fileNumber,
            fullName: (student.lastName + " " + student.firstName),
            firstName: student.firstName,
            lastName: student.lastName,
            identification: [
                type: student.identification.type,
                number: student.identification.number
            ],
            vcardData: student.getVcardData(),
            email: student.emails?.getAt(0),
            phone: student.phones?.getAt(0),
            careers: careers
        ]

        return model
    }

    public boolean registerInExams(Long studentId, List<Long> examIds) {
        if(!examIds || !studentId) {
            return false
        }

        Student student = Student.get(studentId)
        if(!student) {
            return false
        }

        examIds.each { examId ->
            ExamXCathedra examXCathedra = ExamXCathedra.get(examId)
            if(!examXCathedra) {
                return false
            }
            ExamXStudent result = new ExamXStudent()
            result.student = student
            result.exam = examXCathedra
            result.status = 1
            if(!result.validate()) {
                return false
            }

            result.save(flush: true, failOnError: true)
        }

        return true
    }

    public List getRegisteredActiveExamsModelView(Long studentId) {
        List result = []
        Student student = Student.get(studentId)
        if(!student) {
            return result
        }

        def exams = ExamXStudent.withCriteria {
            eq("student", student)
            eq("status", 1)
        }

        exams?.each { exam ->
            result << [
                subject: exam.exam.cathedra.subject.name,
                exam_type: exam.exam.examType.type,
                status: exam.getExamStatusValue()
            ]
        }

        return result
    }
}
