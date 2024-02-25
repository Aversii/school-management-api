package com.laversi.schoolapi.model.courseDto;

import jakarta.validation.constraints.NotBlank;

public record CoursePostDTO(
        String id,
        @NotBlank String name
){}