package edu.agh.project.entities;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String courseName;

    @ManyToOne
    private Teacher mainTeacher;

    public Course() {}

    public Course(String courseName) {
        this.courseName = courseName;
    }
}
