package socket;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Setting {
    public String readSetting(String settingFile, String settingName, char settingChar) {
        String information = "";
        int chars;
        try (BufferedReader reader = new BufferedReader(new FileReader(settingFile))) {
            do {
                chars = reader.read(); //прочитать символы
                if (chars == settingChar) { //если символ равен #
                    String name = reader.readLine(); //прочить строку после символа #
                    if (name.equals(settingName)) { //если строка совпала
                        while ((information != null)) {
                            information = reader.readLine(); //прочитать информацию
                            return information;
                        }
                    }
                }
            } while (chars != -1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        return information;
    }
}
