package com.laversi.schoolapi.model.studentEntity;

import java.time.LocalDate;

import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;
import com.laversi.schoolapi.model.personEntity.PersonEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class StudentEntity extends PersonEntity {

    @ManyToOne
    @JoinColumn(name = "class_id")
    private ClassRoomEntity classEntity;

    @Builder(builderMethodName = "studentBuilder")
    public StudentEntity(String id, String name, String email, String password, LocalDate birthDate, String motherName, String fatherName, String telephone, ClassRoomEntity classEntity) {
        super(id, name, email, password, birthDate, motherName, fatherName, telephone);
        this.classEntity = classEntity;
    }

}
