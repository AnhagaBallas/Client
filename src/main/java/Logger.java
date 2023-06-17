import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class Logger {
    private static Logger instance = null;
    private static File logFile = new File("ClientLog.txt");
    private static FileWriter fileWriter;

    private Logger() {
    }

    public static Logger get() {
        try {
            fileWriter = new FileWriter(logFile);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        if (instance == null) instance = new Logger();
        return instance;
    }

    public void log(String messege) {
        try {
            fileWriter.write(messege + "\n");
            fileWriter.flush();


        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(messege);


    }


}
