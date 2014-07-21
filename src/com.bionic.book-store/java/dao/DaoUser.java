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

    }

    @Override
    public List<User> selectAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User");
        session.close();
        return query.list();
    }

    @Override
    public User selectById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.id=" +
                Integer.toString(id));
        if (!query.list().isEmpty()) {
            session.close();
            return (User) query.list().get(0);
        }
        else session.close();
        return null;
    }

    @Override
    public User selectByEmail(String email) {
        session.close();
        Query query = session.createQuery("select user from User user where user.email='" +
                email + "'");
        if (!query.list().isEmpty()){
            session.close();
            return (User) query.list().get(0);
        }

        else {
            session.close();
            return null;
        }
    }

    @Override
    public void insert(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("User", user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
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
<<<<<<< HEAD

    @Override
    public boolean exist(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("From User where email="+email);
        if (!query.list().isEmpty()){
            session.close();
            return true;
        }

        else {
            session.close();
            return false;
        }
    }
=======
<<<<<<< HEAD
=======
    
>>>>>>> b378d0763d87f0f21cc7ebeb564f671998ab7340
>>>>>>> 5aaf60e539d135ac30b3e53a1b32a0b923c7c955
}
