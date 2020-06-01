package edu.agh.project.entities;

import javax.persistence.*;
import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="examinations")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="GROUP_FK")
    private Group group;
    @OneToMany(mappedBy = "examination", cascade = {CascadeType.PERSIST})
    private Set<Grade> grades = new HashSet<>();

    private Date date;
    private String description;

    public Examination() {}

    public Examination(Group group, Date date, String description) {
        this.group = group;
        this.group.addExamination(this);
        this.date = date;
        this.description = description;
    }

    public int getId(){
        return id;
    }

    public void addGrade(Student rated, double result) {
        if(group.getStudents().contains(rated)) {
            Grade newGrade = new Grade(rated, this, result);
            grades.add(newGrade);
        }
    }

    public Group getGroup() {
        return group;
    }

    public Set<Grade> getGrades() {
        return grades;
    }

    public Date getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setGroup(Group changedGroup) {
        // remove examination from previous group
        if(changedGroup != group){
            group.removeExamination(this);
            group = changedGroup;
            group.addExamination(this);
        }
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
