package com.resourceallocator.backend.entity;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

public enum ProjectStatus {
    ACTIVO("Activo"),
    EN_PLANIFICACION("En planificaci\u00f3n"),
    FINALIZADO("Finalizado");

    private final String label;

    ProjectStatus(String label) {
        this.label = label;
    }

    @JsonValue
    public String getLabel() {
        return label;
    }

    @JsonCreator
    public static ProjectStatus fromLabel(String label) {
        for (ProjectStatus status : values()) {
            if (status.label.equals(label) || status.name().equalsIgnoreCase(label)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estado de proyecto desconocido: " + label);
    }
}