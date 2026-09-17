package com.resourceallocator.backend.adapter.out.persistence.adapter;

import com.resourceallocator.backend.adapter.out.persistence.entity.TechnologyJpaEntity;
import com.resourceallocator.backend.adapter.out.persistence.mapper.TechnologyMapper;
import com.resourceallocator.backend.adapter.out.persistence.repository.TechnologyJpaRepository;
import com.resourceallocator.backend.application.port.out.TechnologyRepository;
import com.resourceallocator.backend.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class TechnologyPersistenceAdapter implements TechnologyRepository {

    private final TechnologyJpaRepository jpaRepository;

    @Override
    @Transactional(readOnly = true)
    public List<Technology> findAll() {
        return jpaRepository.findAll().stream().map(TechnologyMapper::toDomain).toList();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Technology> findById(Long id) {
        return jpaRepository.findById(id).map(TechnologyMapper::toDomain);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Technology> findByName(String name) {
        return jpaRepository.findByNameIgnoreCase(name).map(TechnologyMapper::toDomain);
    }

    @Override
    @Transactional
    public Technology save(Technology technology) {
        TechnologyJpaEntity jpa = new TechnologyJpaEntity();
        if (technology.getId() != null) {
            jpa.setId(technology.getId());
        }
        TechnologyMapper.populate(jpa, technology);
        return TechnologyMapper.toDomain(jpaRepository.save(jpa));
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        jpaRepository.deleteById(id);
    }
}