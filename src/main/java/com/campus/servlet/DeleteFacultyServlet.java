package com.campus.servlet;

import com.campus.dao.FacultyDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-faculty")
public class DeleteFacultyServlet extends HttpServlet {

    private FacultyDAO facultyDAO;

    @Override
    public void init() {
        facultyDAO = new FacultyDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isBlank()) {
            int id = Integer.parseInt(idParam);
            facultyDAO.deleteFaculty(id);
        }

        response.sendRedirect(request.getContextPath() + "/faculty");
    }
}
