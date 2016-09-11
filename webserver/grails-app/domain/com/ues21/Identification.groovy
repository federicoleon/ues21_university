package com.ues21

import com.ues21.enums.IdentificationTypeEnum

class Identification {
    IdentificationType type
    String number

    static belongsTo = [student: Student]

    static constraints = {
        type nullable: false, blank: false
        number blank: false, nullable: false, minSize: 8, maxSize: 8
    }
}
