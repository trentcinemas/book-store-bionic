package dao.daoInterfaces;

import entities.Genre;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoGenreInterface {
    public List<Genre> selectAll();
    public Genre selectById(int id);
    void insert(Genre user);
    void update(Genre user);
    void delete(Genre user);
    void delete(int id);
}
