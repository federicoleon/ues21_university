package com.ues21

import com.ues21.utils.*
import com.ues21.enums.*
import com.ues21.constants.Constants
import org.codehaus.groovy.grails.web.context.ServletContextHolder

class SecretaryController {

    def cathedraService
    def studentService
    def careerService
    def subjectService
    def pdfRenderingService
    
    def registerStudentFlow = {
        
        init {
            action {
                Map model = [
                    careers: careerService.getCareersModelView(),
                    idTypes: IdentificationTypeEnum.values(),
                    phoneTypes: PhoneTypeEnum.values()
                ]
                return model
            }
            on("success").to("landing")
        }

        landing {
            on("register").to("processCareerRegistration")
        }

        processCareerRegistration {
            action {
                Student student = new Student()

                student.firstName = params.firstName
                student.lastName = params.lastName

                String idNumber = params.idNumber.toString().trim()
                Identification identification = new Identification()
                identification.type = params.int("idType")
                identification.number = idNumber
                identification.person = student
                student.identification = identification

                student.fileNumber = idNumber
                student.username = params.idNumber.toString().trim()
                student.password = StringUtils.getMD5(idNumber)

                Phone phone = new Phone()
                phone.type = params.int("phoneType")
                phone.areaCode = params.int("phoneAreaCode")
                phone.number = params.int("phoneNumber")
                phone.person = student
                student.addToPhones(phone)

                Email email = new Email()
                email.address = params.email
                email.person = student
                student.addToEmails(email)

                flow.student = student

                if(!student.validate()) {
                    return error()
                }

                // Register current student:
                def std = student.save(flush: true, failOnError: true)

                // Check for received careerId:
                List careerIds = params.list('carreerIds')
                if(!careerIds) {
                    return error()
                }

                careerIds?.each { careerId ->
                    Career career = Career.get(careerId)
                    if(!career) {
                        return error()
                    }
                    // If we have the career, then save the relation between student and career:
                    CareersXStudent cxs = new CareersXStudent()
                    cxs.career = career
                    cxs.student = student
                    if(cxs.validate()) {
                        cxs.save(flush: true, failOnError: true)
                    }else{
                        return error()
                    }
                }

                flow.studentId = std.id
            }
            on("success").to("savePDFReport")
            on("error").to("error")
        }

        error {
            action {
                return [
                    student: flow.student,
                    error: true,
                    error_msg: "Ha ocurrido un error al intentar registrar el usuario"
                ]
            }
            on("success").to("landing")
        }

        savePDFReport {
            action {
                def model = studentService.getStudentPublicMV(flow.studentId)
                try {

                    def bytes = model.vcardData.getBytes()
                    def data = bytes.encodeBase64().toString()
                    String path = ServletContextHolder.servletContext.getRealPath('qrs')
                    QRCodeService.createQRCodeAndLogoBase64(data, path, "registration_${studentId}.png".toString() )

                    /*
                    ByteArrayOutputStream bytes = pdfRenderingService.render(template: "/reports/student_registration", controller: this, model: model)
                    def fos = new FileOutputStream('NewTestFile.pdf')
                    fos.write(bytes.toByteArray())
                    fos.close()
                    */
                }catch(Exception e) {
                }
                return model
            }
            on("success").to("success")
        }

        success {

        }
    }

    def browseCathedrasFlow = {
        init {
            action {
                def careers = careerService.listAllCareers()
                return [careers: careers, cathedras: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findCathedras") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchCathedras")
        }

        searchCathedras {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                List cathedras = cathedraService.getCathedrasForCareer(careerId)
                if(!cathedras) {
                    return error()
                }
                def cathedrasMV = cathedraService.getCathedrasModelView(cathedras)
                
                def careers = careerService.listAllCareers()
                return [careers: careers, cathedras: cathedrasMV]
            }
            on("success").to("landing")
        }

        error {
            action {
            }
            on("success").to("landing")
        }
    }

    def browseCorrelativesFlow = {
        init {
            action {
                def careers = careerService.listAllCareers()
                return [careers: careers, subjects: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findCareer") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchCorrelatives")
        }

        searchCorrelatives {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                List correlativesMV = subjectService.getCorrelativesModelView(careerId)
                if(!correlativesMV) {
                    return error()
                }
                def careers = careerService.listAllCareers()
                return [careers: careers, subjects: correlativesMV]
            }
            on("success").to("landing")
        }

        error {
            action {
            }
            on("success").to("landing")
        }
    }

    def studentCathedraRegistrationFlow = {
        init {
            action {
                def careers = careerService.getCareersModelView()
                flow.careers = careers
                return [careers: flow.careers, students: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findStudents") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchStudents")

            on("findAvailableCathedras") {
                Long studentId = params.long("studentId")
                flow.studentId = studentId
            }.to("searchCathedras")

            on("confirmRegistration") {
                List selectedCathedras = params.list('cat_selection')
                flow.cathedrasSelection = selectedCathedras
            }.to("processRegistration")
        }

        searchStudents {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                def students = careerService.getStudentsFromCareerMV(careerId)
                if(!students) {
                    students = null
                }
                flow.students = students
                return [careers: flow.careers, students: flow.students]
            }
            on("success").to("landing")
        }

        searchCathedras {
            action {
                Long studentId = flow.studentId
                Long careerId = flow.careerId
                if(!careerId || !studentId) {
                    flow.errorMessage = "Datos inválidos. Se ha notificado al administrador."
                    return error()
                }

                List currentRegisteredCathedras = cathedraService.getActiveCathedrasFromStudent(studentId)
                int currentSubjects = currentRegisteredCathedras.size()
                if(currentSubjects >= Constants.MAX_CONCURRENT_SUBJECTS) {
                    flow.errorClass = Constants.CLASS_INFO
                    flow.errorMessage = "El alumno ya se encuentra inscripto en "+currentSubjects+" materias y es el máximo permitido."
                    return error()
                }

                if(!flow.cathedras) {
                    List cathedras = cathedraService.getAvailableCathedrasForStudent(careerId, studentId)
                    flow.cathedras = cathedraService.getCathedrasModelView(cathedras)
                }
                
                return [careers: flow.careers, students: flow.students, cathedras: flow.cathedras]
            }
            on("success").to("landing")
            on("error").to("error")
        }

        processRegistration {
            action {
                List cathedras = flow.cathedrasSelection
                List<Long> ids
                try {
                    ids = cathedras.collect {it as Long}
                } catch(Exception e) {
                    return error()
                }
                
                if(!ids) {
                    flow.errorMessage = "Debe seleccionar al menos una materia para la inscripción."
                    flow.errorClass = Constants.CLASS_INFO
                    return error()
                }

                if(ids.size() > Constants.MAX_CONCURRENT_SUBJECTS) {
                    flow.errorMessage = "No se puede inscribir en más de " + Constants.MAX_CONCURRENT_SUBJECTS + " materias."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }

                Long studentId = flow.studentId

                List currentRegisteredCathedras = cathedraService.getActiveCathedrasFromStudent(studentId)

                int currentSubjects = currentRegisteredCathedras.size()
                int totalSubjects = currentSubjects + ids.size()
                int remainingSubjects = Constants.MAX_CONCURRENT_SUBJECTS - currentSubjects

                if(totalSubjects > Constants.MAX_CONCURRENT_SUBJECTS) {
                    flow.errorMessage = "El alumno ya se encuentra inscripto en "+currentSubjects+" materias. Sólo se puede inscribir en  "+remainingSubjects+" más."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }

                boolean result = cathedraService.registerStudentInCathedras(studentId, ids)
                if(!result) {
                    flow.errorMessage = "Ocurrió un error al intentar procesar la inscripción a cursado. Reintente más tarde"
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }
            }
            on("success").to("confirmation")
            on("error").to("error")
        }

        confirmation {

        }

        error {
            action {

                def model = [
                    careers: flow.careers,
                    students: flow.students,
                    cathedras: flow.cathedras,
                    errorMessage: flow.errorMessage,
                    errorClass: flow.errorClass
                ]

                flow.errorMessage = null
                flow.errorClass = null

                return model
            }
            on("success").to("landing")
        }
    }

    def browseCourseRegistrationFlow = {
        init {
            action {
                def careers = careerService.getCareersModelView()
                flow.careers = careers
                return [careers: flow.careers, students: [], cathedras: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findCathedras") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchStudents")

            on("findRegistrations") {
                Long studentId = params.long("studentId")
                flow.studentId = studentId
            }.to("searchRegistrations")
        }

        searchStudents {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                def students = careerService.getStudentsFromCareerMV(careerId)
                if(!students) {
                    students = null
                }
                flow.students = students
                return [careers: flow.careers, students: flow.students, cathedras: []]
            }
            on("success").to("landing")
        }

        searchRegistrations {
            action {
                Long studentId = flow.studentId
                List cathedras = cathedraService.getActiveCathedrasFromStudent(studentId)
                List result = cathedraService.getCathedrasModelView(cathedras)
                if(!result) {
                    result = null
                }
                return [careers: flow.careers, students: flow.students, cathedras: result]
            }
            on("success").to("landing")
        }
    }

    def browseAbsencesFlow = {
        init {
            action {
                def careers = careerService.getCareersModelView()
                flow.careers = careers
                return [careers: flow.careers, students: [], absences: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findCathedras") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchStudents")

            on("findAbsences") {
                Long studentId = params.long("studentId")
                flow.studentId = studentId
            }.to("searchAbsences")
        }

        searchStudents {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                def students = careerService.getStudentsFromCareerMV(careerId)
                if(!students) {
                    students = null
                }
                flow.students = students
                return [careers: flow.careers, students: flow.students, absences: []]
            }
            on("success").to("landing")
        }

        searchAbsences {
            action {
                Long studentId = flow.studentId
                Map absences = cathedraService.getCurrentAbsencesForStudentMV(studentId)
                if(!absences) {
                    absences = null
                }
                return [careers: flow.careers, students: flow.students, absences: absences]
            }
            on("success").to("landing")
        }
    }

    def enableTestsPeriodFlow = {
        init {
            action {
                def careers = careerService.getCareersModelView()
                flow.careers = careers
                return [careers: flow.careers, students: [], absences: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findCathedras") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchNextExamPeriod")

            on("enablePeriod") {
                int examPeriodId = params.int("examPeriodId")
                flow.examPeriodId = examPeriodId
            }.to("processEnableExamPeriod")
        }

        searchNextExamPeriod {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                
                Map examPeriodToOpen = careerService.getExamPeriodToOpen(careerId)
                flow.examPeriodToOpen = examPeriodToOpen
                
                return [careers: flow.careers, examPeriod: examPeriodToOpen]
            }
            on("success").to("landing")
        }

        processEnableExamPeriod {
            action {
                int examPeriodId = flow.examPeriodId
                if(!examPeriodId) {
                    flow.errorMessage = "No se ha seleccionado ningún periodo de examen para habilitar."
                    return error()
                }

                Map savedPeriod = flow.examPeriodToOpen
                if(!savedPeriod) {
                    flow.errorMessage = "No se ha seleccionado ningún periodo de examen para habilitar."
                    return error()
                }

                if(examPeriodId != savedPeriod.type) {
                    flow.errorMessage = "Se ha modificado el formulario sin autorización. El administrador ha sido notificado de este evento."
                    return error()
                }

                Long careerId = flow.careerId

                def opened = careerService.openExamPeriod(careerId)
                if(!opened) {
                    flow.errorMessage = "Ha ocurrido un error al intentar habilitar el periodo de examen seleccionado."
                    error()
                }
            }
            on("error").to("error")
            on("success").to("finish")
        }

        error {
            action {
                return [
                    errorMessage: flow.errorMessage
                ]
            }
            on("success").to("landing")
        }

        finish {}
    }

    def registerStudentInExamFlow = {
        init {
            action {
                def careers = careerService.getCareersModelView()
                flow.careers = careers
                return [careers: flow.careers, students: [], exams: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findStudents") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchStudents")

            on("findAvailableExams") {
                Long studentId = params.long("studentId")
                flow.studentId = studentId
            }.to("searchAvailableExams")

            on("confirmRegistration") {
                List selectedExams = params.list('exam_selection')
                flow.selectedExams = selectedExams
            }.to("processRegistration")
        }

        searchStudents {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                def students = careerService.getStudentsFromCareerMV(careerId)
                if(!students) {
                    students = null
                }
                flow.students = students
                return [careers: flow.careers, students: flow.students, exams: []]
            }
            on("success").to("landing")
        }

        searchAvailableExams {
            action {
                Long studentId = flow.studentId
                Long careerId = flow.careerId
                if(!careerId || !studentId) {
                    flow.errorMessage = "Datos inválidos. Se ha notificado al administrador."
                    return error()
                }

                def exams = careerService.getAvailableExamsMV(careerId, studentId)
                if(!exams) {
                    exams = null
                }
                return [careers: flow.careers, students: flow.students, exams: exams]
            }
            on("success").to("landing")
            on("error").to("error")
        }

        processRegistration {
            action {
                List<Long> examIds
                try {
                    examIds = flow.selectedExams?.collect {it as Long}
                } catch(Exception e) {
                    flow.errorMessage = "Se ha modificado el formulario de forma no autorizada. El administrador ha sido notificado."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }
                if(!examIds) {
                    flow.errorMessage = "Debe seleccionar al menos una mesa de examen."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }

                Long studentId = flow.studentId
                if(!studentId) {
                    flow.errorMessage = "No se encuentra el alumno seleccionado. Se ha notificado al administrador."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }

                boolean registered = studentService.registerInExams(studentId, examIds)
                if(!registered) {
                    flow.errorMessage = "Ha ocurrido un error al intentar inscribir al alumno en las mesas de examen seleccionadas. Intente nuevamente más tarde."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }
            }
            on("error").to("error")
            on("success").to("finish")
        }

        error {
            action {
                return []
            }
            on("success").to("landing")
        }

        finish {}
    }

    def browseExamRegistrationFlow = {
        init {
            action {
                def careers = careerService.getCareersModelView()
                flow.careers = careers
                return [careers: flow.careers, students: [], exams: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findCathedras") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("searchStudents")

            on("findRegistrations") {
                Long studentId = params.long("studentId")
                flow.studentId = studentId
            }.to("searchExamRegistrations")
        }

        searchStudents {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                def students = careerService.getStudentsFromCareerMV(careerId)
                if(!students) {
                    students = null
                }
                flow.students = students
                return [careers: flow.careers, students: flow.students, exams: []]
            }
            on("success").to("landing")
        }

        searchExamRegistrations {
            action {
                Long studentId = flow.studentId
                List result = studentService.getRegisteredActiveExamsModelView(studentId)
                if(!result) {
                    result = null
                }
                return [careers: flow.careers, students: flow.students, exams: result]
            }
            on("success").to("landing")
        }
    }

    def registerStudentInCareerFlow = {
        init {
            action {
                def students = studentService.getAllStudents()
                flow.students = students
                return [students: flow.students, careers: []]
            }
            on("success").to("landing")
        }

        landing {
            on("findAvailableCareers") {
                Long studentId = params.long("studentId")
                flow.studentId = studentId
            }.to("searchAvailableCareers")

            on("confirmRegistration") {
                Long careerId = params.long("careerId")
                flow.careerId = careerId
            }.to("processRegistration")
        }

        searchAvailableCareers {
            action {
                Long studentId = flow.studentId
                if(!studentId) {
                    flow.errorMessage = "No se encuentra el alumno seleccionado. Se ha notificado al administrador."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }
                def careers = studentService.getAvailableCareers(studentId)
                
                flow.careers = careers
                return [students: flow.students, careers: flow.careers]
            }
            on("success").to("landing")
        }

        processRegistration {
            action {
                Long studentId = flow.studentId
                if(!studentId) {
                    flow.errorMessage = "No se encuentra el alumno seleccionado. Se ha notificado al administrador."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }

                Long careerId = flow.careerId
                if(!careerId) {
                    flow.errorMessage = "No se encuentra la carrera seleccionada. Se ha notificado al administrador."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }

                boolean registered = studentService.registerInCareer(studentId, careerId)
                if(!registered) {
                    flow.errorMessage = "Ha ocurrido un error al intentar inscribir al alumno en la carrera seleccionada. Intente nuevamente más tarde."
                    flow.errorClass = Constants.CLASS_ERROR
                    return error()
                }
            }
            on("error").to("error")
            on("success").to("finish")
        }

        error {
            action {
                return []
            }
            on("success").to("landing")
        }

        finish {}
    }
}
