package com.laversi.schoolapi.model.personDTO;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;

public record PersonGetDTO(
        String id,
        @NotBlank String name,
        @NotBlank String email,
        @NotNull LocalDate birthDate,
        @NotBlank String motherName,
        @NotBlank String fatherName,
        String telephone
) {}
