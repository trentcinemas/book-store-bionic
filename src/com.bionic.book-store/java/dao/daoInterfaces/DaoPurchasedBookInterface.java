package dao.daoInterfaces;

import entities.PurchasedBook;
import entities.User;

import java.util.Date;
import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoPurchasedBookInterface {
    public PurchasedBook selectById(int id);
    public List<PurchasedBook> selectByUser(User user);
    public List<PurchasedBook> selectByUserId(int id);
    public List<PurchasedBook> selectByDate(Date date);
    public List<PurchasedBook> selectAll();
    void insert(PurchasedBook user);
    void update(PurchasedBook user);
    void delete(PurchasedBook user);
    void delete(int id);
}
