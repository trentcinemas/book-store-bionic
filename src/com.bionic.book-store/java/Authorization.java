import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static util.Logger.Type.PROCESS;

/**
 * Created by Evgeniy Baranuk on 16.07.14.
 */
@Path("/")
public class Authorization {

    @POST
    @Path("authorize")
        public Response authorize(@FormParam("email") String email,
                          @FormParam("password") String password) {

        DaoUserInterface dao = DaoFactory.getDaoUserInstance();
        User user = dao.selectByEmail(email);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                Logger.log(PROCESS, "Authorized : " + email);
                return Response.ok().header("Access-Control-Allow-Origin", "*").build();
            }
        }

        Logger.log(PROCESS, "Not authorized : " + email);
        return Response.status(400).build();
    }

}
