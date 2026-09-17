package com.resourceallocator.backend.adapter.out.persistence.mapper;

import com.resourceallocator.backend.adapter.out.persistence.entity.TechnologyJpaEntity;
import com.resourceallocator.backend.domain.model.Technology;

public final class TechnologyMapper {

    private TechnologyMapper() {
    }

    public static void populate(TechnologyJpaEntity target, Technology source) {
        target.setName(source.getName());
        target.setCategory(source.getCategory());
        target.setVersion(source.getVersion());
        target.setDescription(source.getDescription());
    }

    public static Technology toDomain(TechnologyJpaEntity jpa) {
        return new Technology(
                jpa.getId(),
                jpa.getName(),
                jpa.getCategory(),
                jpa.getVersion(),
                jpa.getDescription());
    }
}