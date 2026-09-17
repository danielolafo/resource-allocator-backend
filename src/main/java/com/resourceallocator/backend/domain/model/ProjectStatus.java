package com.resourceallocator.backend.domain.model;

public enum ProjectStatus {
    ACTIVO("Activo"),
    EN_PLANIFICACION("En planificaci\u00f3n"),
    FINALIZADO("Finalizado");

    private final String label;

    ProjectStatus(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static ProjectStatus fromLabel(String value) {
        for (ProjectStatus status : values()) {
            if (status.label.equals(value) || status.name().equalsIgnoreCase(value)) {
                return status;
            }
        }
        throw new IllegalArgumentException("Estado de proyecto desconocido: " + value);
    }
}