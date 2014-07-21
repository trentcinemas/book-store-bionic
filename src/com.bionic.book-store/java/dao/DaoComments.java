package dao;

import dao.daoInterfaces.DaoCommentInterface;
import entities.Book;
import entities.Comment;
import entities.User;
import org.hibernate.Query;
import org.hibernate.Session;
import util.HibernateUtil;

import java.util.Date;
import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public class DaoComments implements DaoCommentInterface {
    private Session session;
    @Override
    public List<Comment> selectAll() {
        session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM Comment");
        List<Comment> result =query.list();
        session.close();
        return result;
    }

    @Override
    public Comment selectById(int id) {
        session = HibernateUtil.getSessionFactory().openSession();
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
        return null;
    }

    @Override
    public List<Comment> selectByUserId(int id) {
        return null;
    }

    @Override
    public List<Comment> selectByBook(Book book) {
        return null;
    }

    @Override
    public List<Comment> selectByBookId(int id) {
        return null;
    }

    @Override
    public List<Comment> selectByDate(Date date) {
        return null;
    }

    @Override
    public void insert(Comment comment) {

    }

    @Override
    public void update(Comment comment) {

    }

    @Override
    public void delete(Comment comment) {

    }

    @Override
    public void delete(int id) {

    }
}
