package edu.agh.project.daos;

import edu.agh.project.entities.*;

import javax.persistence.EntityManager;
import java.util.Collection;

public class GroupDao extends Dao {

    public GroupDao(EntityManager em) {
        super(em);
    }

    public Group create(Course course, Teacher teacher, GroupTime groupTime){
        Group group = new Group(course, teacher, groupTime);
        create(group);
        return group;
    }

    public void create(Group group){
        beginTransaction();
        em.persist(group);
        commitTransaction();
    }

    public Group find(int id) {
        return em.find(Group.class, id);
    }

    public void addStudentsToGroup(Group group, Collection<Student> students) {
        beginTransaction();
        group.addStudents(students);
        commitTransaction();
    }

    public void update(int id, Course course, Teacher teacher, GroupTime groupTime) {
        beginTransaction();
        Group group = find(id);
        group.setCourse(course);
        group.setTeacher(teacher);
        group.setGroupTime(groupTime);
        em.merge(group);
        commitTransaction();
    }

    public void delete(int id) {
        beginTransaction();
        Group group = find(id);
        em.remove(group);
        commitTransaction();
    }
}
