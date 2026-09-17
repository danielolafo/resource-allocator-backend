package com.resourceallocator.backend.application.port.in;

import com.resourceallocator.backend.application.dto.TechnologyDto;

import java.util.List;

public interface TechnologyUseCase {

    List<TechnologyDto> findAll();

    TechnologyDto findById(Long id);

    TechnologyDto create(TechnologyDto dto);

    TechnologyDto update(Long id, TechnologyDto dto);

    void delete(Long id);
}