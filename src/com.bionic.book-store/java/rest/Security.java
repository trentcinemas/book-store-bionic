package rest;

import entities.User;
import util.CheckUser;
import util.DaoFactory;

import javax.servlet.ServletContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
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
    public InputStream getAdminPage(@CookieParam("user") String userEmail,
                                     @Context ServletContext context) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (CheckUser.isAdmin(user) || CheckUser.isRedactor(user) || CheckUser.isModer(user)) {
            return context.getResourceAsStream("/WEB-INF/pages/admin-admin.html");
        }

        return context.getResourceAsStream("/404.html");
    }
}
