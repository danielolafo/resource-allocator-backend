package com.resourceallocator.backend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Employee {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String position;
    private LocalDate hireDate;
    private BigDecimal costPerDay;
    private List<EmployeeTechnology> technologies = new ArrayList<>();

    public void addTechnology(EmployeeTechnology technology) {
        technologies.add(technology);
    }
}