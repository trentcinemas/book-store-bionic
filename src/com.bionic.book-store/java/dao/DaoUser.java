package dao;

import dao.daoInterfaces.DaoUserInterface;
import entities.Book;
import entities.Comment;
import entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.List;

/**
 * Created by jsarafajr on 17.07.14.
 */
public class DaoUser implements DaoUserInterface {
    @Override
    public List<User> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User");
        return query.list();
    }

    @Override
    public List<Book> selectUserBooks() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select books from User user join user.booksByUserId books");
        return query.list();
    }

    @Override
    public List<Comment> selectComments() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select comments from User user join user.commentsByUserId comments");
        return query.list();
    }

    @Override
    public User selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.id=" +
                Integer.toString(id));
        return (User)query.list().get(0);
    }

    @Override
    public User selectByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.email='" +
                email+"'");
        return (User)query.list().get(0);
    }
}
