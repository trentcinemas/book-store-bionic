import entities.User;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.CookieParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by jsarafajr on 23.07.14.
 */
@Path("add/book")
public class AddBook {
    private final String RESPONSE_HEADER = "Access-Control-Allow-Origin";

    @POST
    public Response addBookToStorage(@CookieParam("user") String enteredUser) {
        User user = DaoFactory.getDaoUserInstance().selectByEmail(enteredUser);
        if (!checkUser(user)) {
            Logger.log(Logger.Type.PROCESS, "Access denied : " + enteredUser);
            return Response.status(403).header(RESPONSE_HEADER, "*")
                    .entity("Вибачте, ви не маєте досупу до даної операції").build();
        }

        //...

        return null;

    }


    private boolean checkUser(User user) {
        if (user == null) return false;
        // TODO check user group

        return true;
    }
}
