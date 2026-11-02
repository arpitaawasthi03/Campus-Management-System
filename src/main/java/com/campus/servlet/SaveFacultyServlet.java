package com.campus.servlet;

import com.campus.dao.FacultyDAO;
import com.campus.model.Faculty;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save-faculty")
public class SaveFacultyServlet extends HttpServlet {

    private FacultyDAO facultyDAO;

    @Override
    public void init() {
        facultyDAO = new FacultyDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String facultyId = request.getParameter("facultyId");
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String department = request.getParameter("department");
        String designation = request.getParameter("designation");
        String officeRoom = request.getParameter("officeRoom");

        int id = (idParam != null && !idParam.isBlank()) ? Integer.parseInt(idParam) : 0;

        Faculty faculty = new Faculty();
        faculty.setId(id);
        faculty.setFacultyId(facultyId != null ? facultyId.trim() : "");
        faculty.setName(name != null ? name.trim() : "");
        faculty.setEmail(email != null ? email.trim() : "");
        faculty.setDepartment(department != null ? department.trim() : "");
        faculty.setDesignation(designation != null ? designation.trim() : "");
        faculty.setOfficeRoom(officeRoom != null ? officeRoom.trim() : "");

        if (id == 0) {
            facultyDAO.saveFaculty(faculty);
        } else {
            facultyDAO.updateFaculty(faculty);
        }

        response.sendRedirect(request.getContextPath() + "/faculty");
    }
}
