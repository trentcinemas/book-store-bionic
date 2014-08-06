package rest;

import dao.DaoPurchasedBook;
import entities.PurchasedBook;
import entities.User;
import jsonClasses.PurchasedBookJson;
import util.CheckUser;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.*;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

import static util.Logger.Type.PROCESS;

/**
 * Created by jsarafajr on 05.08.14.
 */
@Path("purchase")
public class PurchasingRest {
    @Path("getLast")
    @GET
    public ArrayList<PurchasedBookJson> getLastPurchases() {
        // TODO check user

        ArrayList<PurchasedBookJson> bookJsons = new ArrayList<>();

        List<PurchasedBook> purchasedBooks =
                DaoFactory.getDaoPurchasedBookInstance().getLast();


        // last 10 elements
        for (int i = 0; i < 10 && i < purchasedBooks.size(); i++) {
            bookJsons.add(new PurchasedBookJson(purchasedBooks.get(i)));
        }

        return bookJsons;
    }


    @Path("get-all")
    @GET
    @Produces("application/json")
    public ArrayList<PurchasedBookJson> getAllPurchasing(@CookieParam("user") String userEmail) {
        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);

        if (!(CheckUser.isModer(user) || CheckUser.isAdmin(user))) {
            Logger.log(PROCESS, "Access denied : " + userEmail);
            return new ArrayList<>();
        }

        List<PurchasedBook> purchasedBooks = DaoFactory.getDaoPurchasedBookInstance().selectAll();
        ArrayList<PurchasedBookJson> purchasedBookJsons = new ArrayList<>(purchasedBooks.size());

        for (int i = 0; i < purchasedBooks.size(); i++) {
            purchasedBookJsons.add(new PurchasedBookJson(purchasedBooks.get(i)));
        }

        return purchasedBookJsons;
    }

    @POST
    @Path("remove")
    public Response removeBook(@CookieParam("user") String userEmail,
                               @FormParam("id") String idString) {

        User user = DaoFactory.getDaoUserInstance().selectByEmail(userEmail);
        if (!CheckUser.isAdmin(user)) {
            Logger.log(PROCESS, "Access denied remove purchase : " + userEmail);
            return Response.status(403).build();
        }

        int id = Integer.parseInt(idString);
        PurchasedBook book = DaoFactory.getDaoPurchasedBookInstance().selectById(id);
        DaoFactory.getDaoPurchasedBookInstance().delete(book);

        Logger.log(PROCESS, "Purchase id :" + id + "removed by " + userEmail);

        return Response.ok().build();
    }
}
