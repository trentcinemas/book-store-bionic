package dao;

import dao.daoInterfaces.DaoPurchasedBookInterface;
import entities.Book;
import entities.PurchasedBook;
import entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Джон on 23.07.2014.
 */
public class DaoPurchasedBook implements DaoPurchasedBookInterface {

    public List<PurchasedBook> getLast() {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        query = session.createQuery("FROM PurchasedBook order by date desc ");
        if (!query.list().isEmpty()) {
            List<PurchasedBook> result = query.list();
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }


    public boolean exist(User user, Book book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from PurchasedBook where userByUserId.id=" + user.getUserId() +
                "and bookByBookId.id=" + book.getBookId());

        List<PurchasedBook> books = query.list();
        session.close();
        return books.size() != 0;
    }

    @Override
    public PurchasedBook selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select pbook from PurchasedBook pBook where id="+ id);
        if (!query.list().isEmpty()) {
            PurchasedBook result = (PurchasedBook) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;

    }

    @Override
    public List<PurchasedBook> selectByUser(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select pBook from PurchasedBook pBook where pBook.userByUserId="+ user);
        if (!query.list().isEmpty()) {
            List<PurchasedBook> result = (List<PurchasedBook>) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;

    }

    @Override
    public List<PurchasedBook> selectByUserId(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select pBook from PurchasedBook pBook where pBook.userByUserId.id="+ id);
        if (!query.list().isEmpty()) {
            List<PurchasedBook> result = query.list();
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public List<PurchasedBook> selectByDate(Date date) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select pBook from PurchasedBook pBook where pBook.date="+ date);
        if (!query.list().isEmpty()) {
            List<PurchasedBook> result = (List<PurchasedBook>) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public List<PurchasedBook> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from PurchasedBook");
        if (!query.list().isEmpty()) {
            List<PurchasedBook> result = query.list();
            session.close();
            return result;
        } else session.close();
        return null;

    }

    @Override
    public void insert(PurchasedBook book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("PurchasedBook", book);
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
    public void update(PurchasedBook book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(book);
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
    public void delete(PurchasedBook book) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(book);
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
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query =session.createQuery("from PurchasedBook where id="+id);
        PurchasedBook book = (PurchasedBook)query.list().get(0);
        try {
            session.beginTransaction();
            session.persist(book);
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
}
