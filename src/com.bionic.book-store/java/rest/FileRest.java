package rest;


import util.DaoFactory;
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
    @Path("getsmallimage/{id}")
    @Produces("image/png")
    public Response getImage(@PathParam("id")String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getCover());
        return Response.ok(file).build();
    }
    @GET
    @Path("getbigimage/{id}")
    @Produces("images/png")
    public Response getBigImage(@PathParam("id")String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getBigCover());
        return Response.ok(file).build();
    }

    @GET
    @Path("getreviewfile/{id}")
    @Produces("application/pdf")
    public Response getPreview(@PathParam("id")String id){
        File file = new File(FILE_BOOK_PATH+ DaoFactory.getDaoBookInstance().selectById(Integer.parseInt(id)).getPreview());
        return Response.ok(file).build();
    }

}
