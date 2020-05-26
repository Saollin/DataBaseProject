package edu.agh.project.entities;

import javax.persistence.*;

@Entity
@Table(name='courses')
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String courseName;

    public Course() {}

    public Course(String courseName) {
        this.courseName = courseName;
    }
}
