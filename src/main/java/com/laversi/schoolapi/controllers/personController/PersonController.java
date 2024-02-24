package com.laversi.schoolapi.controllers.personController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laversi.schoolapi.model.personDTO.PersonDTO;
import com.laversi.schoolapi.model.personEntity.PersonEntity;
import com.laversi.schoolapi.repositories.PersonRepository;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/person")
public class PersonController {

    @Autowired
    private PersonRepository repo;

    @GetMapping
    public ResponseEntity<List<PersonEntity>> getAllPeople() {
        List<PersonEntity> people = repo.findAll();
        return ResponseEntity.ok(people);
    }

    @PostMapping
    public ResponseEntity<PersonDTO> postPerson(@RequestBody PersonDTO person) {
        PersonEntity savedPerson = repo.save(convertToEntity(person));
        return ResponseEntity.ok(convertToDto(savedPerson));
    }

    private PersonEntity convertToEntity(PersonDTO personDto) {
        return new PersonEntity(
                null,
                personDto.name(),
                personDto.email(),
                personDto.password(),
                personDto.birthDate(),
                personDto.motherName(),
                personDto.fatherName(),
                personDto.telephone());
    }

    private PersonDTO convertToDto(PersonEntity personEntity) {
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
}