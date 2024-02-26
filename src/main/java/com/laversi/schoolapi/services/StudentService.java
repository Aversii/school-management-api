package com.laversi.schoolapi.services;




import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;
import com.laversi.schoolapi.model.studentDTO.StudentDTO;
import com.laversi.schoolapi.model.studentDTO.StudentDTOConverter;
import com.laversi.schoolapi.model.studentEntity.StudentEntity;
import com.laversi.schoolapi.repositories.ClassRepository;
import com.laversi.schoolapi.repositories.StudentRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private ClassRepository classRepository;

    @Autowired
    private StudentDTOConverter studentDTOConverter;

    public List<StudentDTO> getAllStudents() {
        List<StudentEntity> students = studentRepository.findAll();
        return students.stream()
                .map(studentDTOConverter::convertToDto)
                .collect(Collectors.toList());
    }

    public StudentDTO getStudentById(String id) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }

        StudentEntity studentEntity = optionalStudent.get();
        return studentDTOConverter.convertToDto(studentEntity);
    }

    public StudentDTO createStudent(StudentDTO studentDTO) {
        ClassRoomEntity classEntity = getClassEntity(studentDTO.getClassRoomId());
        String id = UUID.randomUUID().toString();
        StudentEntity studentEntity = studentDTOConverter.convertToEntity(studentDTO, classEntity);
        StudentEntity savedStudent = studentRepository.save(studentEntity);
        return studentDTOConverter.convertToDto(savedStudent);
    }

    public StudentDTO updateStudent(String id, StudentDTO studentDTO) {
        Optional<StudentEntity> optionalStudent = studentRepository.findById(id);
        if (optionalStudent.isEmpty()) {
            throw new RuntimeException("Student not found");
        }

        ClassRoomEntity classEntity = getClassEntity(studentDTO.getClassRoomId());
        StudentEntity existingStudent = optionalStudent.get();
        StudentEntity updatedStudent = studentDTOConverter.convertToEntity(studentDTO, classEntity);
        updatedStudent.setId(id);
        StudentEntity savedStudent = studentRepository.save(updatedStudent);
        return studentDTOConverter.convertToDto(savedStudent);
    }

    public void deleteStudent(String id) {
        studentRepository.deleteById(id);
    }

    private ClassRoomEntity getClassEntity(Integer classRoomId) {
        if (classRoomId != null && classRoomId != 0) {
            Optional<ClassRoomEntity> classOptional = classRepository.findById(classRoomId);
            if (classOptional.isEmpty()) {
                throw new RuntimeException("ClassRoom not found");
            }
            return classOptional.get();
        }
        return null;
    }
}
