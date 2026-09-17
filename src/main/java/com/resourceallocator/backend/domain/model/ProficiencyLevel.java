package com.resourceallocator.backend.domain.model;

public enum ProficiencyLevel {
    BASICO("B\u00e1sico"),
    MEDIO("Medio"),
    AVANZADO("Avanzado"),
    EXPERTO("Experto");

    private final String label;

    ProficiencyLevel(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static ProficiencyLevel fromLabel(String value) {
        for (ProficiencyLevel level : values()) {
            if (level.label.equals(value) || level.name().equalsIgnoreCase(value)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Nivel de proficiencia desconocido: " + value);
    }
}