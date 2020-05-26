package edu.agh.project.entities;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name="examinations")
public class Examination {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    private Group examinatedGroup;

    private Date date;
    private String description;

    public Examination() {}

    public Examination(Group examinatedGroup, Date date, String description) {
        this.examinatedGroup = examinatedGroup;
        this.date = date;
        this.description = description;
    }
}
