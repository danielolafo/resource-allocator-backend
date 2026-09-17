package com.resourceallocator.backend.service;

import com.resourceallocator.backend.dto.TechnologyDto;
import com.resourceallocator.backend.entity.Technology;
import com.resourceallocator.backend.exception.ResourceNotFoundException;
import com.resourceallocator.backend.repository.TechnologyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService {

    private final TechnologyRepository technologyRepository;

    @Transactional(readOnly = true)
    public List<TechnologyDto> findAll() {
        return technologyRepository.findAll().stream().map(this::toDto).toList();
    }

    @Transactional(readOnly = true)
    public TechnologyDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Transactional
    public TechnologyDto create(TechnologyDto dto) {
        Technology technology = new Technology(dto.name(), dto.category(), dto.version(), dto.description());
        return toDto(technologyRepository.save(technology));
    }

    @Transactional
    public TechnologyDto update(Long id, TechnologyDto dto) {
        Technology technology = getEntity(id);
        technology.setName(dto.name());
        technology.setCategory(dto.category());
        technology.setVersion(dto.version());
        technology.setDescription(dto.description());
        return toDto(technologyRepository.save(technology));
    }

    @Transactional
    public void delete(Long id) {
        technologyRepository.delete(getEntity(id));
    }

    private Technology getEntity(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tecnolog\u00eda no encontrada con id " + id));
    }

    private TechnologyDto toDto(Technology technology) {
        return new TechnologyDto(
                technology.getId(),
                technology.getName(),
                technology.getCategory(),
                technology.getVersion(),
                technology.getDescription());
    }
}