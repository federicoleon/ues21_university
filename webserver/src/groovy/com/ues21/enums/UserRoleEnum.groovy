package com.ues21.enums

public enum UserRoleEnum {
    
    TEACHER(1, "Docente", "ROLE_TEACHER"),
    STUDENT(2, "Alumno", "ROLE_STUDENT"),
    DIRECTOR(3, "Director", "ROLE_DIRECTOR"),
    SECRETARY(4, "Bedel", "ROLE_SECRETARY")

    private final int id
    private final String value
    private final String role
    
    int id() { id }
    String value() { value }
    String role() { role }

    UserRoleEnum (int id, String value, String role) {
        this.id = id
        this.value = value
        this.role = role
    }

    static UserRoleEnum byRole (String role) {
        if(!role) {
            return null
        }
        return values().find { it.role.equalsIgnoreCase(role.trim().toLowerCase()) }
    }

    static List listAll() {
        return [TEACHER, STUDENT, DIRECTOR, SECRETARY]
    }
}
