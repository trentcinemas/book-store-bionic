package rest;

import entities.User;
import util.CheckUser;
import util.DaoFactory;

import javax.servlet.ServletContext;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import java.io.InputStream;

/**
 * Created by jsarafajr on 27.07.14.
 */
@Path("/")
public class Security {

    @GET
    @Path("admin")
    @Produces("text/html")
    public InputStream getAdminHome(@CookieParam("user") String userEmail,
                                     @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/admin-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/books-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/add-author")
    @Produces("text/html")
    public InputStream getAddAuthorPage(@CookieParam("user") String userEmail,
                                    @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/addauthor-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/addauthor-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/add-book")
    @Produces("text/html")
    public InputStream getAddBookPage(@CookieParam("user") String userEmail,
                                    @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/addbook-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/addbook-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/add-topic")
    @Produces("text/html")
    public InputStream getAddTopicPage(@CookieParam("user") String userEmail,
                                        @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/addtopic-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/addtopic-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/authors-list")
    @Produces("text/html")
    public InputStream getAuthorsListPage(@CookieParam("user") String userEmail,
                                        @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/author-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/author-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/books-list")
    @Produces("text/html")
    public InputStream getBooksListPage(@CookieParam("user") String userEmail,
                                             @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/books-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/books-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/comments-list")
    @Produces("text/html")
    public InputStream getCommentsListPage(@CookieParam("user") String userEmail,
                                             @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);


        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/comments-admin.html");
        }


        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/edit-author")
    @Produces("text/html")
    public InputStream getEditAuthorsPage(@CookieParam("user") String userEmail,
                                          @QueryParam("id") String id,
                                          @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/editauthor-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/editauthor-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/edit-book")
    @Produces("text/html")
    public InputStream getEditBooksPage(@CookieParam("user") String userEmail,
                                        @QueryParam("id") String id,
                                        @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/editbook-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/editbook-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/orders-list")
    @Produces("text/html")
    public InputStream getOrdersListPage(@CookieParam("user") String userEmail,
                                          @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/orders-admin.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/topics-list")
    @Produces("text/html")
    public InputStream getTopicsListPage(@CookieParam("user") String userEmail,
                                          @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/topics-admin.html");
        }
        if (CheckUser.isRedactor(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/editor/topics-editor.html");
        }

        return context.getResourceAsStream("/404.html");
    }

    @GET
    @Path("admin/users-list")
    @Produces("text/html")
    public InputStream getUsersListPage(@CookieParam("user") String userEmail,
                                          @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin/users-admin.html");
        }

        return context.getResourceAsStream("/404.html");
    }

}
