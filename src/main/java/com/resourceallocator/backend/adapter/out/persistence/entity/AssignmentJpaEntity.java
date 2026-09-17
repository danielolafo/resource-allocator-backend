package com.resourceallocator.backend.adapter.out.persistence.entity;

import com.resourceallocator.backend.domain.model.AssignmentMode;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Table(name = "assignments")
@Getter
@Setter
@NoArgsConstructor
public class AssignmentJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "employee_id")
    private EmployeeJpaEntity employee;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "project_id")
    private ProjectJpaEntity project;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private AssignmentMode mode;

    @Column(name = "hours_per_day")
    private Integer hoursPerDay;

    @Column(name = "start_date", nullable = false)
    private LocalDate startDate;

    @Column(name = "end_date", nullable = false)
    private LocalDate endDate;

    @Column(length = 1000)
    private String notes;

    public AssignmentJpaEntity(EmployeeJpaEntity employee, ProjectJpaEntity project, AssignmentMode mode,
                               Integer hoursPerDay, LocalDate startDate, LocalDate endDate, String notes) {
        this.employee = employee;
        this.project = project;
        this.mode = mode;
        this.hoursPerDay = hoursPerDay;
        this.startDate = startDate;
        this.endDate = endDate;
        this.notes = notes;
    }
}