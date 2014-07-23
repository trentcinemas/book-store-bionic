package dao;

import dao.daoInterfaces.DaoUserGroupInterface;
import entities.UserGroup;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Джон on 23.07.2014.
 */
public class DaoUserGroup implements DaoUserGroupInterface {
    @Override
    public List<UserGroup> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM UserGroup");
        List<UserGroup> result = query.list();
        session.close();
        return result;

    }

    // в адмінку напевно, бо не знаю, де ще пригодиться
    @Override
    public void insert(UserGroup userGroup) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("UserGroup", userGroup);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if(session!=null)
                session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public void update(UserGroup userGroup) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(userGroup);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(UserGroup userGroup) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(userGroup);
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
        Query query = session.createQuery("select userGr from UserGroup userGr where id=" + id);
        UserGroup userGroup = (UserGroup) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(userGroup);
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
}
