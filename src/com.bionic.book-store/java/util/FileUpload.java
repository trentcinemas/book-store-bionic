package util;

import java.io.*;

/**
 * Created by Джон on 25.07.2014.
 */
public class FileUpload {
    public static final String SERVER_UPLOAD_LOCATION_FOLDER = "/";

    // save uploaded file to a defined location on the server
    public static void saveFile(InputStream uploadedInputStream,
                          String serverLocation) {

        try {

            int read = 0;
            byte[] bytes = new byte[1024];

            OutputStream outputStream = new FileOutputStream(new File(serverLocation));
            while ((read = uploadedInputStream.read(bytes)) != -1) {
                outputStream.write(bytes, 0, read);
            }
            outputStream.flush();
            outputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}

