package com.campus.servlet;

import com.campus.dao.FacultyDAO;
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
import java.util.List;

@WebServlet("/faculty")
public class FacultyServlet extends HttpServlet {

    private FacultyDAO facultyDAO;
    private TemplateEngine templateEngine;

    @Override
    public void init() {
        facultyDAO = new FacultyDAO();
        templateEngine = ThymeleafConfig.getTemplateEngine();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {

        List<Faculty> facultyList = facultyDAO.getAllFaculty();

        IWebExchange exchange = JakartaServletWebApplication
                .buildApplication(getServletContext())
                .buildExchange(request, response);

        WebContext context = new WebContext(exchange, request.getLocale());
        context.setVariable("facultyList", facultyList);
        context.setVariable("activeTab", "faculty");

        response.setContentType("text/html;charset=UTF-8");
        templateEngine.process("faculty", context, response.getWriter());
    }
}
