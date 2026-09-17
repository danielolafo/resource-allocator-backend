package com.resourceallocator.backend.adapter.in.web;

import com.resourceallocator.backend.application.dto.TechnologyDto;
import com.resourceallocator.backend.application.port.in.TechnologyUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyUseCase technologyUseCase;

    @GetMapping
    public List<TechnologyDto> findAll() {
        return technologyUseCase.findAll();
    }

    @GetMapping("/{id}")
    public TechnologyDto findById(@PathVariable Long id) {
        return technologyUseCase.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnologyDto create(@Valid @RequestBody TechnologyDto dto) {
        return technologyUseCase.create(dto);
    }

    @PutMapping("/{id}")
    public TechnologyDto update(@PathVariable Long id, @Valid @RequestBody TechnologyDto dto) {
        return technologyUseCase.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        technologyUseCase.delete(id);
    }
}