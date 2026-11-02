package com.campus.servlet;

import com.campus.dao.DepartmentDAO;
import com.campus.model.Faculty;
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

@WebServlet("/add-faculty")
public class AddFacultyServlet extends HttpServlet {

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

        Faculty faculty = new Faculty();

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(exchange, request.getLocale());
        context.setVariable("faculty", faculty);
        context.setVariable("departments", departmentDAO.getAllDepartments());
        context.setVariable("activeTab", "faculty");

        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("faculty-form", context, response.getWriter());
    }
}
