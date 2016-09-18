package com.ues21

import com.ues21.utils.*
import com.ues21.enums.*

class SecretaryController {

    def cathedraService
    def studentService
    def careerService
    def subjectService
    
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
            on("register") {
            }.to("processCareerRegistration")
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

                if(!student.validate()) {
                    return error()
                }

                // Register current student:
                student.save(flush: true, failOnError: true)

                // Check for received careerId:
                Long careerId = params.long("carreerId")
                if(!careerId) {
                    return error()
                }

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
            on("success").to("success")
            on("error").to("error")
        }

        error {
            return [
                error: true,
                error_msg: "Ha ocurrido un error al intentar registrar el usuario"
            ]
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
                def cathedrasMV = cathedraService.getCathedraModelView(cathedras)
                
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
                def careers = careerService.listAllCareers()
                return [careers: careers, students: []]
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
                flow.selectedCathedras = selectedCathedras
            }.to("validateRegistration")
        }

        searchStudents {
            action {
                Long careerId = flow.careerId
                if(!careerId) {
                    return error()
                }
                def students = careerService.getStudentsFromCareerMV(careerId)
                def careers = careerService.getCareersModelView()
                return [careers: careers, students: students]
            }
            on("success").to("landing")
        }

        searchCathedras {
            action {
                Long studentId = flow.studentId
                Long careerId = flow.careerId
                if(!careerId || !studentId) {
                    return error()
                }
                List students = careerService.getStudentsFromCareerMV(careerId)
                List careers = careerService.getCareersModelView()
                List cathedras = cathedraService.getAvailableCathedrasForStudent(careerId, studentId)
                List cathedrasMV = cathedraService.getCathedraModelView(cathedras)
                return [careers: careers, students: students, cathedras: cathedrasMV]
            }
            on("success").to("landing")
        }

        validateRegistration {
            action {
                Long studentId = flow.studentId

                List selection = flow.selectedCathedras

                List<Long> cathedraIDs
                try {
                    cathedraIDs = selection.collect {
                        it as Long
                    }
                } catch(Exception e) {
                    return error()
                }
                
                if(!cathedraIDs) {
                    return error()
                }

                //boolean result = cathedraService.processRegistrationInCathedras(studentId, cathedraIDs)
                def result = true
                if(!result) {
                    return error()
                }
            }
            on("error").to("error")
            on("success").to("confirmation")
        }

        error {

        }

        confirmation {

        }
    }
}
