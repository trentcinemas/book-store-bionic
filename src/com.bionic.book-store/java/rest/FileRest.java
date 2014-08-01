package rest;


import util.MultipartRequestMap;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import java.io.File;

/**
 * Created by Джон on 29.07.2014.
 */
@Path("file")
public class FileRest {
    private static final String FILE_BOOK_PATH= MultipartRequestMap.UPLOAD_PATH + "/";

    @GET
    @Path("getimage/{path:.+}")
    @Produces("image/png")
    public Response getImage(@PathParam("path")String relativeFilePath) {

        File file = new File(FILE_BOOK_PATH+relativeFilePath);

        //Response.ResponseBuilder response = Response.ok((Object) file);
        /*response.header("Content-Disposition",
                "attachment; filename=image_from_server.png");
        */return Response.ok(file).build();

    }
}
