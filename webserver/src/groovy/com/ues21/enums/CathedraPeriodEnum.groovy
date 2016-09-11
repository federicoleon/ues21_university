package com.ues21.enums

public enum CathedraPeriodEnum {
    
    FIRST_SEMESTER(1, "Primer semestre"),
    SECOND_SEMESTER(2, "Segundo semestre"),
    ANNUAL(3, "Anual"),
    SUMMER(4, "Materia de verano")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    CathedraPeriodEnum (int id, String type) {
        this.id = id
        this.type = type
    }

    static CathedraPeriodEnum byId (int id) {
        values().find { it.id == id }
    }
}
