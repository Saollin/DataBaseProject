package edu.agh.project.application;

import edu.agh.project.daos.CourseDao;
import edu.agh.project.daos.ExaminationDao;
import edu.agh.project.daos.GroupDao;
import edu.agh.project.daos.StudentDao;
import edu.agh.project.entities.*;

import javax.persistence.*;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

public class DataBaseProject {
    private static StudentDao studentDao;
    private static CourseDao courseDao;
    private static GroupDao groupDao;

    public static void main(final String[] args) {
        // create entity manager
        EntityManagerFactory emf = Persistence.createEntityManagerFactory( "myConfig");
        EntityManager em = emf.createEntityManager();

        // create daos
        StudentDao studentDao = new StudentDao(em);
        CourseDao courseDao = new CourseDao(em);
        GroupDao groupDao = new GroupDao(em);
        ExaminationDao examinationDao = new ExaminationDao(em);

        // create students
        Student student1 = studentDao.create("Grzegorz", "Janosz", 296573);
        Student student2 = studentDao.create("Adam", "Bera", 296546);
        Student student3 = studentDao.create("Grzegorz", "Kowalski", 296333);
        Student student4 = studentDao.create("Andrzej", "Ruba", 296685);
        Student student5 = studentDao.create("Alicja", "Zielińska", 296499);
        //siema, mała zmiana

        // create new course (teacher can be create without dao, because he is saved with creating course if doesn't exist)
        Teacher teacher1 = new Teacher("Robert", "Marcjan");
        Course course = courseDao.create("Bazy danych", teacher1);

        // create new group
        GroupTime groupTime = new GroupTime(DayOfWeek.TUESDAY, Time.valueOf("12:50:00"), Time.valueOf("14:20:00"));
        Group group = groupDao.create(course, teacher1, groupTime);
        groupDao.addStudentsToGroup(group, Arrays.asList(student1, student2, student3, student4, student5));

        // create examination
        Examination examination = examinationDao.create(group, Date.valueOf("2020-04-20"), "Kolos z MongoDB");
        examinationDao.addGradesOfExamination(examination, Arrays.asList(student1, student2), 5);
        examinationDao.addGradesOfExamination(examination, Arrays.asList(student3, student4), 4.5);
        examinationDao.addGrade(examination, student5, 3);

        // we have to close EntityManager at the end of program
        em.close();
    }
}
