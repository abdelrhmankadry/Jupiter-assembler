package Instructions;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class Writer {
    private String filePath;
    static private Writer singleton;
    static private File file;
    static private PrintWriter printWriter;
    private Writer(String filePath) throws FileNotFoundException {
        this.filePath = filePath;
        this.file = new File(filePath);
        this.printWriter =  new PrintWriter(file);
    }
    public static Writer getInstance(String filePath) throws FileNotFoundException {
        if (singleton == null) {
            singleton = new Writer(filePath);
        }
        return singleton;
    }
    public static void write(String code) throws FileNotFoundException {
        StringBuilder stringBuilder = new StringBuilder();
        for (int i = 0; i < code.length(); i++) {
             if (Character.isLetterOrDigit(code.charAt(i))) {
                 stringBuilder.append(code.charAt(i));
                 }

            }
        for (int i = 1; i < 8; i++) {
            stringBuilder.insert(i*4+(i-1),'_');
        }
        printWriter.println(stringBuilder.toString());

    }
    public  static void changeFile(File file) throws FileNotFoundException {
        printWriter = new PrintWriter(file);
    }
    public static void close_file() {
      printWriter.close();
    }
}
