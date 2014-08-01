package dao;

import dao.daoInterfaces.DaoCommentInterface;
import entities.Book;
import entities.Comment;
import entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public class DaoComments implements DaoCommentInterface {

    @Override
    public List<Comment> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Comment");
        List<Comment> result =query.list();
        session.close();
        return result;
    }

    @Override
    public Comment selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comment from Comment comment where comment.id=" +
                Integer.toString(id));
        if (!query.list().isEmpty()) {
            Comment result=(Comment)query.list().get(0);
            session.close();
            return result;
        }
        else session.close();
        return null;

    }

    @Override
    public List<Comment> selectByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comment from Comment comment where comment.userByUserId="+user);
        if (!query.list().isEmpty()) {
            List<Comment> result=query.list();
            session.close();
            return result;
        }
        else session.close();
        return null;
    }

    @Override
    public List<Comment> selectByUserId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comment from Comment comment where comment.userByUserId.id="+Integer.toString(id));
        if (!query.list().isEmpty()) {
            List<Comment> result=query.list();
            session.close();
            return result;
        }
        else session.close();
        return null;
    }

    @Override
    public List<Comment> selectByBook(Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comment from Comment comment where Comment.bookByBookId="+book);
        if (!query.list().isEmpty()) {
            List<Comment> result=query.list();
            session.close();
            return result;
        }
        else session.close();
        return null;
    }

    @Override
    public List<Comment> selectByBookId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comment from Comment comment where comment.bookByBookId.id="+Integer.toString(id));
        if (!query.list().isEmpty()) {
            List<Comment> result=query.list();
            session.close();
            return result;
        }
        else session.close();
        return null;
    }

    @Override
    public List<Comment> selectByDate(Date date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comment from Comment comment where Comment.date="+date);
        if (!query.list().isEmpty()) {
            List<Comment> result=query.list();
            session.close();
            return result;
        }
        else session.close();
        return null;
    }

    @Override
    public void insert(Comment comment) {
      Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("Comment", comment);
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
    public void update(Comment comment) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(comment);
            if(!session.getTransaction().wasCommitted())
            session.getTransaction().commit();
        }
        catch (HibernateException e) {
            if (session!=null) session.getTransaction().rollback();
        }
        finally {
            session.close();
        }
    }

    @Override
    public void delete(Comment comment) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(comment);
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
        Query query = session.createQuery("select comment from Comment comment where id=" + id);
        Comment comment = (Comment) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(comment);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (HibernateException e) {
            if(session!=null)
                session.getTransaction().rollback();
        }
        finally{
            session.close();
        }    }
}
