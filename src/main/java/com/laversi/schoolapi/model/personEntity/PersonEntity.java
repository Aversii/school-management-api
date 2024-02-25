package com.laversi.schoolapi.model.personEntity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "person")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of="id")
public class PersonEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    private String name;

    private String email;

    private String password;

    private LocalDate birthDate;

    private String motherName;

    private String fatherName;

    private String telephone;

    public PersonEntity(String id, String name, String email, LocalDate birthDate, String motherName, String fatherName, String telephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.birthDate = birthDate;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.telephone = telephone;
    }

    
    public PersonEntity(String id, String name, String email,  String motherName, String fatherName, String telephone) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.motherName = motherName;
        this.fatherName = fatherName;
        this.telephone = telephone;
    }
    
    
}