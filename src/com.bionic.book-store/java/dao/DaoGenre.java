package dao;

import dao.daoInterfaces.DaoGenreInterface;
import entities.Genre;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Джон on 23.07.2014.
 */
public class DaoGenre implements DaoGenreInterface {
    @Override
    public List<Genre> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Genre");
        List<Genre> result = query.list();
        session.close();
        return result;
    }

    @Override
    public Genre selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select genre from Genre genre where id="+ id);
        if (!query.list().isEmpty()) {
            Genre result = (Genre) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public void insert(Genre genre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("Genre", genre);
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
    public void update(Genre genre) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(genre);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Genre genre) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(genre);
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
        Query query = session.createQuery("select genre from Genre genre where id=" + id);
        Genre genre= (Genre) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(genre);
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
