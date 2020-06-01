package edu.agh.project.daos;

import edu.agh.project.entities.*;

import javax.persistence.EntityManager;
import java.sql.Date;
import java.util.Collection;

public class ExaminationDao extends Dao {

    public ExaminationDao(EntityManager em) {
        super(em);
    }

    public Examination create(Group group, Date date, String description){
        Examination examination = new Examination(group, date, description);
        create(examination);
        return examination;
    }

    public void create(Examination examination){
        beginTransaction();
        em.persist(examination);
        commitTransaction();
    }

    public Examination find(int id) {
        return em.find(Examination.class, id);
    }

    public void addGrade(Examination examination, Student student, double result) {
        beginTransaction();
        examination.addGrade(student, result);
        commitTransaction();
    }

    public void addGradesOfExamination(Examination examination, Collection<Student> ratedStudents, double result) {
        beginTransaction();
        for(Student s : ratedStudents) {
            examination.addGrade(s, result);
        }
        commitTransaction();
    }

    public void update(int id, Group group, Date date, String description) {
        beginTransaction();
        Examination examination = find(id);
        examination.setDate(date);
        examination.setGroup(group);
        examination.setDescription(description);
        em.merge(examination);
        commitTransaction();
    }

    public void delete(int id) {
        beginTransaction();
        Examination examination = find(id);
        if(examination != null) {
            em.remove(examination);
        }
        commitTransaction();
    }
}
