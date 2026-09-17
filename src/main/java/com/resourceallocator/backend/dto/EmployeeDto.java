package com.resourceallocator.backend.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.*;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

public record EmployeeDto(
        Long id,
        @NotBlank String firstName,
        @NotBlank String lastName,
        @NotBlank @Email String email,
        @NotBlank String position,
        LocalDate hireDate,
        @DecimalMin("0.0") BigDecimal costPerDay,
        @Valid List<EmployeeTechnologyDto> technologies
) {}