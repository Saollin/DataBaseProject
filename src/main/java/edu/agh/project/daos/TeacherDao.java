package edu.agh.project.daos;

import edu.agh.project.entities.PersonData;
import edu.agh.project.entities.Student;
import edu.agh.project.entities.Teacher;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

public class TeacherDao extends Dao {

    public TeacherDao(EntityManager em) {
        super(em);
    }

    public void create(String name, String surname){
        beginTransaction();
        Teacher teacher = new Teacher(name, surname);
        em.persist(teacher);
        commitTransaction();
    }

    public Teacher find(int id) {
        return em.find(Teacher.class, id);
    }

    public Teacher findWithSurname(String surname) throws NoResultException {
        beginTransaction();
        TypedQuery<Teacher> query = em.createQuery("from Teacher as t where t.personData.surname=:surname",Teacher.class);
        query.setParameter("surname", surname);
        Teacher found = query.getSingleResult();
        commitTransaction();
        return found;
    }

    public void update(int id, String name, String surname, Integer indexNumber) {
        beginTransaction();
        Teacher teacher = find(id);
        teacher.setPersonData(new PersonData(name, surname));
        em.merge(teacher);
        commitTransaction();
    }

    public void delete(int id) {
        beginTransaction();
        Teacher teacher = find(id);
        em.remove(teacher);
        commitTransaction();
    }
}
