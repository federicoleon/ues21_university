package com.ues21.enums

public enum UserTypeEnum {
    
    TEACHER(1, "Docente", "teacher"),
    STUDENT(2, "Alumno", "student"),
    DIRECTOR(3, "Director", "director"),
    SECRETARY(4, "Bedel", "secretary")

    private final int id
    private final String value
    private final String role
    
    int id() { id }
    String value() { value }
    String role() { role }

    UserTypeEnum (int id, String value, String role) {
        this.id = id
        this.value = value
        this.role = role
    }

    static UserTypeEnum byRole (String role) {
        if(!role) {
            return null
        }
        return values().find { it.role.equalsIgnoreCase(role.trim().toLowerCase()) }
    }
}
