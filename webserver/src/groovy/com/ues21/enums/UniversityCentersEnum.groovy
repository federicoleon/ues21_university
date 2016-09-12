package com.ues21.enums

public enum UniversityCentersEnum {
    
    NEW_CORDOBA(1, "Nueva Córdoba"),
    CAMPUS(2, "Campus")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    UniversityCentersEnum (int id, String type) {
        this.id = id
        this.type = type
    }

    static UniversityCentersEnum byId (int id) {
        def result = values().find { it.id == id }
        if(!result) {
            return NEW_CORDOBA
        }
        return result
    }
}
