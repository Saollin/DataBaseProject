package edu.agh.project.entities;

import javax.persistence.*;

@Entity
@Table(name="enrollments")
public class Enrollment {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Student student;

    @ManyToOne
    private Group group;

    public Enrollment() {}

    public Enrollment(Student student, Group group) {
        this.student = student;
        this.group = group;
    }
}
