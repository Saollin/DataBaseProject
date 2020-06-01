package edu.agh.project.application;

import edu.agh.project.daos.*;
import edu.agh.project.entities.*;

import javax.persistence.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Date;
import java.sql.Time;
import java.util.Arrays;

public class DataBaseProject {
    private final StudentDao studentDao;
    private final CourseDao courseDao;
    private final GroupDao groupDao;
    private final ExaminationDao examinationDao;
    private final TeacherDao teacherDao;
    private final BufferedReader userInputReader;

    public static void main(final String[] args) throws IOException {

        EntityManagerFactory emf = Persistence.createEntityManagerFactory("myConfig");
        EntityManager em = emf.createEntityManager();

        StudentDao studentDao = new StudentDao(em);
        CourseDao courseDao = new CourseDao(em);
        GroupDao groupDao = new GroupDao(em);
        ExaminationDao examinationDao = new ExaminationDao(em);
        TeacherDao teacherDao = new TeacherDao(em);
        BufferedReader userInputReader =
                new BufferedReader(new InputStreamReader(System.in));
        new DataBaseProject(studentDao,courseDao,groupDao,examinationDao, teacherDao, userInputReader).run();
        /*
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
        em.close();*/
    }

    public DataBaseProject(StudentDao studentDao, CourseDao courseDao, GroupDao groupDao, ExaminationDao examinationDao, TeacherDao teacherDao, BufferedReader userInputReader) {
        this.studentDao = studentDao;
        this.courseDao = courseDao;
        this.groupDao = groupDao;
        this.examinationDao = examinationDao;
        this.teacherDao = teacherDao;
        this.userInputReader = userInputReader;
    }

    private void run() throws IOException {
        System.out.println("Type od user: \n"
                + "1) Teacher. \n"
                + "2) Student. \n");
        int option = Integer.parseInt(userInputReader.readLine());

        switch (option) {
            case 1:
                loginTeacher(3);
                break;
            case 2:
                loginStudent(3);
                break;
        }
    }

    private void loginStudent(int count) throws IOException {
        System.out.println("Login: ");
        int index = Integer.parseInt(userInputReader.readLine());
        try {
            Student student = studentDao.findWithIndex(index);
            studentRun(student);
        }
        catch (NoResultException exception){
            if(count > 0) {
                System.out.println("Nie ma takiego użytkownika. Zostało prób: " + count);
                loginStudent(count-1);
            }
        }
    }

    private void loginTeacher(int count) throws IOException {
        System.out.println("Login: ");
        String surname = userInputReader.readLine().trim();
        try {
            Teacher teacher = teacherDao.findWithSurname(surname);
            teacherRun(teacher);
        }
        catch (NoResultException exception){
            if(count > 0){
                System.out.println("Nie ma takiego użytkownika. Zostało prób: " + count);
                loginTeacher(count-1);
            }
        }
    }

    private void teacherRun(Teacher teacher) throws IOException {
        while(true) {
            System.out.println("Enter an option: \n"
                    + "1) Show list of your courses.\n"
                    + "2) Show list of your groups.\n"
                    + "3) Show list of examinations.\n"
                    + "4) Show list of students from specific group.\n"
                    + "5) Add new examination. \n"
                    + "6) Delete examination.\n"
                    + "7) Add grade\n"
                    + "8) End program\n");
            int option = Integer.parseInt(userInputReader.readLine());

            switch (option) {
                case 1:
                    listOfCoursesTeacher(teacher);
                    break;
                case 2:
                    listOfGroups(teacher);
                    break;
                case 3:
                    listOfExaminations(teacher);
                    break;
                case 4:
                    listOfStudentsFromGroup(teacher);
                    break;
                case 5:
                    addNewExamination(teacher);
                    break;
                case 6:
                    deleteExamination(teacher);
                    break;
                case 7:
                    addGrade(teacher);
                    break;
                case 8:
                    return;
            }
        }
    }

    private void listOfCoursesTeacher(Teacher teacher) throws IOException {
        for (Course c:teacher.getLedCourses()) {
            System.out.println(c.getCourseName());
        }
        userInputReader.readLine();
    }

    private void listOfGroups(Teacher teacher) throws IOException {
        for (Group g:teacher.getTeachedGroups()) {
            System.out.println(g.getCourse().getCourseName() + ", " + g.getGroupTime() + ", Id grupy: " + g.getId());
        }
        userInputReader.readLine();
    }

    private void listOfExaminations(Teacher teacher) throws IOException {
        for (Group g:teacher.getTeachedGroups()) {
            for (Examination e:g.getExaminations()) {
                System.out.println("ExamId: " + e.getId() + ", " + g.getCourse().getCourseName() + ", Id grupy: " + g.getId() + ", "
                        + e.getDescription() + ", " + e.getDate());
            }
        }
        userInputReader.readLine();
    }

    private void addNewExamination(Teacher teacher) throws IOException {
        System.out.println("Enter group Id: ");
        int groupId = Integer.parseInt(userInputReader.readLine());
        Group g = groupDao.find(groupId);
        System.out.println("Enter date(rrrr-mm-dd): ");
        String date = userInputReader.readLine().trim();
        System.out.println("Description of exam: ");
        String desc = userInputReader.readLine().trim();

        examinationDao.create(g,Date.valueOf(date),desc);
    }

    private void deleteExamination(Teacher teacher) throws IOException {
        System.out.println("Enter examination Id: ");
        int examId = Integer.parseInt(userInputReader.readLine());
        examinationDao.delete(examId);
    }

    private void addGrade(Teacher teacher) throws IOException {
        System.out.println("Enter Id of exam: ");
        int examId = Integer.parseInt(userInputReader.readLine());
        Examination exam = examinationDao.find(examId);
        System.out.println("Enter Index number of Student: ");
        int index = Integer.parseInt(userInputReader.readLine());
        Student student = studentDao.findWithIndex(index);
        System.out.println("Enter result: ");
        double result = Double.parseDouble(userInputReader.readLine());

        examinationDao.addGrade(exam,student,result);
    }

    private void listOfStudentsFromGroup(Teacher teacher) throws IOException {
        System.out.println("Enter Id of Group: ");
        int id = Integer.parseInt(userInputReader.readLine());

        Group g = groupDao.find(id);

        for (Student s:g.getStudents()) {
            System.out.println(s.getPersonData().getName() + " " + s.getPersonData().getSurname() + ", Indeks: " + s.getIndexNumber());
        }
        userInputReader.readLine();
    }

    private void studentRun(Student student) throws IOException {
        while(true) {
            System.out.println("Enter an option: \n"
                    + "1) Show list of your courses. \n"
                    + "2) Show your grades\n"
                    + "3) End program\n");

            int option = Integer.parseInt(userInputReader.readLine());

            switch (option) {
                case 1:
                    listOfCoursesStudent(student);
                    break;
                case 2:
                    listOfGradesStudent(student);
                    break;
                case 3:
                    return;
            }
        }
    }

    private void listOfCoursesStudent(Student student) throws IOException {
        for (Group g:student.getGroups()) {
            System.out.println(g.getCourse().getCourseName() + ", " + g.getTeacher().getPersonData().getName() + " " +
                    g.getTeacher().getPersonData().getSurname());
        }
        userInputReader.readLine();
    }

    private void listOfGradesStudent(Student student) throws IOException {
        for (Grade g:student.getGrades()) {
            System.out.println(g.getExamination().getGroup().getCourse().getCourseName() + ", " + g.getExamination().getDescription() + " " +
                    g.getExamination().getDate() + " - " + g.getResult());
        }
        userInputReader.readLine();
    }
}
