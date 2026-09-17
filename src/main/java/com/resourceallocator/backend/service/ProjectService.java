package com.resourceallocator.backend.service;

import com.resourceallocator.backend.dto.ProjectDto;
import com.resourceallocator.backend.dto.ProjectTechnologyRequirementDto;
import com.resourceallocator.backend.entity.Project;
import com.resourceallocator.backend.entity.ProjectTechnologyRequirement;
import com.resourceallocator.backend.entity.Technology;
import com.resourceallocator.backend.exception.BadRequestException;
import com.resourceallocator.backend.exception.ResourceNotFoundException;
import com.resourceallocator.backend.repository.ProjectRepository;
import com.resourceallocator.backend.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService {

    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;

    @Transactional(readOnly = true)
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public ProjectDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Transactional
    public ProjectDto create(ProjectDto dto) {
        Project project = new Project();
        apply(project, dto);
        return toDto(projectRepository.save(project));
    }

    @Transactional
    public ProjectDto update(Long id, ProjectDto dto) {
        Project project = getEntity(id);
        apply(project, dto);
        return toDto(projectRepository.save(project));
    }

    @Transactional
    public void delete(Long id) {
        projectRepository.delete(getEntity(id));
    }

    private Project getEntity(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));
    }

    private void apply(Project project, ProjectDto dto) {
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setClient(dto.client());
        project.setStatus(dto.status());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        project.setDailyRate(dto.dailyRate());

        project.getRequiredTechnologies().clear();
        if (dto.requiredTechnologies() != null) {
            for (ProjectTechnologyRequirementDto req : dto.requiredTechnologies()) {
                Technology technology = resolveTechnology(req.technologyId());
                project.addRequirement(new ProjectTechnologyRequirement(
                        technology,
                        req.minLevel(),
                        req.minYearsExperience(),
                        req.version(),
                        req.count()));
            }
        }
    }

    private Technology resolveTechnology(Long technologyId) {
        return technologyRepository.findById(technologyId)
                .orElseThrow(() -> new BadRequestException(
                        "Tecnolog\u00eda no encontrada con id " + technologyId));
    }

    private ProjectDto toDto(Project project) {
        List<ProjectTechnologyRequirementDto> requirements = project.getRequiredTechnologies().stream()
                .map(r -> new ProjectTechnologyRequirementDto(
                        r.getTechnology().getId(),
                        r.getMinLevel(),
                        r.getMinYearsExperience(),
                        r.getVersion(),
                        r.getCount()))
                .toList();

        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getClient(),
                project.getStatus(),
                project.getStartDate(),
                project.getEndDate(),
                project.getDailyRate(),
                requirements);
    }
}