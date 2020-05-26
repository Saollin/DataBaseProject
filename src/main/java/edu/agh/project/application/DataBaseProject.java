package edu.agh.project.application;

import edu.agh.project.entities.Student;

import javax.persistence.*;

public class DataBaseProject {
    public static void main(final String[] args) throws Exception {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "myConfig");
        EntityManager em = emf.createEntityManager();
        try {
            EntityTransaction etx = em.getTransaction();
            etx.begin();
            Student student = new Student("Grzegorz", "Janosz", "gregory.janosz@gmail.com", 296573);
            em.persist(student);
            etx.commit();
        } finally {
            em.close();
        }
    }
}
