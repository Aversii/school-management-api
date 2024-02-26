package com.laversi.schoolapi.model.studentDTO;

import org.springframework.stereotype.Component;

import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;
import com.laversi.schoolapi.repositories.ClassRepository;
import com.laversi.schoolapi.model.studentEntity.StudentEntity;

@Component
public class StudentDTOConverter {

    private final ClassRepository classRepository;

    public StudentDTOConverter(ClassRepository classRepository) {
        this.classRepository = classRepository;
    }

    public StudentEntity convertToEntity(StudentDTO studentDTO, ClassRoomEntity classEntity) {
        try {
            return StudentEntity.studentBuilder()
                    .id(studentDTO.getId())
                    .name(studentDTO.getName())
                    .email(studentDTO.getEmail())
                    .password(studentDTO.getPassword())
                    .birthDate(studentDTO.getBirthDate())
                    .motherName(studentDTO.getMotherName())
                    .fatherName(studentDTO.getFatherName())
                    .telephone(studentDTO.getTelephone())
                    .classEntity(classEntity)
                    .build();
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
    

    public StudentDTO convertToDto(StudentEntity studentEntity) {
        try {
            String classRoomName = null;
            Integer classRoomId = null;
            if (studentEntity.getClassEntity() != null) {
                classRoomId = studentEntity.getClassEntity().getId();
                classRoomName = classRepository.findById(classRoomId)
                        .map(ClassRoomEntity::getName)
                        .orElse(null);
            }

            return new StudentDTO(
                    studentEntity.getId(),
                    studentEntity.getName(),
                    studentEntity.getEmail(),
                    studentEntity.getPassword(),
                    studentEntity.getBirthDate(),
                    studentEntity.getMotherName(),
                    studentEntity.getFatherName(),
                    studentEntity.getTelephone(),
                    classRoomId,
                    classRoomName
            );
        } catch (Exception e) {
            throw new RuntimeException(e.getMessage());
        }
    }
}
