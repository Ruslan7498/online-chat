package socket;

import java.io.BufferedWriter;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.util.Date;

public class Log {
    public void writeMessage(String fileName, String name, String msg) {
        try (FileOutputStream outputStream = new FileOutputStream(fileName, true);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(outputStream))) {
            Date date = new Date();
            StringBuilder message = new StringBuilder();
            message.append(date);
            message.append(" ");
            message.append(name);
            message.append(": ");
            message.append(msg);
            writer.write(String.valueOf(message));
            writer.newLine();
            writer.flush();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
