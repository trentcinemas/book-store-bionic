package dao;

import dao.daoInterfaces.DaoReplyMessageInterface;
import entities.ReplyMessage;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Eklerka on 7/24/2014.
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
    public ReplyMessage selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select replyMessage from ReplyMessage replyMessage"+
                " where replyMessage.messageId="+ id);
        if (!query.list().isEmpty()) {
            ReplyMessage result = (ReplyMessage) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public void insert(ReplyMessage replyMessage) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("ReplyMessage", replyMessage);
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
    public void update(ReplyMessage replyMessage) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(replyMessage);
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
    public void delete(ReplyMessage replyMessage) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(replyMessage);
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
        Query query = session.createQuery("select replyMessage " +
                "from ReplyMessage replyMessage where replyMessage.messageId=" + id);
        ReplyMessage replyMessage = (ReplyMessage) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(replyMessage);
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
    public List<ReplyMessage> selectByReceiver(String receiver) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("SELECT replyMessage FROM ReplyMessage " +
                "replyMessage WHERE replyMessage.receiver=" + receiver);
        List<ReplyMessage> result = query.list();
        session.close();
        return result;
    }
}
