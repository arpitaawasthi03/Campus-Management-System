package com.campus.servlet;

import com.campus.dao.CourseDAO;
import com.campus.dao.DepartmentDAO;
import com.campus.dao.FacultyDAO;
import com.campus.dao.StudentDAO;
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

@WebServlet(urlPatterns = {"/dashboard", ""})
public class DashboardServlet extends HttpServlet {

    private StudentDAO studentDAO;
    private CourseDAO courseDAO;
    private DepartmentDAO departmentDAO;
    private FacultyDAO facultyDAO;
    private TemplateEngine templateEngine;

    @Override
    public void init() {
        studentDAO = new StudentDAO();
        courseDAO = new CourseDAO();
        departmentDAO = new DepartmentDAO();
        facultyDAO = new FacultyDAO();
        templateEngine = ThymeleafConfig.getTemplateEngine();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        
        long studentCount = studentDAO.getStudentCount();
        long courseCount = courseDAO.getCourseCount();
        long departmentCount = departmentDAO.getDepartmentCount();
        long facultyCount = facultyDAO.getFacultyCount();

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(exchange, request.getLocale());
        context.setVariable("studentCount", studentCount);
        context.setVariable("courseCount", courseCount);
        context.setVariable("departmentCount", departmentCount);
        context.setVariable("facultyCount", facultyCount);
        context.setVariable("activeTab", "dashboard");

        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("dashboard", context, response.getWriter());
    }
}
