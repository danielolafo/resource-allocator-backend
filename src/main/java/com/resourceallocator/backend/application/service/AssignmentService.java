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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

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