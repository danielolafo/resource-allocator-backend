package com.resourceallocator.backend.dto;

import com.resourceallocator.backend.entity.ProficiencyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record EmployeeTechnologyDto(
        @NotNull Long technologyId,
        @NotNull ProficiencyLevel level,
        String version,
        @Min(0) Integer yearsExperience
) {}