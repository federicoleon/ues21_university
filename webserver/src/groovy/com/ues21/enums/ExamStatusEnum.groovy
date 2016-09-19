package com.ues21.enums

public enum ExamStatusEnum {
    
    AVAILABLE(0, "Inscripto"),
    ABSENT(1, "Ausente"),
    FAILED(2, "Reprobado"),
    APPROVED(3, "Aprobado"),
    PROMOTED(4, "Promocionado")

    private final int id
    private final String type
    
    int id() { id }
    String type() { type }

    ExamStatusEnum(int id, String type) {
        this.id = id
        this.type = type
    }

    static ExamStatusEnum byId(int id) {
        return values().find { it.id == id }
    }

    static ExamStatusEnum byType(String type) {
        if(!type) {
            return null
        }
        return values().find { it.type.equalsIgnoreCase(type.trim()) }
    }

    public static int getExamStatusValue(float grade) {
        switch(grade) {
            case -1F:
                return ABSENT.id()

            case [0F..4F]:
                return FAILED.id()

            case [5F..6F]:
                return APPROVED.id()

            case [7F..10F]:
                return PROMOTED.id()
        }
    }

    public static List<Integer> getApprovedStatus() {
        return [
            APPROVED.id(),
            PROMOTED.id()
        ]
    }
}
