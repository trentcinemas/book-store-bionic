package rest;

import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import jsonClasses.UserJson;
import org.apache.commons.codec.digest.DigestUtils;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import static util.Logger.Type.PROCESS;

/**
 * Created by Evgeniy Baranuk on 16.07.14.
 */
@Path("/session")
public class Session {
    private final String COOKIE_PATH = "/";
    private final String COOKIE_DOMAIN = "";

    private DaoUserInterface dao = DaoFactory.getDaoUserInstance();

    @POST
    @Path("login")
    public Response login(@FormParam("email") String email,
                          @FormParam("password") String password) {

        User user = dao.selectByEmail(email);

        String hexedPassword = DigestUtils.md5Hex(password);

        // User not exist
        if (user == null) {
            Logger.log(PROCESS, "Not authorized : " + email);
            return Response.status(400).entity("Неправильні дані авторизації").build();
        }
        // Wrong user password
        if (!hexedPassword.equals(user.getPassword())) {
            Logger.log(PROCESS, "Invalid password : " + email);
            return Response.status(400).entity("Неправильні дані авторизації").build();
        }

        // All OK :
        Logger.log(PROCESS, email + " authorized");

        NewCookie cookie = new NewCookie("user", email, COOKIE_PATH, COOKIE_DOMAIN, "",8800,false);

        Logger.log(PROCESS, email + " cookie saved");

        // HTTP 307 - Redirect
        return Response.status(307).entity("index.html")
                .cookie(cookie).build();
    }

    @GET
    @Path("get-user")
    @Produces("application/json")
    public UserJson getUser(@CookieParam("user") String userEmail) {
        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        return new UserJson(user);
    }


    @POST
    @Path("logout")
    public Response logout(@CookieParam("user") String userEmail) {
        if (userEmail == null) {
            Logger.log(PROCESS, "Not login user");
            return Response.status(400).entity("User not signed in").build();
        }

        NewCookie authorizedCookie =
                new NewCookie("user", userEmail, COOKIE_PATH, COOKIE_DOMAIN, "", 0, false); // Cookie age = 0

        Logger.log(PROCESS, "Logout : " + userEmail);

        return Response.ok()
                .cookie(authorizedCookie).build();
    }

}
