package dao.daoInterfaces;

import entities.Book;
import entities.Comment;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoBookInterface {
    public List<Book> selectAll();
    public List<Book> selectUserBooks();
    public List<Book> selectUserPurchasedBooks();
    public List<Comment> selectComments();
    public Book selectById(int id);
    public List<Book> selectByTitle(String title);
    void insert(Book book);
    void update(Book user);
    void delete(Book book);
    void delete(int id);
}
