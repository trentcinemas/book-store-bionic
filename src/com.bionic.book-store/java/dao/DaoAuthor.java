package dao;

import dao.daoInterfaces.DaoAuthorInterface;
import entities.Author;
import entities.Book;
import entities.User;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by Evgeniy Baranuk on 21.07.14.
 */
public class DaoAuthor implements DaoAuthorInterface {

    public Author selectByName(String name) {
        String firstname = name.split(" ")[0];
        String lastname = name.split(" ")[1];

        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from  Author  where firstname = '" + firstname +
                "' and lastname = '" + lastname + "'" );
        if (query.list().size() == 0) {
            session.close();
            return null;
        }
        Author result = (Author) query.list().get(0);
        session.close();
        return result;
    }

    @Override
    public List<Author> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Author");
        List<Author> authors = query.list();
        session.close();
        return authors;
    }

    @Override
    public List<Author> selectAllByPage(int page) {
            Session session = HibernateUtil.getSessionFactory().openSession();
            Query query = session.createQuery("from Author ");
            query.setFirstResult(0+15*(page-1));
            query.setMaxResults(15);
            List<Author> result =query.list();
            session.close();
            return result;
    }

    @Override
    public List<Author> selectAuthorBooks(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select b from Book b where b.authorByAuthorId = " + id);
        List<Author> authors = query.list();
        session.close();
        return authors;
    }

    @Override
    public Author selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Author where authorId = " + id);
        if (query.list().size() == 0) {
            session.close();
            return null;
        }
        Author result = (Author) query.list().get(0);
        session.close();
        return result;
    }

    @Override
    public List<Author> selectByFirstName(String fname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from  Author  where firstname = '" + fname + "'");
        List<Author> authors = query.list();
        session.close();
        return authors;
    }

    @Override
    public List<Author> selectByLastName(String lname) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from  Author  where lastname = '" + lname + "'");
        List<Author> authors = query.list();
        session.close();
        return authors;
    }

    @Override
    public void insert(Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("Author", author);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
        }
        session.close();
    }

    @Override
    public void update(Author author) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            session.update(author);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) tx.rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(Author author) {
        delete(author.getAuthorId());
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from Author where id = " + id);
        Author author = (Author) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(author);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        }
    }

    @Override
    public List<Author>  orderByName(boolean order,int page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        if (order == true){
            query = session.createQuery("from  Author order by firstname asc");
        }else {
            query = session.createQuery("from  Author order by firstname desc");
        }
        if (!query.list().isEmpty()) {
            List<Author> result = query.list();
            query.setFirstResult(0+15*(page-1));
            query.setMaxResults(15);
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }

    }

    @Override
    public List<Author> orderBySurname(boolean order, int page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        if (order == true){
            query = session.createQuery("from  Author order by lastname asc");
        }else {
            query = session.createQuery("from  Author order by lastname desc");
        }
        if (!query.list().isEmpty()) {
            List<Author> result = query.list();
            query.setFirstResult(0+15*(page-1));
            query.setMaxResults(15);
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }

    public List<Author> orderBy(String name, boolean order, int page){
        switch (name){
            case "name": return orderByName(order,page);
            case "lastname": return orderBySurname(order, page);
            default: return null;
        }
    }
}
