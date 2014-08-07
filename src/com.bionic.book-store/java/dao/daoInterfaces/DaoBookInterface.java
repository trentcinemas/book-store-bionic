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

    List<Book> selectOrdered(String byWhat, int howmuch, boolean order);

    public List<Book> selectAllOrdered(String byWhat, boolean order);
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
    public List<Book> selectByGenreID(int id);

    List<Book> selectPage(int page);

    public List<Book> orderByTitle(boolean order, int page);
    public List<Book> orderByAuthor(boolean order, int page);
    public List<Book> orderByDate(boolean order, int page);
    public List<Book> orderByPageCount(boolean order, int page);
    public List<Book> orderByReviewCount(boolean order, int page);
    public List<Book> orderByPrice(boolean order, int page);
    public List<Book> orderByBuyCount(boolean order, int page);


}
