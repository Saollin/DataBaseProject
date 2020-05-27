package edu.agh.project.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="teachers")
public class Teacher {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Embedded
    private PersonData personData;

    @OneToMany(mappedBy = "teacher")
    private Set<Group> teachedGroups = new HashSet<>();

    @OneToMany(mappedBy = "mainTeacher")
    private Set<Course> ledCourses = new HashSet<>();

    public Teacher() {}

    public Teacher(String name, String surname) {
        this.personData = new PersonData(name, surname);
    }

    public int getId() {
        return id;
    }

    public void addTeachedGroups(Group group) {
        teachedGroups.add(group);
    }

    public void removeFromTeachedGroups(Group group) {
        teachedGroups.remove(group);
    }

    public void addLedCourse(Course course) {
        ledCourses.add(course);
    }

    public void removeFromLedCourses(Course course) {
        ledCourses.remove(course);
    }

    public PersonData getPersonData() {
        return personData;
    }

    public void setPersonData(PersonData personData) {
        this.personData = personData;
    }
}
