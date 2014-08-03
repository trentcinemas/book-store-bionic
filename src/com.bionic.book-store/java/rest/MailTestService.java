package rest;

import util.MailService;

import javax.ws.rs.FormParam;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

/**
 * Created by Джон on 02.08.2014.
 */
@Path("mail")
public class MailTestService {
    @Path("send")
    @POST
    public Response mail(@FormParam("to")final String to,
                         @FormParam("sub")final String sub,@FormParam("message")final String message){

       new Thread(new Runnable() {
           @Override
           public void run() {
               MailService.send(to, sub, message);
           }
       });

        return Response.ok().build();
    }
}
