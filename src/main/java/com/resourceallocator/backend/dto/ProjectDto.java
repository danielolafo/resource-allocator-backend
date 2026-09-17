package com.resourceallocator.backend.dto;

import com.resourceallocator.backend.entity.ProjectStatus;
import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record ProjectDto(
        Long id,
        @NotBlank String name,
        String description,
        @NotBlank String client,
        @NotNull ProjectStatus status,
        LocalDate startDate,
        LocalDate endDate,
        @DecimalMin("0.0") BigDecimal dailyRate,
        @Valid List<ProjectTechnologyRequirementDto> requiredTechnologies
) {}