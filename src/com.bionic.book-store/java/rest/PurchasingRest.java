package rest;

import dao.DaoPurchasedBook;
import entities.PurchasedBook;
import jsonClasses.PurchasedBookJson;
import util.DaoFactory;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import java.util.ArrayList;
import java.util.List;

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
}
