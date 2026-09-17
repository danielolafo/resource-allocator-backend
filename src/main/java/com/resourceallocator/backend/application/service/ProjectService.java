package com.resourceallocator.backend.application.service;

import com.resourceallocator.backend.application.dto.ProjectDto;
import com.resourceallocator.backend.application.dto.ProjectTechnologyRequirementDto;
import com.resourceallocator.backend.application.exception.BadRequestException;
import com.resourceallocator.backend.application.exception.ResourceNotFoundException;
import com.resourceallocator.backend.application.port.in.ProjectUseCase;
import com.resourceallocator.backend.application.port.out.AssignmentRepository;
import com.resourceallocator.backend.application.port.out.ProjectRepository;
import com.resourceallocator.backend.application.port.out.TechnologyRepository;
import com.resourceallocator.backend.domain.model.Project;
import com.resourceallocator.backend.domain.model.ProjectTechnologyRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProjectService implements ProjectUseCase {

    private final ProjectRepository projectRepository;
    private final TechnologyRepository technologyRepository;
    private final AssignmentRepository assignmentRepository;

    @Override
    public List<ProjectDto> findAll() {
        return projectRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public ProjectDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Override
    public ProjectDto create(ProjectDto dto) {
        Project project = toDomain(dto);
        project.setId(null);
        return toDto(projectRepository.save(project));
    }

    @Override
    public ProjectDto update(Long id, ProjectDto dto) {
        getEntity(id);
        Project project = toDomain(dto);
        project.setId(id);
        return toDto(projectRepository.save(project));
    }

    @Override
    public void delete(Long id) {
        getEntity(id);
        projectRepository.deleteById(id);
    }

    private Project getEntity(Long id) {
        return projectRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Proyecto no encontrado con id " + id));
    }

    private Project toDomain(ProjectDto dto) {
        Project project = new Project();
        project.setName(dto.name());
        project.setDescription(dto.description());
        project.setClient(dto.client());
        project.setStatus(dto.status());
        project.setStartDate(dto.startDate());
        project.setEndDate(dto.endDate());
        project.setDailyRate(dto.dailyRate());
        project.setMaxEmployees(dto.maxEmployees());
        if (dto.requiredTechnologies() != null) {
            for (ProjectTechnologyRequirementDto req : dto.requiredTechnologies()) {
                requireTechnology(req.technologyId());
                project.addRequirement(new ProjectTechnologyRequirement(
                        null,
                        req.technologyId(),
                        req.minLevel(),
                        req.minYearsExperience(),
                        req.version(),
                        req.count()));
            }
        }
        return project;
    }

    private void requireTechnology(Long technologyId) {
        if (technologyRepository.findById(technologyId).isEmpty()) {
            throw new BadRequestException("Tecnolog\u00eda no encontrada con id " + technologyId);
        }
    }

    private ProjectDto toDto(Project project) {
        List<ProjectTechnologyRequirementDto> requirements = project.getRequiredTechnologies().stream()
                .map(r -> new ProjectTechnologyRequirementDto(
                        r.getTechnologyId(),
                        r.getMinLevel(),
                        r.getMinYearsExperience(),
                        r.getVersion(),
                        r.getCount()))
                .toList();

        Integer maxEmployees = project.getMaxEmployees();
        long assignedEmployees = assignmentRepository.countActiveEmployees(project.getId(), LocalDate.now());
        boolean seekingEmployees = maxEmployees == null || assignedEmployees < maxEmployees;

        return new ProjectDto(
                project.getId(),
                project.getName(),
                project.getDescription(),
                project.getClient(),
                project.getStatus(),
                project.getStartDate(),
                project.getEndDate(),
                project.getDailyRate(),
                maxEmployees,
                (int) assignedEmployees,
                seekingEmployees,
                requirements);
    }
}