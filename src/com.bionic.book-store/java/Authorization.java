import util.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Evgeniy Baranuk on 16.07.14.
 */
@Path("/")
public class Authorization {

    @GET
    @Path("authorize")
    public Response login(@HeaderParam("email") String login,
                          @HeaderParam("password") String password) {

        System.out.println(DaoFactory.getDaoUserInstance().selectByEmail("m@m.com"));
        System.out.println(DaoFactory.getDaoUserInstance().selectById(1));

        return Response.ok().build();
    }

}
