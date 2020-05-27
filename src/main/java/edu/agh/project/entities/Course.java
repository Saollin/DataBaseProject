package edu.agh.project.entities;

import javax.persistence.*;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="courses")
public class Course {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String courseName;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    private Teacher mainTeacher;

    @OneToMany(mappedBy = "course")
    private Set<Group> courseGroups = new HashSet<>();

    public Course() {}

    public Course(String courseName, Teacher mainTeacher) {
        this.mainTeacher = mainTeacher;
        this.mainTeacher.addLedCourse(this);
        this.courseName = courseName;
    }

    public void addGroupToCourse(Group group) {
        courseGroups.add(group);
    }

    public void removeGroupFromCourse(Group group) {
        courseGroups.remove(group);
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

    public void setMainTeacher(Teacher teacher) {
        if(mainTeacher != teacher) {
            mainTeacher.removeFromLedCourses(this);
            this.mainTeacher = teacher;
            teacher.addLedCourse(this);
        }
    }
}
