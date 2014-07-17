package util;

import dao.DaoUser;

/**
 * Created by Джон on 17.07.2014.
 */
public class DaoFactory {
    private static DaoUser userDAO = new DaoUser();
    public static DaoUser getDaoUserInstance(){
        return userDAO;
    }
}
