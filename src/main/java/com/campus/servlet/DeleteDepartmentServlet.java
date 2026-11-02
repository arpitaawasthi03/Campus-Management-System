package com.campus.servlet;

import com.campus.dao.DepartmentDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/delete-department")
public class DeleteDepartmentServlet extends HttpServlet {

    private DepartmentDAO departmentDAO;

    @Override
    public void init() {
        departmentDAO = new DepartmentDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam != null && !idParam.isBlank()) {
            int id = Integer.parseInt(idParam);
            departmentDAO.deleteDepartment(id);
        }

        response.sendRedirect(request.getContextPath() + "/departments");
    }
}
