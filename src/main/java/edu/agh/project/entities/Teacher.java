package edu.agh.project.entities;

import javax.persistence.*;

@Entity
@Table(name="teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private PersonData personData;

    public Teacher() {}

    public Teacher(String name, String surname, String email) {
        this.personData = new PersonData(name, surname, email);
    }

    public int getId() {
        return id;
    }

    public PersonData getPersonData() {
        return personData;
    }
}