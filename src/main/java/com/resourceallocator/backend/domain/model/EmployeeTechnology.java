package com.resourceallocator.backend.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeTechnology {

    private Long id;
    private Long technologyId;
    private ProficiencyLevel level;
    private String version;
    private Integer yearsExperience;
}