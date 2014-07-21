package util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by Evgeniy Baranuk on 17.07.14.
 * Logger class, which based on Singleton pattern
 */
public class Logger {
    // single instance
    private static Logger instance = new Logger();
// не хотіло створювати файл в потоній папці , програма вилітала
    //дописав конкретний диск в корені
    //треба це рішити
    private final String FILE_PATH = "log.txt";
    private BufferedWriter writer;

    private Logger() {
        try {
            File f = new File(FILE_PATH);
            System.out.println(f.getAbsolutePath());
            writer = new BufferedWriter(new FileWriter(FILE_PATH));
        } catch (IOException e) {
            System.out.println("Can not create Logger");
        }
    }

    /**
     * Logging message to file (FILE_PATH) and output to stdout
     * @param type enum type of message
     * @param msg text which will be logged
     */
    public static void log(Type type, String msg) {
         instance.logMessage(type, msg);
    }

    public void logMessage(Type type, String msg) {
        System.out.printf("%19s %-7s : %s \n", getCurrentTime(), type.toString(), msg);
        try {
            writer.write(String.format("%19s %-7s : %s \n", getCurrentTime(), type.toString(), msg));
        } catch (IOException e) {
            System.out.println("Can not write to" + FILE_PATH);
        }
    }

    private String getCurrentTime() {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        return sdf.format(new Date());
    }

    public enum Type {
        ERROR,
        DEBUG,
        PROCESS
    }
}
