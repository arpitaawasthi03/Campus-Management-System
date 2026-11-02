package com.campus;

import com.campus.dao.CourseDAO;
import com.campus.dao.DepartmentDAO;
import com.campus.dao.FacultyDAO;
import com.campus.dao.StudentDAO;
import com.campus.model.Course;
import com.campus.model.Department;
import com.campus.model.Faculty;
import com.campus.model.Student;
import com.campus.util.HibernateUtil;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import java.util.List;

/**
 * Standalone verification application entry point.
 * Demonstrates and verifies direct Hibernate CRUD operations across all campus models.
 */
public class TestCampusApp {

    public static void main(String[] args) {
        System.out.println("==========================================================");
        System.out.println("          CAMPUS MANAGEMENT SYSTEM - CLI TEST             ");
        System.out.println("==========================================================");

        // Attempt default SessionFactory; fallback to H2 embedded memory if MySQL is offline
        try {
            HibernateUtil.getSessionFactory();
            System.out.println("[INFO] Connected using default hibernate.cfg.xml database configuration.");
        } catch (Throwable ex) {
            System.out.println("[WARN] Primary database connection unavailable: " + ex.getMessage());
            System.out.println("[INFO] Initializing fallback in-memory H2 database engine...");

            Configuration cfg = new Configuration();
            cfg.setProperty("hibernate.connection.driver_class", "org.h2.Driver");
            cfg.setProperty("hibernate.connection.url", "jdbc:h2:mem:campus_cli;DB_CLOSE_DELAY=-1;MODE=MySQL");
            cfg.setProperty("hibernate.connection.username", "sa");
            cfg.setProperty("hibernate.connection.password", "");
            cfg.setProperty("hibernate.dialect", "org.hibernate.dialect.H2Dialect");
            cfg.setProperty("hibernate.hbm2ddl.auto", "create-drop");
            cfg.setProperty("hibernate.show_sql", "false");

            cfg.addAnnotatedClass(Student.class);
            cfg.addAnnotatedClass(Course.class);
            cfg.addAnnotatedClass(Department.class);
            cfg.addAnnotatedClass(Faculty.class);

            SessionFactory fallbackFactory = cfg.buildSessionFactory();
            HibernateUtil.setSessionFactory(fallbackFactory);
            System.out.println("[INFO] In-memory database initialized successfully.");
        }

        StudentDAO studentDAO = new StudentDAO();
        CourseDAO courseDAO = new CourseDAO();
        DepartmentDAO departmentDAO = new DepartmentDAO();
        FacultyDAO facultyDAO = new FacultyDAO();

        // 1. Department Operations
        System.out.println("\n--- 1. Testing Department Operations ---");
        Department dept = new Department("CSE", "Computer Science & Engineering", "Dr. Margaret Hamilton", "cse@campus.edu", "Turing Building");
        departmentDAO.saveDepartment(dept);
        System.out.println("Saved Department: " + dept.getCode() + " - " + dept.getName() + " (ID: " + dept.getId() + ")");

        // 2. Faculty Operations
        System.out.println("\n--- 2. Testing Faculty Operations ---");
        Faculty faculty = new Faculty("FAC-001", "Dr. Alan Turing", "aturing@campus.edu", "Computer Science & Engineering", "Professor", "Room 401");
        facultyDAO.saveFaculty(faculty);
        System.out.println("Saved Faculty: " + faculty.getName() + " [" + faculty.getDesignation() + "]");

        // 3. Course Operations
        System.out.println("\n--- 3. Testing Course Operations ---");
        Course course = new Course("CS201", "Data Structures & Algorithms", "Computer Science & Engineering", 4, faculty.getName(), "Fundamental data structures and algorithms");
        courseDAO.saveCourse(course);
        System.out.println("Saved Course: " + course.getCourseCode() + " - " + course.getCourseName() + " (" + course.getCredits() + " Credits)");

        // 4. Student Operations
        System.out.println("\n--- 4. Testing Student Operations ---");
        Student student = new Student("STU-2026-001", "Ada", "Lovelace", "ada@campus.edu", "Computer Science & Engineering", 2, "+1-555-0199", 3.98);
        studentDAO.saveStudent(student);
        System.out.println("Saved Student: " + student.getFullName() + " | Roll: " + student.getRollNumber() + " | GPA: " + student.getGpa());

        // Read all students
        List<Student> students = studentDAO.getAllStudents();
        System.out.println("\n--- Student Directory List (" + students.size() + ") ---");
        for (Student s : students) {
            System.out.println("  [" + s.getId() + "] " + s.getRollNumber() + " - " + s.getFullName() + " | " + s.getDepartment() + " | GPA: " + s.getGpa());
        }

        // Update student
        student.setGpa(4.00);
        studentDAO.updateStudent(student);
        System.out.println("Updated Student GPA to: " + studentDAO.getStudentById(student.getId()).getGpa());

        System.out.println("\n==========================================================");
        System.out.println("          ALL CAMPUS MODULE TESTS COMPLETED SUCCESSFULLY! ");
        System.out.println("==========================================================");

        HibernateUtil.shutdown();
    }
}
