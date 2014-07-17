import dao.DaoUser;
import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import util.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.List;

import static util.Logger.Type.*;

/**
 * Created by Evgeniy Baranuk on 16.07.14.
 */
@Path("/")
public class Authorization {
    @GET
    @Path("authorize")
    public Response login(@HeaderParam("login") String login,
                          @HeaderParam("password") String password) {

        DaoUserInterface daoUser = new DaoUser();
        List<User> users = daoUser.selectAll();
        for (User u : users) {
            System.out.println(u);
        }

        return Response.ok().build();
    }

}
