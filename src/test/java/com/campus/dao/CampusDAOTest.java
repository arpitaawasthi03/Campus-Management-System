package com.campus.dao;

import com.campus.model.Course;
import com.campus.model.Department;
import com.campus.model.Faculty;
import com.campus.model.Student;
import com.campus.util.HibernateUtil;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CampusDAOTest {

    private static StudentDAO studentDAO;
    private static CourseDAO courseDAO;
    private static DepartmentDAO departmentDAO;
    private static FacultyDAO facultyDAO;

    @BeforeAll
    public static void setUp() {
        // Initializes Hibernate SessionFactory using test classpath hibernate.cfg.xml (H2 in-memory)
        HibernateUtil.getSessionFactory();

        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
        departmentDAO = new DepartmentDAO();
        facultyDAO = new FacultyDAO();
    }

    @AfterAll
    public static void tearDown() {
        HibernateUtil.shutdown();
    }

    @Test
    public void testStudentCRUD() {
        // 1. Create
        Student student = new Student("STU-101", "Ada", "Lovelace", "ada@campus.edu", "Computer Science", 3, "+15550100", 3.95);
        studentDAO.saveStudent(student);
        assertTrue(student.getId() > 0, "Student ID should be generated");

        // 2. Read One
        Student found = studentDAO.getStudentById(student.getId());
        assertNotNull(found, "Saved student should be retrieved");
        assertEquals("Ada", found.getFirstName());
        assertEquals("Lovelace", found.getLastName());
        assertEquals("Ada Lovelace", found.getFullName());

        // 3. Update
        found.setGpa(4.00);
        found.setSemester(4);
        studentDAO.updateStudent(found);

        Student updated = studentDAO.getStudentById(found.getId());
        assertEquals(4.00, updated.getGpa(), 0.001);
        assertEquals(4, updated.getSemester());

        // 4. Read All
        List<Student> students = studentDAO.getAllStudents();
        assertFalse(students.isEmpty(), "Student list should not be empty");

        // 5. Delete
        int idToDelete = updated.getId();
        studentDAO.deleteStudent(idToDelete);
        assertNull(studentDAO.getStudentById(idToDelete), "Student should be null after deletion");
    }

    @Test
    public void testCourseCRUD() {
        Course course = new Course("CS-301", "Distributed Systems", "Computer Science", 4, "Dr. Leslie Lamport", "Concepts in distributed computing");
        courseDAO.saveCourse(course);
        assertTrue(course.getId() > 0);

        Course fetched = courseDAO.getCourseById(course.getId());
        assertNotNull(fetched);
        assertEquals("Distributed Systems", fetched.getCourseName());

        fetched.setCredits(3);
        courseDAO.updateCourse(fetched);
        assertEquals(3, courseDAO.getCourseById(fetched.getId()).getCredits());

        assertTrue(courseDAO.getAllCourses().size() >= 1);
        courseDAO.deleteCourse(fetched.getId());
        assertNull(courseDAO.getCourseById(fetched.getId()));
    }

    @Test
    public void testDepartmentCRUD() {
        Department dept = new Department("CSE", "Computer Science & Engineering", "Dr. Margaret Hamilton", "cse@campus.edu", "Building A");
        departmentDAO.saveDepartment(dept);
        assertTrue(dept.getId() > 0);

        Department fetched = departmentDAO.getDepartmentById(dept.getId());
        assertNotNull(fetched);
        assertEquals("CSE", fetched.getCode());

        fetched.setBuilding("Building C, Wing 2");
        departmentDAO.updateDepartment(fetched);
        assertEquals("Building C, Wing 2", departmentDAO.getDepartmentById(fetched.getId()).getBuilding());

        assertTrue(departmentDAO.getDepartmentCount() >= 1);
        departmentDAO.deleteDepartment(fetched.getId());
        assertNull(departmentDAO.getDepartmentById(fetched.getId()));
    }

    @Test
    public void testFacultyCRUD() {
        Faculty faculty = new Faculty("FAC-007", "Alan Turing", "aturing@campus.edu", "Mathematics", "Distinguished Professor", "Room 101");
        facultyDAO.saveFaculty(faculty);
        assertTrue(faculty.getId() > 0);

        Faculty fetched = facultyDAO.getFacultyById(faculty.getId());
        assertNotNull(fetched);
        assertEquals("Alan Turing", fetched.getName());

        fetched.setDesignation("Chair Professor");
        facultyDAO.updateFaculty(fetched);
        assertEquals("Chair Professor", facultyDAO.getFacultyById(fetched.getId()).getDesignation());

        assertTrue(facultyDAO.getFacultyCount() >= 1);
        facultyDAO.deleteFaculty(fetched.getId());
        assertNull(facultyDAO.getFacultyById(fetched.getId()));
    }
}
