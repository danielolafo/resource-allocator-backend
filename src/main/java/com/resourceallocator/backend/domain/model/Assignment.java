package com.resourceallocator.backend.domain.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
public class Assignment {

    private Long id;
    private Long employeeId;
    private Long projectId;
    private AssignmentMode mode;
    private Integer hoursPerDay;
    private LocalDate startDate;
    private LocalDate endDate;
    private String notes;
}