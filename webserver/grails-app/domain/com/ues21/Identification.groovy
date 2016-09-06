package com.ues21

import com.ues21.enums.IdentificationTypeEnum

class Identification {
    IdentificationType type
    String number

    static constraints = {
        type nullable: false, inList: IdentificationTypeEnum.values()*.value()
        number blank: false, nullable: false, minSize: 13, maxSize: 20
    }
}
