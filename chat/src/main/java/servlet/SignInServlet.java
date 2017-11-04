package servlet;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import org.eclipse.jetty.websocket.servlet.WebSocketServlet;
import org.eclipse.jetty.websocket.servlet.WebSocketServletFactory;
import service.ChatService;
import service.ChatServiceImpl;
import service.DBService;
import websocket.ChatWebSocket;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(urlPatterns = {"/chat"})
public class SignInServlet extends WebSocketServlet {
    private final static int LOGOUT_TIME = 10 * 60 * 1000;

    private DBService dbService;
    private ChatService chatService;

    public SignInServlet(DBService dbService) {
        this.dbService = dbService;
        this.chatService = new ChatServiceImpl();
    }

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if (dbService.checkLoginInDatabase(req.getParameter("login"), req.getParameter("pass"))) {
            Configuration cfg = new Configuration();
            Template template = cfg.getTemplate("templates/chat.html");
            Map<String, String> data = new HashMap<>();
            data.put("login", req.getParameter("login"));
            try {
                template.process(data, resp.getWriter());
            } catch (TemplateException e) {
                e.printStackTrace();
            }
        }
        else {
            resp.getWriter().println("Wrong login or pass");
        }
    }

    @Override
    public void configure(WebSocketServletFactory webSocketServletFactory) {
        webSocketServletFactory.getPolicy().setIdleTimeout(LOGOUT_TIME);
        webSocketServletFactory.setCreator((req, resp) -> new ChatWebSocket(chatService));
    }
}
