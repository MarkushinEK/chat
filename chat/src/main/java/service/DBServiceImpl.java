package service;

import dao.DAObject;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

import dataset.DataSet;

public class DBServiceImpl implements DBService {

    private SessionFactory sessionfactory;
    private Transaction trx;

    public DBServiceImpl() {
        Configuration config = new Configuration();
        config.addAnnotatedClass(DataSet.class);
        config.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        config.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        config.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/db");
        config.setProperty("hibernate.connection.username", "root");
        config.setProperty("hibernate.connection.password", "test");
        config.setProperty("hibernate.show_sql", "true");
        config.setProperty("hibernate.hbm2ddl.auto", "update");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder();
        builder.applySettings(config.getProperties());
        ServiceRegistry serviceRegistry = builder.build();
        sessionfactory = config.buildSessionFactory(serviceRegistry);
    }

    public void addData(DataSet data) {
        Session session = sessionfactory.openSession();
        trx = session.beginTransaction();
        DAObject dao = new DAObject(session);
        dao.saveData(data);
        trx.commit();
        session.close();
    }

    public boolean checkLoginInDatabase(String login) {
        Session session = sessionfactory.openSession();
        DAObject dao = new DAObject(session);
        boolean check = dao.checkLogin(login);
        session.close();
        return check;
    }

    public boolean checkLoginInDatabase(String login, String pass) {
        Session session = sessionfactory.openSession();
        DAObject dao = new DAObject(session);
        boolean check = dao.checkLoginAndPass(login, pass);
        session.close();
        return check;
    }
}
