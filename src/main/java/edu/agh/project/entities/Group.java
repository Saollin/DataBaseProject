package edu.agh.project.entities;

import javax.persistence.*;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="COURSE_FK")
    private Course course;

    @ManyToOne(cascade = {CascadeType.PERSIST})
    @JoinColumn(name="TEACHER_FK")
    private Teacher teacher;

    @Embedded
    private GroupTime groupTime;
    @ManyToMany(cascade = {CascadeType.PERSIST})
    private Set<Student> students = new HashSet<>();

    @OneToMany(mappedBy = "group", cascade = {CascadeType.PERSIST})
    private Set<Examination> examinations = new HashSet<>();

    public Group() {}

    public Group(Course course, Teacher teacher, GroupTime groupTime) {
        this.course = course;
        this.course.addGroupToCourse(this);
        this.teacher = teacher;
        teacher.addTeachedGroups(this);
        this.groupTime = groupTime;
    }

    public int getId(){
        return id;
    }

    public void addExamination(Examination examination) {
        examinations.add(examination);
    }

    public void removeExamination(Examination examination) {
        examinations.remove(examination);
    }

    public void addStudent(Student student) {
        students.add(student);
        student.addToGroup(this);
    }

    public void addStudents(Collection<Student> students) {
        for(Student s : students) {
            addStudent(s);
        }
    }

    public void removeStudent(Student student) {
        student.removeFromGroup(this);
        students.remove(student);
    }

    public void setCourse(Course changedCourse) {
        if(changedCourse != course) {
            course.removeGroupFromCourse(this);
            this.course = changedCourse;
            changedCourse.addGroupToCourse(this);
        }
    }

    public void setTeacher(Teacher changedTeacher) {
        if(changedTeacher != teacher){
            teacher.removeFromTeachedGroups(this);
            this.teacher = changedTeacher;
            changedTeacher.addTeachedGroups(this);
        }
    }

    public void setGroupTime(GroupTime groupTime) {
        this.groupTime = groupTime;
    }

    public Course getCourse() {
        return course;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public GroupTime getGroupTime() {
        return groupTime;
    }

    public Set<Student> getStudents() {
        return students;
    }

    public Set<Examination> getExaminations() {
        return examinations;
    }
}
