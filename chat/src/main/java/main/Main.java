package main;

import org.eclipse.jetty.server.Handler;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.handler.HandlerList;
import org.eclipse.jetty.server.handler.ResourceHandler;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import service.ChatService;
import service.ChatServiceImpl;
import service.DBService;
import service.DBServiceImpl;
import servlet.RegistrationServlet;
import servlet.SignInServlet;

public class Main {

    public static void main(String[] args) throws Exception {
        DBService dbService = new DBServiceImpl();
        ChatService chatService = new ChatServiceImpl();

        Server server = new Server(8080);
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);

        context.addServlet(new ServletHolder(new SignInServlet(dbService)), "/chat");
        context.addServlet(new ServletHolder(new RegistrationServlet(dbService)), "/registration");

        ResourceHandler resource_handler = new ResourceHandler();
        resource_handler.setDirectoriesListed(true);
        resource_handler.setWelcomeFiles(new String[]{"index.html"});
        resource_handler.setResourceBase("public_html");

        HandlerList handlers = new HandlerList();
        handlers.setHandlers(new Handler[]{resource_handler, context});
        server.setHandler(handlers);

        server.start();
        System.out.println("Server started");
        server.join();
    }
}
