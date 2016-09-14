package com.ues21

import com.ues21.utils.*
import com.ues21.enums.*

class SecretaryController {

    def cathedraService
    def studentService
    def careerService
    
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
                student.save(flush: true, failOnError: true)
            }.to("processCareerRegistration")
        }

        processCareerRegistration {
            action {
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
                cxs.student = flow.student
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
                student: flow.student,
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
                def cathedras = Cathedra.list()
                def result = cathedraService.getCathedraModelView(cathedras)
                def model = [cathedras: result]
            }
            on("success").to("landing")
        }

        landing {

        }
    }

    def registerStudentCathedraFlow = {
        init {
            action {
                def students = Student.list()
                def cathedras = studentService.getAvailableCathedras()

                def model = [
                    students: students, 
                    cathedras: cathedraService.getCathedraModelView(cathedras)
                ]
            }
            on("success").to("landing")
        }

        landing {

        }
    }
}
