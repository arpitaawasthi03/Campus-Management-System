package com.campus.servlet;

import com.campus.dao.DepartmentDAO;
import com.campus.model.Department;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/save-department")
public class SaveDepartmentServlet extends HttpServlet {

    private DepartmentDAO departmentDAO;

    @Override
    public void init() {
        departmentDAO = new DepartmentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        String code = request.getParameter("code");
        String name = request.getParameter("name");
        String headOfDepartment = request.getParameter("headOfDepartment");
        String contactEmail = request.getParameter("contactEmail");
        String building = request.getParameter("building");

        int id = (idParam != null && !idParam.isBlank()) ? Integer.parseInt(idParam) : 0;

        Department department = new Department();
        department.setId(id);
        department.setCode(code != null ? code.trim() : "");
        department.setName(name != null ? name.trim() : "");
        department.setHeadOfDepartment(headOfDepartment != null ? headOfDepartment.trim() : "");
        department.setContactEmail(contactEmail != null ? contactEmail.trim() : "");
        department.setBuilding(building != null ? building.trim() : "");

        if (id == 0) {
            departmentDAO.saveDepartment(department);
        } else {
            departmentDAO.updateDepartment(department);
        }

        response.sendRedirect(request.getContextPath() + "/departments");
    }
}
