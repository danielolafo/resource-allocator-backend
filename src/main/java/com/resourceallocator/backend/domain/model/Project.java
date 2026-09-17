package com.resourceallocator.backend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Project {

    private Long id;
    private String name;
    private String description;
    private String client;
    private ProjectStatus status;
    private LocalDate startDate;
    private LocalDate endDate;
    private BigDecimal dailyRate;
    private List<ProjectTechnologyRequirement> requiredTechnologies = new ArrayList<>();

    public void addRequirement(ProjectTechnologyRequirement requirement) {
        requiredTechnologies.add(requirement);
    }
}