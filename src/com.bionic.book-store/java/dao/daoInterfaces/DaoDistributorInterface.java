package dao.daoInterfaces;

import entities.Distributor;

import java.util.List;

/**
 * Created by Джон on 21.07.2014.
 */
public interface DaoDistributorInterface {
    public List<Distributor> selectAll();
    public Distributor selectById(String url);
    public Distributor selectByUrl(String url);
    void insert(Distributor distributor);
    void update(Distributor distributor);
    void delete(Distributor distributor);
    void delete(int id);
}
