package dao.daoInterfaces;

import entities.Book;
import entities.Comment;
import entities.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoCommentInterface {
    public List<Comment> selectAll(int page);
    public Comment selectById(int id);
    public List<Comment> selectByUser(User user);
    public List<Comment> selectByUserId(int id);
    public List<Comment> selectByBook(Book book);
    public List<Comment> selectByBookId(int id);
    public List<Comment> selectByDate(Date date);
    void insert(Comment comment);
    void update(Comment comment);
    void delete(Comment comment);
    void delete(int id);
    public List<Comment> search(String searchstring);
    public List<Comment> orderByEmail(boolean order, int page);
    public List<Comment> orderByTitle(boolean order, int page);
    public List<Comment> orderByDate(boolean order, int page);
}
