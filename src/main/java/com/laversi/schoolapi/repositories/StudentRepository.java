package com.laversi.schoolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import com.laversi.schoolapi.model.studentEntity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, String> {
}