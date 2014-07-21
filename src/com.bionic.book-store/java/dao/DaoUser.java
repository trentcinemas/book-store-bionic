package dao;

import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import entities.UserGroup;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by jsarafajr on 17.07.14.
 */
public class DaoUser implements DaoUserInterface {
    private Session session;

    public DaoUser() {
        session = HibernateUtil.getSessionFactory().openSession();
    }

    @Override
    public List<User> selectAll() {
        Query query = session.createQuery("FROM User");
        return query.list();
    }

    @Override
    public User selectById(int id) {

        Query query = session.createQuery("select user from User user where user.id=" +
                Integer.toString(id));
        if (!query.list().isEmpty())
            return (User) query.list().get(0);
        else return null;
    }

    @Override
    public User selectByEmail(String email) {
        Query query = session.createQuery("select user from User user where user.email='" +
                email + "'");
        if (!query.list().isEmpty())
            return (User) query.list().get(0);
        else return null;
    }

    @Override
    public void insert(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("User", user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
    }

    @Override
    public void update(User user) {

    }

    @Override
    public void delete(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User selectByUserGroup(UserGroup group) {
        return null;
    }

    @Override
    public User selectByUserGroupType(String type) {
        return null;
    }
    
}
