package com.resourceallocator.backend.adapter.out.persistence.adapter;

import com.resourceallocator.backend.adapter.out.persistence.entity.ProjectJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.entity.ProjectTechnologyRequirementJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.entity.TechnologyJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.mapper.ProjectMapper;
import com.resourceallocator.backend.adapter.out.persistence.repository.ProjectJpaRepository;
import com.resourceallocator.backend.adapter.out.persistence.repository.TechnologyJpaRepository;
import com.resourceallocator.backend.application.port.out.ProjectRepository;
import com.resourceallocator.backend.domain.model.Project;
import com.resourceallocator.backend.domain.model.ProjectTechnologyRequirement;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class ProjectPersistenceAdapter implements ProjectRepository {

    private final ProjectJpaRepository jpaRepository;
    private final TechnologyJpaRepository technologyJpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Project> findAll() {
        return jpaRepository.findAll().stream().map(ProjectMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Project> findById(Long id) {
        return jpaRepository.findById(id).map(ProjectMapper::toDomain);
    }

    @Override
    @Transactional
    public Project save(Project project) {
        ProjectJpaEntity jpa;
        if (project.getId() == null) {
            jpa = new ProjectJpaEntity();
        } else {
            jpa = jpaRepository.findById(project.getId())
                    .orElseThrow(() -> new EntityNotFoundException(
                            "Proyecto no encontrado con id " + project.getId()));
            jpa.getRequiredTechnologies().clear();
        }

        ProjectMapper.populate(jpa, project);
        for (ProjectTechnologyRequirement req : project.getRequiredTechnologies()) {
            TechnologyJpaEntity technology = technologyJpaRepository
                    .getReferenceById(req.getTechnologyId());
            jpa.addRequirement(new ProjectTechnologyRequirementJpaEntity(
                    technology,
                    req.getMinLevel(),
                    req.getMinYearsExperience(),
                    req.getVersion(),
                    req.getCount()));
        }
        return ProjectMapper.toDomain(jpaRepository.save(jpa));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}