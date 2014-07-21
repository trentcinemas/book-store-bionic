package dao.daoInterfaces;

import entities.Genre;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoGenreInterface {
    void insert(Genre user);
    void update(Genre user);
    void delete(Genre user);
    void delete(int id);
}
