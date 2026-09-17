package com.resourceallocator.backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProficiencyLevel {
    BASICO("B\u00e1sico"),
    MEDIO("Medio"),
    AVANZADO("Avanzado"),
    EXPERTO("Experto");

    private final String label;

    ProficiencyLevel(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static ProficiencyLevel fromLabel(String label) {
        for (ProficiencyLevel level : values()) {
            if (level.label.equals(label) || level.name().equalsIgnoreCase(label)) {
                return level;
            }
        }
        throw new IllegalArgumentException("Nivel de proficiencia desconocido: " + label);
    }
}