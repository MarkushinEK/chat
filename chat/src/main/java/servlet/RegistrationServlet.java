package servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import dataset.DataSet;
import service.DBService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class RegistrationServlet extends HttpServlet {

    private DBService dbService;

    public RegistrationServlet(DBService dbService) {
        this.dbService = dbService;
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Configuration cfg = new Configuration();
        Template template = cfg.getTemplate("templates/registration.html");
        template.dump(resp.getWriter());
    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        DataSet data = new DataSet(req.getParameter("login"), req.getParameter("pass"));
        if (dbService.checkLoginInDatabase(req.getParameter("login"))) {
            resp.getWriter().println("Login is already in use.");
            resp.setStatus(200);
        }
        else {
            dbService.addData(data);
            resp.getWriter().println("Registration is successfully!");
            resp.setStatus(200);
        }


    }


}
