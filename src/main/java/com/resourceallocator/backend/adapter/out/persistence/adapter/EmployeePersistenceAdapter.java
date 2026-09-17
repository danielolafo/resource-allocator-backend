package com.resourceallocator.backend.adapter.out.persistence.adapter;

import com.resourceallocator.backend.adapter.out.persistence.entity.EmployeeJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.entity.EmployeeTechnologyJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.entity.TechnologyJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.mapper.EmployeeMapper;
import com.resourceallocator.backend.adapter.out.persistence.repository.EmployeeJpaRepository;
import com.resourceallocator.backend.adapter.out.persistence.repository.TechnologyJpaRepository;
import com.resourceallocator.backend.application.port.out.EmployeeRepository;
import com.resourceallocator.backend.domain.model.Employee;
import com.resourceallocator.backend.domain.model.EmployeeTechnology;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class EmployeePersistenceAdapter implements EmployeeRepository {

    private final EmployeeJpaRepository jpaRepository;
    private final TechnologyJpaRepository technologyJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Employee> findAll() {
        return jpaRepository.findAll().stream().map(EmployeeMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findById(Long id) {
        return jpaRepository.findById(id).map(EmployeeMapper::toDomain);
    }

    @Override
    @Transactional
    public Employee save(Employee employee) {
        EmployeeJpaEntity jpa;
        if (employee.getId() == null) {
            jpa = new EmployeeJpaEntity();
        } else {
            jpa = jpaRepository.findById(employee.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Empleado no encontrado con id " + employee.getId()));
            jpa.getTechnologies().clear();
        }

        EmployeeMapper.populate(jpa, employee);
        for (EmployeeTechnology tech : employee.getTechnologies()) {
            TechnologyJpaEntity technology = technologyJpaRepository
                    .getReferenceById(tech.getTechnologyId());
            jpa.addTechnology(new EmployeeTechnologyJpaEntity(
                    technology,
                    tech.getLevel(),
                    tech.getVersion(),
                    tech.getYearsExperience()));
        }
        return EmployeeMapper.toDomain(jpaRepository.save(jpa));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}