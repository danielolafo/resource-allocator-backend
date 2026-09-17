package com.resourceallocator.backend.adapter.out.persistence.mapper;

import com.resourceallocator.backend.adapter.out.persistence.entity.ProjectJpaEntity;
import com.resourceallocator.backend.domain.model.Project;
import com.resourceallocator.backend.domain.model.ProjectTechnologyRequirement;

import java.util.List;

public final class ProjectMapper {

    private ProjectMapper() {
    }

    public static void populate(ProjectJpaEntity target, Project source) {
        target.setName(source.getName());
        target.setDescription(source.getDescription());
        target.setClient(source.getClient());
        target.setStatus(source.getStatus());
        target.setStartDate(source.getStartDate());
        target.setEndDate(source.getEndDate());
        target.setDailyRate(source.getDailyRate());
        target.setMaxEmployees(source.getMaxEmployees());
    }

    public static Project toDomain(ProjectJpaEntity jpa) {
        List<ProjectTechnologyRequirement> requirements = jpa.getRequiredTechnologies().stream()
                .map(r -> new ProjectTechnologyRequirement(
                        r.getId(),
                        r.getTechnology().getId(),
                        r.getMinLevel(),
                        r.getMinYearsExperience(),
                        r.getVersion(),
                        r.getCount()))
                .toList();

        Project project = new Project();
        project.setId(jpa.getId());
        project.setName(jpa.getName());
        project.setDescription(jpa.getDescription());
        project.setClient(jpa.getClient());
        project.setStatus(jpa.getStatus());
        project.setStartDate(jpa.getStartDate());
        project.setEndDate(jpa.getEndDate());
        project.setDailyRate(jpa.getDailyRate());
        project.setMaxEmployees(jpa.getMaxEmployees());
        project.setRequiredTechnologies(requirements);
        return project;
    }
}