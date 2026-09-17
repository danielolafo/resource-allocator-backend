package com.resourceallocator.backend.adapter.out.persistence.entity;

import com.resourceallocator.backend.domain.model.ProficiencyLevel;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_technologies")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeTechnologyJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeJpaEntity employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technology_id")
    private TechnologyJpaEntity technology;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProficiencyLevel level;

    private String version;

    @Column(name = "years_experience")
    private Integer yearsExperience;

    public EmployeeTechnologyJpaEntity(TechnologyJpaEntity technology, ProficiencyLevel level,
                                       String version, Integer yearsExperience) {
        this.technology = technology;
        this.level = level;
        this.version = version;
        this.yearsExperience = yearsExperience;
    }
}