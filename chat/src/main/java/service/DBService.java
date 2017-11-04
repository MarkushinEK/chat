package service;

import dataset.DataSet;

public interface DBService {

    void addData(DataSet data);

    boolean checkLoginInDatabase(String login);

    boolean checkLoginInDatabase(String login, String pass);


}
