package com.ues21

import com.ues21.enums.PhoneTypeEnum

class Phone {

    Integer type
    Integer areaCode
    Integer number
    String company
    
    static belongsTo = [person: Person]

    static constraints = {
        type nullable: false, inList: PhoneTypeEnum.values()*.id()
        areaCode nullable: false
        number nullable: false
        company nullable: true, blank: true
    }
}
