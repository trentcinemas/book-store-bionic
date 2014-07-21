package dao;

import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import entities.UserGroup;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
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
        List<User> result =query.list();
        session.close();
        return result;
    }

    @Override
    public User selectById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.id=" +
                Integer.toString(id));
        if (!query.list().isEmpty()) {
            User result=(User)query.list().get(0);
            session.close();
            return result;
        }
        else session.close();
        return null;
    }

    @Override
    public User selectByEmail(String email) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.email='" +
                email + "'");
        if (!query.list().isEmpty()){
            User result = (User)query.list().get(0);
            session.close();
            return result;
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
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(user);
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
        }
        finally {
            session.close();
        }
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

    @Override
    public boolean exist(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query= session.createQuery("From User where email="+"'"+email+"'");
        if (!query.list().isEmpty()){
            session.close();
            return true;
        }
        else {
            session.close();
            return false;
        }
    }
}
