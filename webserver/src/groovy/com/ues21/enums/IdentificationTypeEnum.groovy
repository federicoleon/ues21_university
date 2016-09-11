package com.ues21.enums

public enum IdentificationTypeEnum {
    
    DNI(1, "DNI", "Documento Nacional de Identidad")

    private final int id
    private final String type
    private final String value
    
    int id() { id }
    String type() { type }
    String value() { value }

    IdentificationTypeEnum (int id, String type, String value) {
        this.id = id
        this.type = type
        this.value = value
    }

    static IdentificationTypeEnum byId (int id) {
        values().find { it.id == id }
    }

    static IdentificationTypeEnum byType (String type) {
        values().find { it.type == type }
    }
}
