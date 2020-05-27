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

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Teacher getMainTeacher() {
        return mainTeacher;
    }

    public void setMainTeacher(Teacher mainTeacher) {
        this.mainTeacher = mainTeacher;
    }
}
