package com.resourceallocator.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProjectTechnologyRequirement {

    private Long id;
    private Long technologyId;
    private ProficiencyLevel minLevel;
    private Integer minYearsExperience;
    private String version;
    private Integer count;
}