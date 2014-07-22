package dao;

import dao.daoInterfaces.DaoBookInterface;
import entities.Book;
import entities.Distributor;
import entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by UFO on 22.07.2014.
 */
public class DaoBook implements DaoBookInterface {

    private Session session;

    @Override
    public List<Book> selectAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Book");
        if (!query.list().isEmpty()){
            List<Book> result = query.list();
            session.close();
            return result;
        }else{
            session.close();
            return null;
        }
    }

    @Override
    public Book selectById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book where book.id=" +
                Integer.toString(id));
        if (!query.list().isEmpty()) {
            Book result=(Book)query.list().get(0);
            session.close();
            return result;
        }
        else {
            session.close();
            return null;
        }
    }

    @Override
    public List<Book> selectByTitle(String title) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book where book.title='" +
                title + "'");
        if (!query.list().isEmpty()) {
            List <Book> result = query.list();
            session.close();
            return result;
        }
        else {
            session.close();
            return null;
        }
    }

    @Override
    public List<Book> selectByUser(User user) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book where book.title='" +
                user + "'");
        if (!query.list().isEmpty()) {
            List <Book> result = query.list();
            session.close();
            return result;
        }
        else {
            session.close();
            return null;
        }
    }

    @Override
    public List<Book> selectByUserId(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book join User user " +
                "where user.id = "+ Integer.toString(id));
        if(!query.list().isEmpty()){
            List<Book> result = query.list();
            session.close();
            return result;
        }
        else {
            session.close();
            return null;
        }
    }

    @Override
    public List<Book> selectByDistributor(Distributor distr) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book where book.title='" +
                distr + "'");
        if (!query.list().isEmpty()) {
            List <Book> result = query.list();
            session.close();
            return result;
        }
        else {
            session.close();
            return null;
        }
    }

    @Override
    public List<Book> selectByDistributorId(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book join Distributor distributor " +
                "where distributor.id = "+ Integer.toString(id));
        if(!query.list().isEmpty()){
            List<Book> result = query.list();
            session.close();
            return result;
        }
        else {
            session.close();
            return null;
        }
    }

    @Override
    public void insert(Book book) {
        session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("Book", book);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public void update(Book book) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(book);
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
    public void delete(Book book) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Book book where book = '"+book+"'");
            int deletedCount = query.executeUpdate();
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
    public void delete(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Query query = session.createQuery("delete from Book book where book.id =" + Integer.toString(id));
            int deletedCount = query.executeUpdate();
            tx.commit();
        }
        catch (Exception e) {
            if (tx!=null) tx.rollback();
        }
        finally {
            session.close();
        }
    }
}
