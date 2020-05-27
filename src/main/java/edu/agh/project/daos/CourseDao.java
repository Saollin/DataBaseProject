package edu.agh.project.daos;

import edu.agh.project.entities.Course;
import edu.agh.project.entities.PersonData;
import edu.agh.project.entities.Teacher;

import javax.persistence.EntityManager;

public class CourseDao extends Dao {

    public CourseDao(EntityManager em) {
        super(em);
    }

    public Course create(String courseName, Teacher mainTeacher){
        Course course = new Course(courseName, mainTeacher);
        create(course);
        return course;
    }

    public void create(Course course) {
        beginTransaction();
        em.persist(course);
        commitTransaction();
    }


    public Course find(int id) {
        return em.find(Course.class, id);
    }

    public void update(int id, String courseName, Teacher mainTeacher) {
        beginTransaction();
        Course course = find(id);
        course.setCourseName(courseName);
        course.setMainTeacher(mainTeacher);
        em.merge(course);
        commitTransaction();
    }

    public void delete(int id) {
        beginTransaction();
        Course course = find(id);
        em.remove(course);
        commitTransaction();
    }
}
