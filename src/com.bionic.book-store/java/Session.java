import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.CookieParam;
import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Cookie;
import javax.ws.rs.core.NewCookie;
import javax.ws.rs.core.Response;

import static util.Logger.Type.PROCESS;

/**
 * Created by Evgeniy Baranuk on 16.07.14.
 */
@Path("/session")
public class Session {
    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";

    private DaoUserInterface dao = DaoFactory.getDaoUserInstance();

  /*  @POST
    @Path("/")
    public Response index(@CookieParam("user") String user) {
        if (user == null) {
            Logger.log(PROCESS, "user is in");
            try {
                java.net.URI location = new java.net.URI("localhost:8080/");
                return Response.temporaryRedirect(location).build();
            }
            catch(URISyntaxException e){}
        }
        else {
            Logger.log(PROCESS,user+" is in");
            try {
                java.net.URI location = new java.net.URI("localhost:8080/user-cabinet");
                return Response.temporaryRedirect(location).build();
            }
            catch(URISyntaxException e){}
        }
        return Response.status(400).build();
    }
*/
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

        NewCookie cookie = new NewCookie("user", email);
        Logger.log(PROCESS, email + " cookie saved");

        return Response.ok()
                .header(RESPONSE_HEADER, "*").cookie(cookie).build();
    }


    @POST
    @Path("logout")
    public Response logout(@CookieParam("user") String user) {
        if (user == null) {
            Logger.log(PROCESS, "Not login user");
            return Response.status(400).entity("User not signed in").build();
        }

        Cookie cookie = new Cookie("user", user);
        NewCookie authorizedCookie =
                new NewCookie(cookie, "Removed authorized profile cookie", 0, true); // Cookie age = 0

        Logger.log(PROCESS, "Logout : " + user);

        return Response.ok()
                .header(RESPONSE_HEADER, "*").cookie(authorizedCookie).build();
    }

}
