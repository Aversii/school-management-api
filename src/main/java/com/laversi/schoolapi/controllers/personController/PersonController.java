package com.laversi.schoolapi.controllers.personController;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.laversi.schoolapi.model.personDTO.*;
import com.laversi.schoolapi.model.personEntity.PersonEntity;
import com.laversi.schoolapi.repositories.PersonRepository;

import jakarta.validation.Valid;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public ResponseEntity<PersonDTO> postPerson(@Valid @RequestBody PersonDTO person) {
        PersonEntity savedPerson = repo.save(convertToEntity(person));
        return ResponseEntity.ok(convertToDto(savedPerson));
    }
    @PutMapping("/{id}")
    public ResponseEntity<PersonDTO> updatePerson(@PathVariable String id, @RequestBody PersonPutDTO person) {
        Optional<PersonEntity> optionalPerson = repo.findById(id);
        if (optionalPerson.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
    
        PersonEntity existingPerson = optionalPerson.get();
        if (person.name() != null) {
            existingPerson.setName(person.name());
        }
        if (person.email() != null) {
            existingPerson.setEmail(person.email());
        }
        if (person.password() != null) {
            existingPerson.setPassword(person.password());
        }
        if (person.birthDate() != null) {
            existingPerson.setBirthDate(person.birthDate());
        }
        if (person.motherName() != null) {
            existingPerson.setMotherName(person.motherName());
        }
        if (person.fatherName() != null) {
            existingPerson.setFatherName(person.fatherName());
        }
        if (person.telephone() != null) {
            existingPerson.setTelephone(person.telephone());
        }
    
        PersonEntity updatedPerson = repo.save(existingPerson);
        return ResponseEntity.ok(convertToDto(updatedPerson));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePerson(@PathVariable String id) {
        repo.deleteById(id);
        return ResponseEntity.noContent().build();
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
                personEntity.getName(),
                personEntity.getEmail(),
                personEntity.getPassword(),
                personEntity.getBirthDate(),
                personEntity.getMotherName(),
                personEntity.getFatherName(),
                personEntity.getTelephone());
    }
}