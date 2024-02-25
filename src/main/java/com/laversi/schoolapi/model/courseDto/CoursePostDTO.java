package com.laversi.schoolapi.model.courseDto;

import jakarta.validation.constraints.NotBlank;

public record CoursePostDTO(
        int id,
        @NotBlank String name
){}