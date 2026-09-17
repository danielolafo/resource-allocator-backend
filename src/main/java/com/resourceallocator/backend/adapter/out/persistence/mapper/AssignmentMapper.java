package com.resourceallocator.backend.adapter.out.persistence.mapper;

import com.resourceallocator.backend.adapter.out.persistence.entity.AssignmentJpaEntity;
import com.resourceallocator.backend.domain.model.Assignment;

public final class AssignmentMapper {

    private AssignmentMapper() {
    }

    public static void populate(AssignmentJpaEntity target, Assignment source) {
        target.setMode(source.getMode());
        target.setHoursPerDay(source.getHoursPerDay());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setNotes(source.getNotes());
    }

    public static Assignment toDomain(AssignmentJpaEntity jpa) {
        Assignment assignment = new Assignment();
        assignment.setId(jpa.getId());
        assignment.setEmployeeId(jpa.getEmployee().getId());
        assignment.setProjectId(jpa.getProject().getId());
        assignment.setMode(jpa.getMode());
        assignment.setHoursPerDay(jpa.getHoursPerDay());
        assignment.setStartDate(jpa.getStartDate());
        assignment.setEndDate(jpa.getEndDate());
        assignment.setNotes(jpa.getNotes());
        return assignment;
    }
}