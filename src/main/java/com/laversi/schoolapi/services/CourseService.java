package com.laversi.schoolapi.services;

import com.laversi.schoolapi.model.CourseEntity.CourseEntity;
import com.laversi.schoolapi.model.courseDto.CourseDTOConverter;
import com.laversi.schoolapi.model.courseDto.CourseGetDTO;
import com.laversi.schoolapi.model.courseDto.CoursePostDTO;
import com.laversi.schoolapi.model.courseDto.CoursePutDTO;
import com.laversi.schoolapi.repositories.CourseRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private CourseDTOConverter courseDTOConverter;

    public List<CourseGetDTO> getAllCourse() {
        List<CourseEntity> people = courseRepository.findAll();
        return people.stream()
                .map(courseEntity -> courseDTOConverter.convertToGetDto(courseEntity))
                .collect(Collectors.toList());
    }

        public CourseGetDTO getCourseById(int id) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);
        if (optionalCourse.isEmpty()) {
            throw new RuntimeException("Course not found");
        }

        CourseEntity courseEntity = optionalCourse.get();
        return courseDTOConverter.convertToGetDto(courseEntity);
    }

    public CoursePostDTO createCourse(CoursePostDTO courseDto) {
        CourseEntity courseEntity = courseDTOConverter.convertToEntity(courseDto);
        CourseEntity savedCourse = courseRepository.save(courseEntity);
        return courseDTOConverter.convertToDto(savedCourse);
    }

    public CoursePutDTO updateCourse(int id, CoursePutDTO courseDto) {
        Optional<CourseEntity> optionalCourse = courseRepository.findById(id);

        CourseEntity existingCourse = optionalCourse.get();
        courseDTOConverter.updateEntityFromPutDto(existingCourse, courseDto);
    
        CourseEntity updatedCourse = courseRepository.save(existingCourse);
        return courseDTOConverter.convertToPutDto(updatedCourse);
    }

    public void deleteCourse(int id) {
        courseRepository.deleteById(id);
    }
}
