package edu.agh.project.daos;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public abstract class Dao {

    protected EntityManager em;
    protected EntityTransaction etx;

    public Dao(EntityManager em) {
        this.em = em;
        this.etx = em.getTransaction();
    }

    protected void beginTransaction() {
        try {
            etx.begin();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void commitTransaction() {
        try {
            etx.commit();
//            em.close();
        } catch (IllegalStateException e) {
            rollbackTransaction();
        }
    }

    protected void rollbackTransaction() {
        try {
            etx.rollback();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        }
    }
}
