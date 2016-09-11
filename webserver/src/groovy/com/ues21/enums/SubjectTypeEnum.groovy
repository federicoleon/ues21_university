package com.ues21.enums

public enum SubjectTypeEnum {

    REGULAR(1, "Semestral"),
    VIRTUAL(2, "Anual")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    SubjectTypeEnum (int id, String type) {
        this.id = id
        this.type = type
    }

    static SubjectTypeEnum byId (int id) {
        values().find { it.id == id }
    }
}
