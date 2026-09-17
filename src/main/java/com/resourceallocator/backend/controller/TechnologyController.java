package com.resourceallocator.backend.controller;

import com.resourceallocator.backend.dto.TechnologyDto;
import com.resourceallocator.backend.service.TechnologyService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/technologies")
@RequiredArgsConstructor
public class TechnologyController {

    private final TechnologyService technologyService;

    @GetMapping
    public List<TechnologyDto> findAll() {
        return technologyService.findAll();
    }

    @GetMapping("/{id}")
    public TechnologyDto findById(@PathVariable Long id) {
        return technologyService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public TechnologyDto create(@Valid @RequestBody TechnologyDto dto) {
        return technologyService.create(dto);
    }

    @PutMapping("/{id}")
    public TechnologyDto update(@PathVariable Long id, @Valid @RequestBody TechnologyDto dto) {
        return technologyService.update(id, dto);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        technologyService.delete(id);
    }
}