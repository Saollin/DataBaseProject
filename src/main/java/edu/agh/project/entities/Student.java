package edu.agh.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private PersonData personData;
    private Integer indexNumber;

    public Student() { }

    public Student(String name, String surname, Integer indexNumber) {
        this.personData = new PersonData(name, surname);
        this.indexNumber = indexNumber;
    }

    public int getId() {
        return id;
    }

    public PersonData getPersonData() {
        return personData;
    }

    public Integer getIndexNumber() {
        return indexNumber;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }

    public void setIndexNumber(Integer indexNumber) {
        this.indexNumber = indexNumber;
    }
}
