package dao.daoInterfaces;

import entities.Book;
import entities.Comment;
import entities.User;

import java.util.List;

/**
 * Created by Джон on 16.07.2014.
 */
public interface DaoUserInterface {
    public List<User> selectAll();
    public List<Book> selectUserBooks();
    public List<Comment> selectComments();
    public User selectById(int id);
    public User selectByEmail(String login);
}
