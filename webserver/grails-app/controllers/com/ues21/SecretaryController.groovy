package com.ues21

import com.ues21.utils.*
import com.ues21.enums.*

class SecretaryController {
    
    def registerStudentFlow = {
        
        init {
            action {
                def model = [
                    idTypes: IdentificationTypeEnum.values(),
                    phoneTypes: PhoneTypeEnum.values()
                ]
            }
            on("success").to("landing")
        }

        landing {
            on("register") {
                Student student = new Student()
                flow.student = student

                student.firstName = params.firstName
                student.lastName = params.lastName

                Identification identification = new Identification()
                identification.type = params.idType
                identification.number = params.idNumber
                identification.person = student
                student.identification = identification

                student.fileNumber = params.idNumber
                student.username = params.idNumber.toString().trim()
                student.password = StringUtils.getMD5(params.idNumber.toString().trim())

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
            }.to("processRegistration")
        }

        processRegistration() {
            action {
                def student = flow.student
                try {
                    if(student.validate()) {
                        student.save(flush: true, failOnError: true)
                        return success()
                    }else{
                        return error()
                    }
                } catch(Exception e) {
                    e.printStackTrace()
                    return error()
                }
                def model = [student: student]
            }
            on("success").to("success")
            on("error").to("error")
        }

        error {
        }

        success {
        }
    }

    def browseCathedrasFlow = {
        init {
            action {
                def cathedras = Cathedra.list()
                def result = []
                cathedras?.each { cathedra ->
                    result << [
                        subject_name: cathedra.subject.name,
                        classroom: cathedra.classroom.name,
                        center: UniversityCentersEnum.byId(cathedra.classroom.center).type(),
                        building: UniversityBuildingsEnum.byId(cathedra.classroom.building).type()
                    ]
                }
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
                def cathedras = Cathedra.list()

                def model = [students: students, cathedras: cathedras]
            }
            on("success").to("landing")
        }

        landing {

        }
    }
}
