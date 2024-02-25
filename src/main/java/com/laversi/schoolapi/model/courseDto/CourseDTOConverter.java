package com.laversi.schoolapi.model.courseDto;

import org.springframework.stereotype.Service;

import com.laversi.schoolapi.model.CourseEntity.CourseEntity;

@Service
public class CourseDTOConverter {

    public CourseEntity convertToEntity(CoursePostDTO CourseDto, int id) {
        return new CourseEntity(
                id,
                CourseDto.name()
        );
    }

    public CoursePostDTO convertToDto(CourseEntity CourseEntity) {
        return new CoursePostDTO(
                CourseEntity.getId(),
                CourseEntity.getName());
    }

     public CoursePutDTO convertToPutDto(CourseEntity CourseEntity) {
        return new CoursePutDTO(
                CourseEntity.getName());
    }

    public CourseGetDTO convertToGetDto(CourseEntity CourseEntity) {
        return new CourseGetDTO(
                CourseEntity.getId(),
                CourseEntity.getName());
    }
    public void updateEntityFromPutDto(CourseEntity existingCourse, CoursePutDTO CourseDto) {
        existingCourse.setName(CourseDto.name() != null ? CourseDto.name() : existingCourse.getName());
    }
    
    
}
