package com.laversi.schoolapi.controllers.studentController;

import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;
import com.laversi.schoolapi.model.studentDTO.StudentDTO;
import com.laversi.schoolapi.model.studentDTO.StudentDTOConverter;
import com.laversi.schoolapi.repositories.ClassRepository;
import com.laversi.schoolapi.repositories.StudentRepository;
import com.laversi.schoolapi.model.studentEntity.StudentEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final ClassRepository classRepository;
    private final StudentDTOConverter studentDTOConverter;

    public StudentController(StudentRepository studentRepository, ClassRepository classRepository, StudentDTOConverter studentDTOConverter) {
        this.studentRepository = studentRepository;
        this.classRepository = classRepository;
        this.studentDTOConverter = studentDTOConverter;
    }

    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        List<StudentDTO> studentDTOs = students.stream()
                .map(studentDTOConverter::convertToDto)
                .collect(Collectors.toList());
        return ResponseEntity.ok(studentDTOs);
    }

    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> getStudentById(@PathVariable String id) {
        Optional<StudentEntity> studentOptional = studentRepository.findById(id);
        return studentOptional.map(studentDTOConverter::convertToDto)
                .map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<StudentDTO> createStudent(@RequestBody StudentDTO studentDTO) {
        try {
            ClassRoomEntity classEntity = null;
            Integer classRoomId = studentDTO.getClassRoomId();
            if (classRoomId != null && classRoomId != 0) {
                Optional<ClassRoomEntity> classOptional = classRepository.findById(classRoomId);
                if (classOptional.isPresent()) {
                    classEntity = classOptional.get();
                } else {
                    return ResponseEntity.notFound().build();
                }
            }
            StudentEntity studentEntity = studentDTOConverter.convertToEntity(studentDTO, classEntity);
            StudentEntity savedStudent = studentRepository.save(studentEntity);
            return ResponseEntity.status(HttpStatus.CREATED).body(studentDTOConverter.convertToDto(savedStudent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    

    @PutMapping("/{id}")
    public ResponseEntity<StudentDTO> updateStudent(@PathVariable String id, @RequestBody StudentDTO studentDTO) {
        try {
            Optional<StudentEntity> existingStudentOptional = studentRepository.findById(id);
            if (existingStudentOptional.isEmpty()) {
                return ResponseEntity.notFound().build();
            }
    
            StudentEntity existingStudent = existingStudentOptional.get();
            Integer classRoomId = studentDTO.getClassRoomId();
            ClassRoomEntity classEntity = existingStudent.getClassEntity(); // Mantém o valor atual se nenhum novo for fornecido
    
            if (classRoomId != null && classRoomId != 0) {
                Optional<ClassRoomEntity> classOptional = classRepository.findById(classRoomId);
                if (classOptional.isEmpty()) {
                    return ResponseEntity.notFound().build();
                }
                classEntity = classOptional.get();
            }
    
            StudentEntity updatedStudent = studentDTOConverter.convertToEntity(studentDTO, classEntity);
            updatedStudent.setId(id);
            StudentEntity savedStudent = studentRepository.save(updatedStudent);
            return ResponseEntity.ok(studentDTOConverter.convertToDto(savedStudent));
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }
    
    


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStudent(@PathVariable String id) {
        studentRepository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
