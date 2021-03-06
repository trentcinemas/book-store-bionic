package dao.daoInterfaces;

import entities.UserGroup;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoUserGroupInterface {
    public List<UserGroup> selectAll();
    void insert(UserGroup user);
    void update(UserGroup user);
    void delete(UserGroup user);
    void delete(int id);
}
