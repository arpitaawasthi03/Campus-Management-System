package com.campus.servlet;

import com.campus.dao.CourseDAO;
import com.campus.model.Course;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save-course")
public class SaveCourseServlet extends HttpServlet {

    private CourseDAO courseDAO;

    @Override
    public void init() {
        courseDAO = new CourseDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String courseCode = request.getParameter("courseCode");
        String courseName = request.getParameter("courseName");
        String department = request.getParameter("department");
        String creditsParam = request.getParameter("credits");
        String instructor = request.getParameter("instructor");
        String description = request.getParameter("description");

        int id = (idParam != null && !idParam.isBlank()) ? Integer.parseInt(idParam) : 0;
        int credits = (creditsParam != null && !creditsParam.isBlank()) ? Integer.parseInt(creditsParam) : 3;

        Course course = new Course();
        course.setId(id);
        course.setCourseCode(courseCode != null ? courseCode.trim() : "");
        course.setCourseName(courseName != null ? courseName.trim() : "");
        course.setDepartment(department != null ? department.trim() : "");
        course.setCredits(credits);
        course.setInstructor(instructor != null ? instructor.trim() : "");
        course.setDescription(description != null ? description.trim() : "");

        if (id == 0) {
            courseDAO.saveCourse(course);
        } else {
            courseDAO.updateCourse(course);
        }

        response.sendRedirect(request.getContextPath() + "/courses");
    }
}
