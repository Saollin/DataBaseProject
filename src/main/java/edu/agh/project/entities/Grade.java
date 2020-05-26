package edu.agh.project.entities;

import javax.persistence.*;
import javax.persistence.criteria.CriteriaBuilder;

@Entity
@Table(name="grades")
public class Grade {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Student student;
    @ManyToOne
    private Examination examination;
    private int grade;

    public Grade() {}

    public Grade(Student student, Examination examination, int grade) {
        this.student = student;
        this.examination = examination;
        this.grade = grade;
    }
}
