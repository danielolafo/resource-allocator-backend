package com.resourceallocator.backend.dto;

import com.resourceallocator.backend.entity.ProficiencyLevel;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public record ProjectTechnologyRequirementDto(
        @NotNull Long technologyId,
        @NotNull ProficiencyLevel minLevel,
        @Min(0) Integer minYearsExperience,
        String version,
        @Min(0) Integer count
) {}