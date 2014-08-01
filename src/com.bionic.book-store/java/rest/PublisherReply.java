package rest;

import dao.DaoReplyMessage;
import entities.ReplyMessage;
import jsonClasses.ReplyMessageJson;
import util.DaoFactory;
import util.Logger;

import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by jsarafajr on 24.07.14.
 */

@Path("reply")
public class PublisherReply {

    @POST
    @Path("send")
    public Response sendReplyMessage(@FormParam("email") String email,
                                 @FormParam("receiver") String receiver,
                                 @FormParam("text") String text) {

        DaoReplyMessage daoReplyMessage = DaoFactory.getDaoReplyMessageInstance();

        ReplyMessage replyMessage = new ReplyMessage();

        replyMessage.setEmail(email);
        replyMessage.setReceiver(receiver);
        replyMessage.setText(text);
        replyMessage.setDate(new Timestamp(new Date().getTime()));

        daoReplyMessage.insert(replyMessage);

        Logger.log(Logger.Type.PROCESS, "Reply from " + email);

        return Response.ok().build();
    }


    @GET
    @Path("getLast")
    public ArrayList<ReplyMessageJson> getLastMessages() {
        ArrayList<ReplyMessageJson> replyMessageJsons = new ArrayList<>();
        List<ReplyMessage> replyMessages = DaoFactory.getDaoReplyMessageInstance().selectAll();

        // TODO move to DAO select
        for (int i = 0; i < 10 && i < replyMessages.size(); i++) {
            replyMessageJsons.add(new ReplyMessageJson(replyMessages.get(i)));
        }

        return replyMessageJsons;
    }
}
