package com.laversi.schoolapi.model.teacherEntity;

import java.time.LocalDate;

import com.laversi.schoolapi.model.CourseEntity.CourseEntity;
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
public class TeacherEntity extends PersonEntity {

    @ManyToOne
    @JoinColumn(name = "classroom_id")
    private ClassRoomEntity classEntity;

    @ManyToOne
    @JoinColumn(name = "course_id")
    private CourseEntity subject;

    @Builder(builderMethodName = "teacherBuilder")
    public TeacherEntity(String id, String name, String email, String password, LocalDate birthDate, String motherName, String fatherName, String telephone, ClassRoomEntity classEntity, CourseEntity subject) {
        super(id, name, email, password, birthDate, motherName, fatherName, telephone);
        this.classEntity = classEntity;
        this.subject = subject;
    }

}
