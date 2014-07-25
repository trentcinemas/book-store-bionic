package rest;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;

/**
 * Created by jsarafajr on 24.07.14.
 */

@Path("reply")
public class PublisherReply {

    @POST
    @Path("send")
    public void sendReplyMessage(@FormParam("email") String email,
                                 @FormParam("receiver") String receiver,
                                 @FormParam("text") String text) {


    }
}
