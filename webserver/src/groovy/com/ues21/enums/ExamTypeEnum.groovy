package com.ues21.enums

public enum ExamTypeEnum {
    
    FIRST(1, "Primer parcial"),
    SECOND(2, "Segundo parcial"),
    RETRY(3, "Recuperatorio"),
    FINAL(4, "Final")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    ExamTypeEnum (int id, String type) {
        this.id = id
        this.type = type
    }

    static ExamTypeEnum byId (int id) {
        values().find { it.id == id }
    }
}
