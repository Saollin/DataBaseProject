package edu.agh.project.entities;

import javax.persistence.*;

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
    private int result;

    public Grade() {}

    public Grade(Student student, Examination examination, int grade) {
        this.student = student;
        this.student.addGrade(this);
        this.examination = examination;
        this.examination.addGrade(this);
        this.result = grade;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Examination getExamination() {
        return examination;
    }

    public void setExamination(Examination examination) {
        this.examination = examination;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int grade) {
        this.result = grade;
    }
}
