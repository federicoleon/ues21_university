package com.ues21

import com.ues21.enums.IdentificationTypeEnum

class Identification implements Serializable {
    int type
    String number

    static belongsTo = [person: Person]

    static constraints = {
        type(nullable: false, blank: false)
        number(blank: false, nullable: false, unique: ["type"])
    }
}
