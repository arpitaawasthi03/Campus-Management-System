package com.campus.servlet;

import com.campus.dao.DepartmentDAO;
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

@WebServlet("/add-student")
public class AddStudentServlet extends HttpServlet {

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

        Student student = new Student();
        student.setSemester(1);
        student.setGpa(0.0);

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
