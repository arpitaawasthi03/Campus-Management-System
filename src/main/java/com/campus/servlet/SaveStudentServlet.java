package com.campus.servlet;

import com.campus.dao.StudentDAO;
import com.campus.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save-student")
public class SaveStudentServlet extends HttpServlet {

    private StudentDAO studentDAO;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String rollNumber = request.getParameter("rollNumber");
        String firstName = request.getParameter("firstName");
        String lastName = request.getParameter("lastName");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String semesterParam = request.getParameter("semester");
        String phone = request.getParameter("phone");
        String gpaParam = request.getParameter("gpa");

        int id = (idParam != null && !idParam.isBlank()) ? Integer.parseInt(idParam) : 0;
        int semester = (semesterParam != null && !semesterParam.isBlank()) ? Integer.parseInt(semesterParam) : 1;
        double gpa = (gpaParam != null && !gpaParam.isBlank()) ? Double.parseDouble(gpaParam) : 0.0;

        Student student = new Student();
        student.setId(id);
        student.setRollNumber(rollNumber != null ? rollNumber.trim() : "");
        student.setFirstName(firstName != null ? firstName.trim() : "");
        student.setLastName(lastName != null ? lastName.trim() : "");
        student.setEmail(email != null ? email.trim() : "");
        student.setDepartment(department != null ? department.trim() : "");
        student.setSemester(semester);
        student.setPhone(phone != null ? phone.trim() : "");
        student.setGpa(gpa);

        if (id == 0) {
            studentDAO.saveStudent(student);
        } else {
            studentDAO.updateStudent(student);
        }

        response.sendRedirect(request.getContextPath() + "/students");
    }
}
