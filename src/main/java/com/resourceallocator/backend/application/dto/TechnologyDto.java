package com.resourceallocator.backend.application.dto;

import jakarta.validation.constraints.NotBlank;

public record TechnologyDto(
        Long id,
        @NotBlank String name,
        String category,
        String version,
        String description
) {}