package edu.agh.project.entities;

import javax.persistence.*;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String name;
    private String surname;
    private String email;
    private Integer indexNumber;

    public Student() { }

    public Student(String name, String surname, String email, Integer indexNumber) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.indexNumber = indexNumber;
    }
}
