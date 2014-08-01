package rest;

import javax.servlet.ServletContext;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import java.io.InputStream;

/**
 * Created by jsarafajr on 27.07.14.
 */
@Path("/")
public class Security {

    @GET
    @Path("secure")
    @Produces("text/html")
    public InputStream getSecurePage(@CookieParam("user") String user,
                                     @Context ServletContext context) {

        if (user == null) return context.getResourceAsStream("/WEB-INF/pages/404.html");

        return context.getResourceAsStream("/WEB-INF/pages/secure.html");
    }
}
