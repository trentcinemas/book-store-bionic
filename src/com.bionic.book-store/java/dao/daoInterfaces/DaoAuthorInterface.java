package dao.daoInterfaces;

import entities.Author;
import entities.Book;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoAuthorInterface {
    public List<Author> selectAll();
    public List<Author> selectAllByPage(int page);
    public List<Author> selectAuthorBooks(int id);
    public Author selectById(int id);
    public List<Author> selectByFirstName(String fname);
    public List<Author> selectByLastName(String lname);
    public void insert(Author author);
    public void update(Author author);
    public void delete(Author author);
    public void delete(int id);
    public List<Author> orderByName(boolean order,int page);
    public List<Author> orderBySurname(boolean order, int page);
}
