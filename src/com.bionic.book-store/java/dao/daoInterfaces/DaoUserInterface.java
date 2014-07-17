package dao.daoInterfaces;

import entities.User;

import java.util.List;

/**
 * Created by Джон on 16.07.2014.
 */
public interface DaoUserInterface {
    public List<User> selectAll();
}
