package com.ues21.enums

public enum UniversityBuildingsEnum {
    
    SEDE_CENRAL_NEW_CORDOBA(1, "Edificio central"),
    SEDE_CENRAL_CAMPUS(2, "Edificio central")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    UniversityBuildingsEnum (int id, String type) {
        this.id = id
        this.type = type
    }

    static UniversityBuildingsEnum byId (int id) {
        def result = values().find { it.id == id }
        if(!result) {
            return SEDE_CENRAL_NEW_CORDOBA
        }
        return result
    }
}
