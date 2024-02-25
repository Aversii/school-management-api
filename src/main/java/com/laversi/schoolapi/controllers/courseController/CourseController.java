package com.laversi.schoolapi.controllers.courseController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laversi.schoolapi.model.courseDto.CourseGetDTO;
import com.laversi.schoolapi.model.courseDto.CoursePostDTO;
import com.laversi.schoolapi.model.courseDto.CoursePutDTO;
import com.laversi.schoolapi.services.CourseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/course")
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping
    public ResponseEntity<List<CourseGetDTO>> getAllCourse() {
        try {
            List<CourseGetDTO> people = courseService.getAllCourse();
            return ResponseEntity.ok(people);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseGetDTO> getCourseById(@PathVariable int id) {
        try {
            CourseGetDTO course = courseService.getCourseById(id);
            return ResponseEntity.ok(course);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<CoursePostDTO> postCourse(@Valid @RequestBody CoursePostDTO course) {
        try {
            CoursePostDTO savedCourse = courseService.createCourse(course);
            return ResponseEntity.ok(savedCourse);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<CoursePutDTO> updateCourse(@PathVariable int id, @RequestBody CoursePutDTO course) {
        try {
            CoursePutDTO updatedCourse = courseService.updateCourse(id, course);
            return ResponseEntity.ok(updatedCourse);

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable int id) {
        try {
            courseService.deleteCourse(id);
            return ResponseEntity.noContent().build();
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
}