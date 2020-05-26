package edu.agh.project.daos;

import edu.agh.project.entities.PersonData;
import edu.agh.project.entities.Student;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.swing.text.html.parser.Entity;

public class StudentDao extends Dao {

    public StudentDao(EntityManager em) {
        super(em);
    }

    public void create(String name, String surname, Integer indexNumber){
        beginTransaction();
        Student student = new Student(name, surname, indexNumber);
        em.persist(student);
        commitTransaction();
    }

    public Student find(int id) {
        return em.find(Student.class, id);
    }

    public void update(int id, String name, String surname, Integer indexNumber) {
        beginTransaction();
        Student student = find(id);
        student.setPersonData(new PersonData(name, surname));
        student.setIndexNumber(indexNumber);
        em.merge(student);
        commitTransaction();
    }

    public void delete(int id) {
        beginTransaction();
        Student student = find(id);
        em.remove(student);
        commitTransaction();
    }
}
