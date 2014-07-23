package dao;

import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import entities.UserGroup;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by jsarafajr on 17.07.14.
 */
public class DaoUser implements DaoUserInterface {

    public DaoUser() {

    }

    @Override
    public List<User> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User");
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public User selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.id="+ id);
        if (!query.list().isEmpty()) {
            User result = (User) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public User selectByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.email='" +
                email + "'");
        if (!query.list().isEmpty()) {
            User result = (User) query.list().get(0);
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
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
            if(session!=null)
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(User user) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            if (!session.getTransaction().wasCommitted())
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null)
            session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where id=" + id);
        User user = (User) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(user);
            if (!session.getTransaction().wasCommitted())
            session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null)
            session.getTransaction().rollback();
        }
        finally{
            session.close();
        }
    }

    @Override
    public User selectByUserGroup(UserGroup group) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.userGroupByGroupId=" +
                group);
        if (!query.list().isEmpty()) {
            User result = (User) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public User selectByUserGroupType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.userGroupByGroupId.type=" +
                type);
        if (!query.list().isEmpty()) {
            User result = (User) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public boolean exist(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From User where email=" + "'" + email + "'");
        if (!query.list().isEmpty()) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }
}
