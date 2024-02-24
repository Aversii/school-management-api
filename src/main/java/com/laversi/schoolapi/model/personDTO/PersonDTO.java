package com.laversi.schoolapi.model.personDTO;


import java.time.LocalDate;

import com.laversi.schoolapi.model.personEntity.PersonEntity;

public record PersonDTO(
        String id,
        String name,
        String email,
        String password,
        LocalDate birthDate,
        String motherName,
        String fatherName,
        String telephone

        
) {
    public PersonEntity toEntity() {
    return new PersonEntity(this);
}
}

