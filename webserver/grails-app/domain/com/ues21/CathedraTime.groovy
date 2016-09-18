package com.ues21

class CathedraTime implements Serializable {

    Integer day = 0
    Date start
    Date end

    static mapping = {
        day(nullable: false)
        start(nullable: false)
        end(nullable: false)
    }
}
