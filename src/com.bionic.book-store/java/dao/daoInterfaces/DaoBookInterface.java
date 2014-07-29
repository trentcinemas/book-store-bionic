package dao.daoInterfaces;

import entities.Book;
import entities.Distributor;
import entities.User;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoBookInterface {
    public List<Book> selectAll();
    public Book selectById(int id);
    public List<Book> selectByTitle(String title);
    public List<Book> selectByUser(User user);
    public List<Book> selectByUserId(int id);
    public List<Book> selectByDistributor(Distributor distr);
    public List<Book> selectByDistributorId(int id);
    void insert(Book book);
    void update(Book book);
    void delete(Book book);
    void delete(int id);
    public List<Book> search(String s);
}
