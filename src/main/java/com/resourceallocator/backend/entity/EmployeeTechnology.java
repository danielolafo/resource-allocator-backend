package com.resourceallocator.backend.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "employee_technologies")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeTechnology {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    @JsonIgnore
    private Employee employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "technology_id")
    private Technology technology;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ProficiencyLevel level;

    private String version;

    @Column(name = "years_experience")
    private Integer yearsExperience;

    public EmployeeTechnology(Technology technology, ProficiencyLevel level, String version, Integer yearsExperience) {
        this.technology = technology;
        this.level = level;
        this.version = version;
        this.yearsExperience = yearsExperience;
    }
}