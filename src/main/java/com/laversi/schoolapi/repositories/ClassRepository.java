package com.laversi.schoolapi.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.laversi.schoolapi.model.classRoomEntity.ClassRoomEntity;

public interface ClassRepository extends JpaRepository<ClassRoomEntity, Integer> {
}