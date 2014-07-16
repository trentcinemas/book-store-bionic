import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;

@Path("/hello")
public class HelloWorld {

    @GET
    public String getMsg() {
        return "Yo!m Im Jersey";
    }

    @GET
    @Path("/{param}")
    public String getMsg(@PathParam("param") String msg) {
        return "Jersey say : " + msg;
    }

}