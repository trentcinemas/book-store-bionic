package dao;

import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import entities.UserGroup;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import util.HibernateUtil;

import java.math.BigInteger;
import java.util.List;

/**
 * Created by jsarafajr on 17.07.14.
 */
public class DaoUser implements DaoUserInterface {
    public final static String ALL = "";

    public DaoUser() {

    }

    @Override
    public List<User> selectAll(int limit, boolean order, String orderByWhat) {
        if (orderByWhat != "") {
            if (order == true)
                orderByWhat = "order by" + orderByWhat + " asc";
            else
                orderByWhat = "order by" + orderByWhat + " desc";
        }
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User user " +orderByWhat);
        if(limit<0 || limit>query.list().size()) limit=query.list().size();
        query.setFirstResult(0);
        query.setMaxResults(limit);
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<User> selectByUserGroup(UserGroup group, int limit, boolean order, String orderByWhat) {
        return null;
    }

    @Override
    public List<User> selectByUserGroupType(String type, int limit, boolean order, String orderByWhat) {
        return null;
    }

    @Override
    public List<User> selectAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("FROM User");
//        query.set
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public User selectById(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.id=" + id);
        if (!query.list().isEmpty()) {
            User result = (User) query.list().get(0);
            session.close();
            return result;
        } else session.close();
        return null;
    }

    @Override
    public User selectByEmail(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.email='" +
                email + "'");
        if (!query.list().isEmpty()) {
            User result = (User) query.list().get(0);
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }

    @Override
    public void insert(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.persist("User", user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void update(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.update(user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (Exception e) {
            if (session != null) session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(User user) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.delete(user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where id=" + id);
        User user = (User) query.list().get(0);
        try {
            session.beginTransaction();
            session.delete(user);
            if (!session.getTransaction().wasCommitted())
                session.getTransaction().commit();
        } catch (HibernateException e) {
            if (session != null)
                session.getTransaction().rollback();
        } finally {
            session.close();
        }
    }

    @Override
    public List<User> search(String str) {
        str = str.trim();
        str = str.toLowerCase();
        String[] words = str.split(" ");

        Session session;
        session = HibernateUtil.getSessionFactory().openSession();

        String selectQuery = "select u from User u where lower(u.name) like '%" + str + "%' or lower(u.email) like '%" + str + "%'";
        for (String w : words)
            selectQuery += "or lower(u.name) like '%" + w + "%' or lower(u.email) like '%" + w + "%'";

        Query query = session.createQuery(selectQuery);
        if (!query.list().isEmpty()) {
            List<User> result = query.list();
            session.close();
            return result;
        } else {
            session.close();
            return null;
        }
    }

    @Override
    public List<User> selectPage(int page){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("from User");
        query.setFirstResult(0+15*(page-1));
        query.setMaxResults(15);
        List<User> result =query.list();
        session.close();
        return result;
    }
    @Override
    public List<User> orderByName(boolean order, int page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        if (order == true){
            query = session.createQuery("from User user order by user.name asc ");
        }else
        {
            query = session.createQuery("from User user order by user.name desc ");
        }
        query.setFirstResult(0+15*(page-1));
        query.setMaxResults(15);
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<User> orderByeMail(boolean order, int page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        if (order == true){
            query = session.createQuery("from User user order by user.email asc ");
        }else
        {
            query = session.createQuery("from User user order by user.email desc ");
        }
        query.setFirstResult(0+15*(page-1));
        query.setMaxResults(15);
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<User> orderByGroup(boolean order, int page) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = null;
        if (order == true){
            query = session.createQuery("from User user order by user.userGroupByGroupId.type asc ");
        }else
        {
            query = session.createQuery("from User user order by user.userGroupByGroupId.type desc ");
        }
        query.setFirstResult(0+15*(page-1));
        query.setMaxResults(15);
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<User> selectByUserGroup(UserGroup group) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.userGroupByGroupId=" +
                group.getUserGroupId());
        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public List<User> selectByUserGroupType(String type) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("select user from User user where user.userGroupByGroupId.type=" +
                type);

        List<User> result = query.list();
        session.close();
        return result;
    }

    @Override
    public boolean exist(String email) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Query query = session.createQuery("From User where email=" + "'" + email + "'");
        if (!query.list().isEmpty()) {
            session.close();
            return true;
        } else {
            session.close();
            return false;
        }
    }

    public List<User> orderBy (String cell,int page,boolean order){
        switch (cell){
            case "name": return orderByName(order,page);
            case "email": return orderByeMail(order,page);
            case "group": return orderByGroup(order,page);
            default: return null;
        }
    }
    public BigInteger count(){
        Session session =HibernateUtil.getSessionFactory().openSession();
        SQLQuery query=session.createSQLQuery("select count(1) from User");
        return (BigInteger) (query.list().get(0));
    }
}
