package com.resourceallocator.backend.adapter.out.persistence.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "employees")
@Getter
@Setter
@NoArgsConstructor
public class EmployeeJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "first_name", nullable = false)
    private String firstName;

    @Column(name = "last_name", nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    private String position;

    @Column(name = "hire_date")
    private LocalDate hireDate;

    @Column(name = "cost_per_day", precision = 10, scale = 2)
    private BigDecimal costPerDay;

    @OneToMany(mappedBy = "employee", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeTechnologyJpaEntity> technologies = new ArrayList<>();

    public void addTechnology(EmployeeTechnologyJpaEntity technology) {
        technologies.add(technology);
        technology.setEmployee(this);
    }
}