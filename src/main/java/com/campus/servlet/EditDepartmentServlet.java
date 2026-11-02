package com.campus.servlet;

import com.campus.dao.DepartmentDAO;
import com.campus.model.Department;
import com.campus.util.ThymeleafConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.web.IWebExchange;
import org.thymeleaf.web.servlet.JakartaServletWebApplication;

import java.io.IOException;

@WebServlet("/edit-department")
public class EditDepartmentServlet extends HttpServlet {

    private DepartmentDAO departmentDAO;
    private TemplateEngine templateEngine;

    @Override
    public void init() {
        departmentDAO = new DepartmentDAO();
        templateEngine = ThymeleafConfig.getTemplateEngine();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/departments");
            return;
        }

        int id = Integer.parseInt(idParam);
        Department department = departmentDAO.getDepartmentById(id);

        if (department == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Department not found");
            return;
        }

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(exchange, request.getLocale());
        context.setVariable("department", department);
        context.setVariable("activeTab", "departments");

        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("department-form", context, response.getWriter());
    }
}
