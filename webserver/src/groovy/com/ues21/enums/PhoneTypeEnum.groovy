package com.ues21.enums

public enum PhoneTypeEnum {
    
    LINE(1, "Fijo"),
    MOBILE(2, "Celular")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    PhoneTypeEnum (int id, String type) {
        this.id = id
        this.type = type
    }

    static PhoneTypeEnum byId (int id) {
        values().find { it.id == id }
    }

    static PhoneTypeEnum byType(String type) {
        if(type == null) {
            return null
        }
        return values().find { it.type.equalsIgnoreCase(type)}
    }
}
