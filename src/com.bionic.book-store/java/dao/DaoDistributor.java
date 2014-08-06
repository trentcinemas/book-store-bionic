    package dao;

import dao.daoInterfaces.DaoDistributorInterface;
import entities.Distributor;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Джон on 23.07.2014.
 */
public class DaoDistributor implements DaoDistributorInterface {
    @Override
    public List<Distributor> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Distributor");
        List<Distributor> result = query.list();
        session.close();
        return result;

    }

    @Override
    public Distributor selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select distr from Distributor distr where id="+ id);
        if (!query.list().isEmpty()) {
            Distributor result = (Distributor) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public Distributor selectByUrl(String url) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select distr from Distributor distr where url="+ url);
        if (!query.list().isEmpty()) {
            Distributor result = (Distributor) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public void insert(Distributor distributor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("Distributor", distributor);
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
    public void update(Distributor distributor) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(distributor);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Distributor distributor) {
        Session session= HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(distributor);
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
        Query query = session.createQuery("select distributor from Distributor distributor where id=" + id);
        Distributor distributor = (Distributor) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(distributor);
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
