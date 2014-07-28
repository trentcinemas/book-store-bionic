package util;

import dao.*;

/**
 * Created by Джон on 17.07.2014.
 */
public class DaoFactory {
    private static DaoAuthor daoAuthor = new DaoAuthor();
    private static DaoBook daoBook = new DaoBook();
    private static DaoComments daoComments = new DaoComments();
    private static DaoDistributor daoDistributor = new DaoDistributor();
    private static DaoGenre daoGenre = new DaoGenre();
    private static DaoReplyMessage daoReplyMessage = new DaoReplyMessage();
    private static DaoUser userDAO = new DaoUser();
    private static DaoUserGroup daoUserGroup = new DaoUserGroup();

    public static DaoUser getDaoUserInstance(){
        return userDAO;
    }

    public static DaoAuthor getDaoAuthorInstance() {
        return daoAuthor;
    }

    public static DaoBook getDaoBookInstance() {
        return daoBook;
    }

    public static DaoComments getDaoCommentsInstance() {
        return daoComments;
    }

    public static DaoDistributor getDaoDistributorInstance() {
        return daoDistributor;
    }

    public static DaoGenre getDaoGenreInstance() {
        return daoGenre;
    }

    public static DaoReplyMessage getDaoReplyMessageInstance() {
        return daoReplyMessage;
    }

    public static DaoUserGroup getDaoUserGroupInstance() {
        return daoUserGroup;
    }

    private DaoFactory() {

    }
}
