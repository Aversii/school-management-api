package com.laversi.schoolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laversi.schoolapi.model.CourseEntity.CourseEntity;

public interface CourseRepository extends JpaRepository<CourseEntity, Integer> {
}