package com.laversi.schoolapi.services;

import org.springframework.stereotype.Service;
import com.laversi.schoolapi.model.personDTO.PersonDTO;
import com.laversi.schoolapi.model.personDTO.PersonGetDTO;
import com.laversi.schoolapi.model.personDTO.PersonPutDTO;
import com.laversi.schoolapi.model.personEntity.PersonEntity;

@Service
public class PersonDTOConverter {

    PersonEntity convertToEntity(PersonDTO personDto, String id) {
        return new PersonEntity(
                id,
                personDto.name(),
                personDto.email(),
                personDto.password(),
                personDto.birthDate(),
                personDto.motherName(),
                personDto.fatherName(),
                personDto.telephone());
    }

    PersonDTO convertToDto(PersonEntity personEntity) {
        return new PersonDTO(
                personEntity.getId(),
                personEntity.getName(),
                personEntity.getEmail(),
                personEntity.getPassword(),
                personEntity.getBirthDate(),
                personEntity.getMotherName(),
                personEntity.getFatherName(),
                personEntity.getTelephone());
    }

     PersonPutDTO convertToPutDto(PersonEntity personEntity) {
        return new PersonPutDTO(
                personEntity.getName(),
                personEntity.getEmail(),
                personEntity.getPassword(),
                personEntity.getBirthDate(),
                personEntity.getMotherName(),
                personEntity.getFatherName(),
                personEntity.getTelephone());
    }

    PersonGetDTO convertToGetDto(PersonEntity personEntity) {
        return new PersonGetDTO(
                personEntity.getId(),
                personEntity.getName(),
                personEntity.getEmail(),
                personEntity.getBirthDate(),
                personEntity.getMotherName(),
                personEntity.getFatherName(),
                personEntity.getTelephone());
    }
    public void updateEntityFromPutDto(PersonEntity existingPerson, PersonPutDTO personDto) {
        existingPerson.setName(personDto.name() != null ? personDto.name() : existingPerson.getName());
        existingPerson.setEmail(personDto.email() != null ? personDto.email() : existingPerson.getEmail());
        existingPerson.setPassword(personDto.password() != null ? personDto.password() : existingPerson.getPassword());
        existingPerson.setBirthDate(personDto.birthDate() != null ? personDto.birthDate() : existingPerson.getBirthDate());
        existingPerson.setMotherName(personDto.motherName() != null ? personDto.motherName() : existingPerson.getMotherName());
        existingPerson.setFatherName(personDto.fatherName() != null ? personDto.fatherName() : existingPerson.getFatherName());
        existingPerson.setTelephone(personDto.telephone() != null ? personDto.telephone() : existingPerson.getTelephone());
    }
    
    
}
