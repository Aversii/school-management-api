package com.laversi.schoolapi.services;

import com.laversi.schoolapi.model.personDTO.*;
import com.laversi.schoolapi.model.personEntity.PersonEntity;
import com.laversi.schoolapi.repositories.PersonRepository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private PersonDTOConverter personDTOConverter;

    public List<PersonGetDTO> getAllPeople() {
        List<PersonEntity> people = personRepository.findAll();
        return people.stream()
                .map(personEntity -> personDTOConverter.convertToGetDto(personEntity))
                .collect(Collectors.toList());
    }

        public PersonGetDTO getPersonById(String id) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);
        if (optionalPerson.isEmpty()) {
            throw new RuntimeException("Person not found");
        }

        PersonEntity personEntity = optionalPerson.get();
        return personDTOConverter.convertToGetDto(personEntity);
    }

    public PersonDTO createPerson(PersonDTO personDto) {
        String id = UUID.randomUUID().toString();
        PersonEntity personEntity = personDTOConverter.convertToEntity(personDto, id);
        PersonEntity savedPerson = personRepository.save(personEntity);
        return personDTOConverter.convertToDto(savedPerson);
    }

    public PersonPutDTO updatePerson(String id, PersonPutDTO personDto) {
        Optional<PersonEntity> optionalPerson = personRepository.findById(id);

        PersonEntity existingPerson = optionalPerson.get();
        personDTOConverter.updateEntityFromPutDto(existingPerson, personDto);
    
        PersonEntity updatedPerson = personRepository.save(existingPerson);
        return personDTOConverter.convertToPutDto(updatedPerson);
    }

    public void deletePerson(String id) {
        personRepository.deleteById(id);
    }
}
