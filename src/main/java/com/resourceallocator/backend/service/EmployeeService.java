package com.resourceallocator.backend.service;

import com.resourceallocator.backend.dto.EmployeeDto;
import com.resourceallocator.backend.dto.EmployeeTechnologyDto;
import com.resourceallocator.backend.entity.Employee;
import com.resourceallocator.backend.entity.EmployeeTechnology;
import com.resourceallocator.backend.entity.Technology;
import com.resourceallocator.backend.exception.BadRequestException;
import com.resourceallocator.backend.exception.ResourceNotFoundException;
import com.resourceallocator.backend.repository.EmployeeRepository;
import com.resourceallocator.backend.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final TechnologyRepository technologyRepository;

    @Transactional(readOnly = true)
    public List<EmployeeDto> findAll() {
        return employeeRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public EmployeeDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Transactional
    public EmployeeDto create(EmployeeDto dto) {
        Employee employee = new Employee();
        apply(employee, dto);
        return toDto(employeeRepository.save(employee));
    }

    @Transactional
    public EmployeeDto update(Long id, EmployeeDto dto) {
        Employee employee = getEntity(id);
        apply(employee, dto);
        return toDto(employeeRepository.save(employee));
    }

    @Transactional
    public void delete(Long id) {
        employeeRepository.delete(getEntity(id));
    }

    private Employee getEntity(Long id) {
        return employeeRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Empleado no encontrado con id " + id));
    }

    private void apply(Employee employee, EmployeeDto dto) {
        employee.setFirstName(dto.firstName());
        employee.setLastName(dto.lastName());
        employee.setEmail(dto.email());
        employee.setPosition(dto.position());
        employee.setHireDate(dto.hireDate());
        employee.setCostPerDay(dto.costPerDay());

        employee.getTechnologies().clear();
        if (dto.technologies() != null) {
            for (EmployeeTechnologyDto techDto : dto.technologies()) {
                Technology technology = resolveTechnology(techDto.technologyId());
                employee.addTechnology(new EmployeeTechnology(
                        technology,
                        techDto.level(),
                        techDto.version(),
                        techDto.yearsExperience()));
            }
        }
    }

    private Technology resolveTechnology(Long technologyId) {
        return technologyRepository.findById(technologyId)
                .orElseThrow(() -> new BadRequestException(
                        "Tecnolog\u00eda no encontrada con id " + technologyId));
    }

    private EmployeeDto toDto(Employee employee) {
        List<EmployeeTechnologyDto> technologies = employee.getTechnologies().stream()
                .map(t -> new EmployeeTechnologyDto(
                        t.getTechnology().getId(),
                        t.getLevel(),
                        t.getVersion(),
                        t.getYearsExperience()))
                .toList();

        return new EmployeeDto(
                employee.getId(),
                employee.getFirstName(),
                employee.getLastName(),
                employee.getEmail(),
                employee.getPosition(),
                employee.getHireDate(),
                employee.getCostPerDay(),
                technologies);
    }
}