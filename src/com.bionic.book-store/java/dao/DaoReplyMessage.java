package dao;

import dao.daoInterfaces.DaoReplyMessageInterface;
import entities.ReplyMessage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Evgeniy Baranuk on 26.07.14.
 */
public class DaoReplyMessage implements DaoReplyMessageInterface {

    @Override
    public List<ReplyMessage> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM ReplyMessage");
        List<ReplyMessage> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<ReplyMessage> getModeratorMessages() {
        String moder = "moder";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select msg from ReplyMessage msg where msg.receiver="+ moder);
        List<ReplyMessage> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<ReplyMessage> getAdminMessages() {
        String moder = "admin";
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select msg from ReplyMessage msg where msg.receiver="+ moder);
        List<ReplyMessage> result = query.list();
        session.close();
        return result;
    }

    @Override
    public ReplyMessage selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select msg from ReplyMessage msg where msg.id="+ id);
        if (!query.list().isEmpty()) {
            ReplyMessage result = (ReplyMessage) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public void insert(ReplyMessage message) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("ReplyMessage", message);
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
    public void update(ReplyMessage message) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(message);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(ReplyMessage message) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(message);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session != null)
                session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select msg from ReplyMessage msg where id=" + id);
        ReplyMessage msg = (ReplyMessage) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(msg);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session != null)
                session.getTransaction().rollback();
        }
        finally{
            session.close();
        }
    }
}
