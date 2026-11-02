package com.campus.servlet;

import com.campus.dao.DepartmentDAO;
import com.campus.dao.StudentDAO;
import com.campus.model.Student;
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

@WebServlet("/edit-student")
public class EditStudentServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private DepartmentDAO departmentDAO;
    private TemplateEngine templateEngine;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
        departmentDAO = new DepartmentDAO();
        templateEngine = ThymeleafConfig.getTemplateEngine();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        String idParam = request.getParameter("id");
        if (idParam == null || idParam.isBlank()) {
            response.sendRedirect(request.getContextPath() + "/students");
            return;
        }

        int id = Integer.parseInt(idParam);
        Student student = studentDAO.getStudentById(id);

        if (student == null) {
            response.sendError(HttpServletResponse.SC_NOT_FOUND, "Student not found");
            return;
        }

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(exchange, request.getLocale());
        context.setVariable("student", student);
        context.setVariable("departments", departmentDAO.getAllDepartments());
        context.setVariable("activeTab", "students");

        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("student-form", context, response.getWriter());
    }
}
