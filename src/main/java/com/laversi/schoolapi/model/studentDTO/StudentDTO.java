package com.laversi.schoolapi.model.studentDTO;
import java.time.LocalDate;

import com.laversi.schoolapi.model.studentEntity.StudentEntity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
// StudentDTO.java
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {

    private String id;
    private String name;
    private String email;
    private String password;
    private LocalDate birthDate;
    private String motherName;
    private String fatherName;
    private String telephone;
    private Integer classRoomId;
    private String classRoomName;

    public static StudentDTO fromStudentEntity(StudentEntity studentEntity) {
        return new StudentDTO(
                studentEntity.getId(),
                studentEntity.getName(),
                studentEntity.getEmail(),
                studentEntity.getPassword(),
                studentEntity.getBirthDate(),
                studentEntity.getMotherName(),
                studentEntity.getFatherName(),
                studentEntity.getTelephone(),
                studentEntity.getClassEntity() != null ? studentEntity.getClassEntity().getId() : null,
                studentEntity.getClassEntity() != null ? studentEntity.getClassEntity().getName() : null
        );
    }
}
