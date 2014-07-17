import dao.UserDao;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

/**
 * Created by jsarafajr on 16.07.14.
 */
@Path("/")
public class Authorization {
    @POST
    @Path("authorize")
    public Response login(@FormParam("login") String login,
                         @FormParam("password") String password) {

        System.out.println(login + " " + password);

        if (UserDao.getUsers().contains(login)) {
            if (UserDao.getPassword(login).equals(password)) {
                return Response.ok().header("Access-Control-Allow-Origin", "*").build();
            }
        }

        return Response.status(400).build();
    }


}
