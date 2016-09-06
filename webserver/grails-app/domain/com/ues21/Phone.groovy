package com.ues21

import com.ues21.enums.PhoneTypeEnum

class Phone {
    
    Integer type
    Integer areaCode
    Integer number
    Integer company

    static constraints = {
        type nullable: false, inList: PhoneTypeEnum.values()*.id()
        areaCode nullable: false
        number nullable: false
        company nullable: false
    }
}
