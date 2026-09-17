package com.resourceallocator.backend.application.dto;

import com.resourceallocator.backend.domain.model.ProficiencyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EmployeeTechnologyDto(
        @NotNull Long technologyId,
        @NotNull ProficiencyLevel level,
        String version,
        @Min(0) Integer yearsExperience
) {}