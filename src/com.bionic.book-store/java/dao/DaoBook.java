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
    @Override
    public List<Book> selectAllOrdered(String byWhat,boolean order) {
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query=null;
        if(order==true)
        query = session.createQuery("FROM Book order by "+byWhat+" asc");
        else
        query = session.createQuery("FROM Book order by "+byWhat+" desc");
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
    public List<Book> selectAll() {
        Session session;
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
        Session session;
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
        Session session;
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
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book where book.userByUserId='" +
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
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select b from Book b join b.userByUserId c where c.userId ="+id);
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
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select book from Book book where book.distributorByDistrId ='" +
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
        Session session;
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select b from Book b join b.distributorByDistrId c where c.distrId ="+id);
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
        Session session;
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
        Session session;
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
        Session session;
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
        Session session;
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
