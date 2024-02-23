package com.laversi.schoolapi.repositories;
import org.springframework.data.jpa.repository.JpaRepository;
import com.laversi.schoolapi.model.personEntity.PersonEntity;


public interface PersonRepository extends JpaRepository<PersonEntity, String> {
    
}
