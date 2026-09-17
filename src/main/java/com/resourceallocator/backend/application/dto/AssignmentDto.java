package com.resourceallocator.backend.application.dto;

import com.resourceallocator.backend.domain.model.AssignmentMode;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record AssignmentDto(
        Long id,
        @NotNull Long employeeId,
        @NotNull Long projectId,
        @NotNull AssignmentMode mode,
        @Min(1) Integer hoursPerDay,
        @NotNull LocalDate startDate,
        @NotNull LocalDate endDate,
        String notes
) {}