package com.resourceallocator.backend.application.service;

import com.resourceallocator.backend.application.dto.TechnologyDto;
import com.resourceallocator.backend.application.exception.ResourceNotFoundException;
import com.resourceallocator.backend.application.port.in.TechnologyUseCase;
import com.resourceallocator.backend.application.port.out.TechnologyRepository;
import com.resourceallocator.backend.domain.model.Technology;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TechnologyService implements TechnologyUseCase {

    private final TechnologyRepository technologyRepository;

    @Override
    public List<TechnologyDto> findAll() {
        return technologyRepository.findAll().stream().map(this::toDto).toList();
    }

    @Override
    public TechnologyDto findById(Long id) {
        return toDto(getEntity(id));
    }

    @Override
    public TechnologyDto create(TechnologyDto dto) {
        Technology technology = toDomain(dto);
        technology.setId(null);
        return toDto(technologyRepository.save(technology));
    }

    @Override
    public TechnologyDto update(Long id, TechnologyDto dto) {
        getEntity(id);
        Technology technology = toDomain(dto);
        technology.setId(id);
        return toDto(technologyRepository.save(technology));
    }

    @Override
    public void delete(Long id) {
        getEntity(id);
        technologyRepository.deleteById(id);
    }

    private Technology getEntity(Long id) {
        return technologyRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Tecnolog\u00eda no encontrada con id " + id));
    }

    private Technology toDomain(TechnologyDto dto) {
        return new Technology(null, dto.name(), dto.category(), dto.version(), dto.description());
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