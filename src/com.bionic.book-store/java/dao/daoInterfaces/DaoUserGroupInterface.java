package dao.daoInterfaces;

import entities.User;
import entities.UserGroup;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoUserGroupInterface {
    List<User> selectUsersByType();
    List<User> selectUsersById();
    void insert(UserGroup user);
    void update(UserGroup user);
    void delete(UserGroup user);
    void delete(int id);
}
