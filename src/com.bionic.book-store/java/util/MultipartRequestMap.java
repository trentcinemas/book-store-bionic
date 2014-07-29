package util;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;
import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Джон on 28.07.2014.
 */
public class MultipartRequestMap extends HashMap<String, List<Object>> {
    private final String DEFAULT_ENCODING="UTF-8";
    private final String UPLOAD_PATH="D:\\Bionic\\Books";
    private String STORAGE="";
    private String encoding;

    public MultipartRequestMap(HttpServletRequest request) {
        super();
        try {


            this.encoding = request.getCharacterEncoding();
            if (this.encoding == null) {
                try {
                    request.setCharacterEncoding(this.encoding = DEFAULT_ENCODING);
                } catch (UnsupportedEncodingException ex) {
                    //  Logger.getLogger(MultipartRequestMap.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            String tempLocation=getValue(request.getPart("title"));
            File myDir = new File(UPLOAD_PATH+"\\"+tempLocation);
            myDir.mkdirs();
            this.STORAGE = UPLOAD_PATH+"\\"+tempLocation;

            for (Part part : request.getParts()) {
                String fileName = part.getSubmittedFileName();
                if (fileName == null) {
                    putMulti(part.getName(), getValue(part));
                } else {
                    processFilePart(part, fileName);
                }
            }
        } catch (IOException | ServletException ex) {
            //Logger.getLogger(MultipartRequestMap.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public String getStringParameter(String name) {
        List<Object> list = get(name);
        return (list != null) ? (String) get(name).get(0) : null;
    }

    public File getFileParameter(String name) {
        List<Object> list = get(name);
        return (list != null) ? (File) get(name).get(0) : null;
    }

    private void processFilePart(Part part, String fileName) throws IOException {
        File tempFile = new File(STORAGE, fileName);
        tempFile.createNewFile();
        tempFile.deleteOnExit();

        try (BufferedInputStream input = new BufferedInputStream(part.getInputStream(), 8192);
             BufferedOutputStream output = new BufferedOutputStream(new FileOutputStream(tempFile), 8192);) {

            byte[] buffer = new byte[8192];
            for (int length = 0; ((length = input.read(buffer)) > 0);) {
                output.write(buffer, 0, length);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        part.delete();
        putMulti(part.getName(), tempFile);
    }

    private String getValue(Part part) throws IOException {
        BufferedReader reader
                = new BufferedReader(new InputStreamReader(part.getInputStream(), encoding));
        StringBuilder value = new StringBuilder();
        char[] buffer = new char[8192];
        for (int length; (length = reader.read(buffer)) > 0;) {
            value.append(buffer, 0, length);
        }
        return value.toString();
    }

    private <T> void putMulti(final String key, final T value) {
        List<Object> values = (List<Object>) super.get(key);

        if (values == null) {
            values = new ArrayList<>();
            values.add(value);
            put(key, values);
        } else {
            values.add(value);
        }
    }
}