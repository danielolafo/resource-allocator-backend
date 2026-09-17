package com.resourceallocator.backend.domain.model;

public enum AssignmentMode {
    HORAS("Horas"),
    DIAS("D\u00edas"),
    RANGO("Rango");

    private final String label;

    AssignmentMode(String label) {
        this.label = label;
    }

    public String label() {
        return label;
    }

    public static AssignmentMode fromLabel(String value) {
        for (AssignmentMode mode : values()) {
            if (mode.label.equals(value) || mode.name().equalsIgnoreCase(value)) {
                return mode;
            }
        }
        throw new IllegalArgumentException("Modo de asignaci\u00f3n desconocido: " + value);
    }
}