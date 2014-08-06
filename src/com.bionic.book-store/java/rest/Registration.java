package rest;

import dao.daoInterfaces.DaoUserInterface;
import entities.User;
import org.apache.commons.codec.digest.DigestUtils;
import util.DaoFactory;
import util.Logger;
import util.MailService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static util.Logger.Type.PROCESS;

/**
 * Created by Джон on 21.07.2014.
 */
@Path("register")
public class Registration {
    @POST
    public Response register(@FormParam("email") String email,
                              @FormParam("password") String password,
                              @FormParam("name") String name) {

        DaoUserInterface daoUser = DaoFactory.getDaoUserInstance();

        if (!checkEmail(email)) {
            Logger.log(PROCESS, "Invalid email : " + email);
            return Response.status(400)
                    .entity("Введена неправильна e-mail адреса").build();
        }

        if (name.length() < 3 || name.length() > 30) {
            Logger.log(PROCESS, "Invalid name " + name);
            return Response.status(400)
                    .entity("Введене неправильне ім'я").build();
        }

        // HTTP 409 (Conflict)
        if (daoUser.exist(email)) {
            Logger.log(PROCESS, email + " already exist");
            return Response.status(409)
                    .entity("Користувач з таким іменем уже зареєстрований").build();
        }

        User user = new User();

        user.setName(name);
        user.setEmail(email);
        user.setPassword(DigestUtils.md5Hex(password));
        user.setUserGroupByGroupId(DaoFactory.getDaoUserGroupInstance().getGroupByType("Користувач"));

        Logger.log(PROCESS, "Registered : " + email);

        daoUser.insert(user);

        MailService.send(user.getEmail(), "Registration", "Дякуємо, за реєстрацію на нашому веб-сайті!" +
                "\nВаш логін:" + email +
                "\nВаш пароль:" + password +
                "\nБудь ласка, не повідомляйте свій пароль стороннім особам!");

        return Response.ok().build();
    }

    private boolean checkEmail(String email) {
        Pattern pattern = Pattern.compile(".+@.+");
        Matcher matcher = pattern.matcher(email);
        return matcher.find();
    }

}
