package com.ues21

class Subject {

    String name
    int points
    int type
    int semester

    static hasMany = [correlatives: Subject]

    public int getAcademicYear() {
        switch(semester) {
            case [1, 2]:
                return 1;
            case [3, 4]:
                return 2;
            case [5, 6]:
                return 3;
            case [7, 8]:
                return 4;
            case [9, 10]:
                return 5;
            default:
                return 1;
        }
    }

    public int getPeriod() {
        if(semester % 2 == 0) {
            return 1
        }else{
            return 2
        }
    }
}
