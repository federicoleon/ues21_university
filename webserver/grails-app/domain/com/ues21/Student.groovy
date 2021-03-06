package com.ues21

import com.ues21.enums.UserRoleEnum
import com.ues21.enums.PhoneTypeEnum

class Student extends Person implements Serializable {

    String fileNumber

    static mapping = {
        tablePerHierarchy false
    }

    static constraints = {
        fileNumber nullable: false, blank: false
    }

    public String getRole() {
        return UserRoleEnum.STUDENT.role()
    }

    public String getFullName() {
        return lastName.toUpperCase() + ", " + firstName.toUpperCase()
    }

    public transient String getVcardData() {
        StringBuilder vc = new StringBuilder()
        vc << "BEGIN:VCARD\n"
        vc << "VERSION:3.0\n"
        vc << "N:${lastName};${firstName};;;\n"
        emails?.each { email ->
            vc << "EMAIL;type=internet,pref:${email.address}\n"
        }
        vc << "ORG:Universidad Siglo 22\n"
        phones?.each { phone ->
            String completePhone = phone.areaCode + "" + phone.number
            if(PhoneTypeEnum.LINE.equals()) {
                vc << "TEL;HOME:${completePhone}\n"
            }else{
                vc << "TEL;CELL:${completePhone}\n"
            }
        }
        vc << "TITLE:${fileNumber}\n"
        vc << "REV:${creationDate.getTime()}\n"
        vc << "END:VCARD"
        return vc.toString()
    }
}
