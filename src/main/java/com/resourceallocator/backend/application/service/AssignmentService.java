package com.resourceallocator.backend.application.service;

import com.resourceallocator.backend.application.dto.AssignmentDto;
import com.resourceallocator.backend.application.exception.BadRequestException;
import com.resourceallocator.backend.application.exception.ResourceNotFoundException;
import com.resourceallocator.backend.application.port.in.AssignmentUseCase;
import com.resourceallocator.backend.application.port.out.AssignmentRepository;
import com.resourceallocator.backend.application.port.out.EmployeeRepository;
import com.resourceallocator.backend.application.port.out.ProjectRepository;
import com.resourceallocator.backend.domain.model.Assignment;
import com.resourceallocator.backend.domain.model.AssignmentMode;
import com.resourceallocator.backend.domain.model.Project;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AssignmentService implements AssignmentUseCase {

    private final AssignmentRepository assignmentRepository;
    private final EmployeeRepository employeeRepository;
    private final ProjectRepository projectRepository;

    @Override
    public List<AssignmentDto> findAll() {
        return assignmentRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public AssignmentDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Override
    public AssignmentDto create(AssignmentDto dto) {
        requireProject(dto.projectId());
        validateCapacity(dto.projectId());
        Assignment assignment = toDomain(dto);
        assignment.setId(null);
        return toDto(assignmentRepository.save(assignment));
    }

    @Override
    public AssignmentDto update(Long id, AssignmentDto dto) {
        getEntity(id);
        Assignment assignment = toDomain(dto);
        assignment.setId(id);
        return toDto(assignmentRepository.save(assignment));
    }

    @Override
    public void delete(Long id) {
        getEntity(id);
        assignmentRepository.deleteById(id);
    }

    private Assignment getEntity(Long id) {
        return assignmentRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Asignaci\u00f3n no encontrada con id " + id));
    }

    private Assignment toDomain(AssignmentDto dto) {
        requireEmployee(dto.employeeId());
        requireProject(dto.projectId());
        if (dto.mode() == AssignmentMode.HORAS && dto.hoursPerDay() == null) {
            throw new BadRequestException("hoursPerDay es obligatorio para el modo HORAS");
        }
        Assignment assignment = new Assignment();
        assignment.setEmployeeId(dto.employeeId());
        assignment.setProjectId(dto.projectId());
        assignment.setMode(dto.mode());
        assignment.setHoursPerDay(dto.hoursPerDay());
        assignment.setStartDate(dto.startDate());
        assignment.setEndDate(dto.endDate());
        assignment.setNotes(dto.notes());
        return assignment;
    }

    private void requireEmployee(Long employeeId) {
        if (employeeRepository.findById(employeeId).isEmpty()) {
            throw new BadRequestException("Empleado no encontrado con id " + employeeId);
        }
    }

    private void requireProject(Long projectId) {
        if (projectRepository.findById(projectId).isEmpty()) {
            throw new BadRequestException("Proyecto no encontrado con id " + projectId);
        }
    }

    private void validateCapacity(Long projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new BadRequestException("Proyecto no encontrado con id " + projectId));
        if (project.getMaxEmployees() == null) {
            return;
        }
        long assigned = assignmentRepository.countActiveEmployees(projectId, LocalDate.now());
        if (assigned >= project.getMaxEmployees()) {
            throw new BadRequestException(
                    "El proyecto ha alcanzado su capacidad m\u00e1xima de " + project.getMaxEmployees()
                            + " empleado(s) y no est\u00e1 buscando m\u00e1s empleados.");
        }
    }

    private AssignmentDto toDto(Assignment assignment) {
        return new AssignmentDto(
                assignment.getId(),
                assignment.getEmployeeId(),
                assignment.getProjectId(),
                assignment.getMode(),
                assignment.getHoursPerDay(),
                assignment.getStartDate(),
                assignment.getEndDate(),
                assignment.getNotes());
    }
}