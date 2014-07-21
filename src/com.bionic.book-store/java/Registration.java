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

import static util.Logger.Type.ERROR;
import static util.Logger.Type.PROCESS;

/**
 * Created by Джон on 21.07.2014.
 */
@Path("register")
public class Registration {
    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";

    @POST
    public Response register(@FormParam("email") String email,
                              @FormParam("password") String password,
                              @FormParam("name") String name) {

        DaoUserInterface daoUser = DaoFactory.getDaoUserInstance();

        // HTTP 409 (Conflict)
        if (daoUser.exist(email)) {
            Logger.log(ERROR, email + " already exist");
            return Response.status(409).header(RESPONSE_HEADER, "*")
                    .entity("User already exist").build();
        }


        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(DigestUtils.md5Hex(password));
        // TODO Додати групу юзера
        //  user.setUserGroupByGroupId();

        Logger.log(PROCESS, "Registered : " + email);

        daoUser.insert(user);

        return Response.ok().header("Access-Control-Allow-Origin", "*").build();
    }
}
