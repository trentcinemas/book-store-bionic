package rest;

import dao.DaoUser;
import entities.User;
import jsonClasses.UserJson;
import sun.rmi.runtime.Log;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jsarafajr on 30.07.14.
 */
@Path("user")
public class UserRest {

    @POST
    @Path("update")
    public Response updateUser(@FormParam("id") String idString,
                               @FormParam("email") String email,
                               @FormParam("name") String name,
                               @FormParam("group") String group) {

        int id = Integer.parseInt(idString);

        User user = DaoFactory.getDaoUserInstance().selectById(id);

        String prevValueLog = "\"" + user.getEmail() + " " + user.getName() + " " + user.getUserGroupByGroupId().getType() + "\"";

        user.setName(name);
        user.setEmail(email);
        user.setUserGroupByGroupId(DaoFactory.getDaoUserGroupInstance().getGroupByType(group));

        DaoFactory.getDaoUserInstance().update(user);

        String valueLog = "\"" + user.getEmail() + " " + user.getName() + " " + user.getUserGroupByGroupId().getType() + "\"";

        Logger.log(Logger.Type.PROCESS, "Edit : " + prevValueLog + " to " + valueLog);

        return Response.ok().build();
    }

    @GET
    @Path("getAll")
    @Produces("application/json")
    public ArrayList<UserJson> getAllUsers() {
        ArrayList<UserJson> usersJson = new ArrayList<UserJson>();
        List<User> users = DaoFactory.getDaoUserInstance().selectAll();

        for (User user : users) {
            usersJson.add(new UserJson(user));
        }

        return usersJson;
    }

    @GET
    @Path("getLast")
    @Produces("application/json")
    public ArrayList<UserJson> getLastRegistered() {
        ArrayList<UserJson> usersJson = new ArrayList<UserJson>();
        List<User> users = DaoFactory.getDaoUserInstance().selectAll();

        // TODO change !!11 (limit, order add to DAO)
        // O_O
        for (int i = users.size() - 1, j = 0; j < 10 & i >= 0; i--, j++) {
            usersJson.add(new UserJson(users.get(i)));
        }

        return usersJson;
    }
}
