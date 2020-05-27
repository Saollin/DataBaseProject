package edu.agh.project.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="students")
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private PersonData personData;
    private Integer indexNumber;

    @ManyToMany(mappedBy = "students", cascade = {CascadeType.PERSIST})
    private Set<Group> groups = new HashSet<>();

    @OneToMany(mappedBy = "student")
    private Set<Grade> grades = new HashSet<>();

    public Student() { }

    public Student(String name, String surname, Integer indexNumber) {
        this.personData = new PersonData(name, surname);
        this.indexNumber = indexNumber;
    }

    public void addGrade(Grade grade) {
        grades.add(grade);
    }

    public void addGrades(Set<Grade> newGrades) {
        grades.addAll(newGrades);
    }

    public void removeGrade(Grade grade) {
        grades.remove(grade);
    }

    public void addToGroup(Group group) {
        groups.add(group);
    }

    public boolean removeFromGroup(Group group) {
        return groups.remove(group);
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

    public Set<Group> getGroups() {
        return groups;
    }

    public Set<Grade> getGrades() {
        return grades;
    }
}
