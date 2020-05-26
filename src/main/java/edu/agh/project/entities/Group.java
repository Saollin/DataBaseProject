package edu.agh.project.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Set;

@Entity
@Table(name="groups")
public class Group {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Course course;

    @ManyToOne
    private Teacher teacher;

    @Embedded
    private GroupTime groupTime;

    @OneToMany
    @JoinColumn(name="EXAMINATIONS_FK")
    private Set<Examination> examinations;

    public Group() {}

    public Group(Course course, Teacher teacher, GroupTime groupTime) {
        this.course = course;
        this.teacher = teacher;
        this.groupTime = groupTime;
    }
}
