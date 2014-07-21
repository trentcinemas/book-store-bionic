import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import sun.security.provider.MD5;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import static util.Logger.Type.PROCESS;

/**
 * Created by Джон on 21.07.2014.
 */
/*@Path("/")*/
public class Registration {

    @POST
    @Path("register")
    public Response authorize(@FormParam("email") String email,
                              @FormParam("password") String password,
                              @FormParam("name") String name) {

        DaoUserInterface dao = DaoFactory.getDaoUserInstance();
        User user = new User();
        user.setName(name);
        user.setEmail(email);
        user.setPassword(DigestUtils.md5Hex(password));
        Logger.log(PROCESS, "Registered : " + email);
        dao.insert(user);
        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }
}