package dao;

import org.hibernate.Criteria;
import org.hibernate.Session;

import org.hibernate.criterion.Restrictions;
import dataset.DataSet;

public class DAObject {
    Session session;

    public DAObject(Session session) {
        this.session = session;
    }

    public void saveData(DataSet dataset) {
        session.save(dataset);
    }

    public boolean checkLogin(String login) {
        Criteria criteria = session.createCriteria(DataSet.class);
        if ((DataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult() != null)
            return true;
        return false;
    }

    public boolean checkLoginAndPass(String login, String pass) {
        Criteria criteria = session.createCriteria(DataSet.class);
        if ((DataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult() != null)
            if (((DataSet) criteria.add(Restrictions.eq("login", login)).uniqueResult()).getPassword().equals(pass))
                return true;
        return false;
    }
}
