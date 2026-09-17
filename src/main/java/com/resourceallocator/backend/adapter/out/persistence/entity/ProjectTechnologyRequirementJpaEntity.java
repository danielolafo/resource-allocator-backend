package com.resourceallocator.backend.adapter.out.persistence.entity;

import com.resourceallocator.backend.domain.model.ProficiencyLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "project_technology_requirements")
@Getter
@Setter
@NoArgsConstructor
public class ProjectTechnologyRequirementJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technology_id")
    private TechnologyJpaEntity technology;

    @Enumerated(EnumType.STRING)
    @Column(name = "min_level", nullable = false)
    private ProficiencyLevel minLevel;

    @Column(name = "min_years_experience")
    private Integer minYearsExperience;

    private String version;

    private Integer count;

    public ProjectTechnologyRequirementJpaEntity(TechnologyJpaEntity technology, ProficiencyLevel minLevel,
                                                 Integer minYearsExperience, String version, Integer count) {
        this.technology = technology;
        this.minLevel = minLevel;
        this.minYearsExperience = minYearsExperience;
        this.version = version;
        this.count = count;
    }
}