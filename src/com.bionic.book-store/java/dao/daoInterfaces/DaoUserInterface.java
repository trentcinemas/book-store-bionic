package dao.daoInterfaces;

import entities.User;
import entities.UserGroup;

import java.util.List;

/**
 * Created by Джон on 16.07.2014.
 */
public interface DaoUserInterface {
    public List<User> selectAll();
    public User selectById(int id);
    public User selectByEmail(String login);
    public List<User> selectByUserGroup(UserGroup group);
    public List<User> selectByUserGroupType(String type);
    boolean exist(String email);
    void insert(User user);
    void update(User user);
    void delete(User user);
    void delete(int id);
}
