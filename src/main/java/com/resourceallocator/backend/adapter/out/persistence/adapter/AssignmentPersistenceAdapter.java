package com.resourceallocator.backend.adapter.out.persistence.adapter;

import com.resourceallocator.backend.adapter.out.persistence.entity.AssignmentJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.entity.EmployeeJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.entity.ProjectJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.mapper.AssignmentMapper;
import com.resourceallocator.backend.adapter.out.persistence.repository.AssignmentJpaRepository;
import com.resourceallocator.backend.adapter.out.persistence.repository.EmployeeJpaRepository;
import com.resourceallocator.backend.adapter.out.persistence.repository.ProjectJpaRepository;
import com.resourceallocator.backend.application.port.out.AssignmentRepository;
import com.resourceallocator.backend.domain.model.Assignment;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class AssignmentPersistenceAdapter implements AssignmentRepository {

    private final AssignmentJpaRepository jpaRepository;
    private final EmployeeJpaRepository employeeJpaRepository;
    private final ProjectJpaRepository projectJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Assignment> findAll() {
        return jpaRepository.findAll().stream().map(AssignmentMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Assignment> findById(Long id) {
        return jpaRepository.findById(id).map(AssignmentMapper::toDomain);
    }

    @Override
    @Transactional
    public Assignment save(Assignment assignment) {
        AssignmentJpaEntity jpa;
        if (assignment.getId() == null) {
            jpa = new AssignmentJpaEntity();
        } else {
            jpa = jpaRepository.findById(assignment.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Asignaci\u00f3n no encontrada con id " + assignment.getId()));
        }
        jpa.setEmployee(employeeJpaRepository.getReferenceById(assignment.getEmployeeId()));
        jpa.setProject(projectJpaRepository.getReferenceById(assignment.getProjectId()));
        AssignmentMapper.populate(jpa, assignment);
        return AssignmentMapper.toDomain(jpaRepository.save(jpa));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public long countActiveEmployees(Long projectId, LocalDate today) {
        return jpaRepository.countActiveEmployees(projectId, today);
    }
}