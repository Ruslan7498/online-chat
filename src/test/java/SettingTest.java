import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class SettingTest {
    public static final String SETTING_FILE = "settings.txt";
    public static final String SETTING_IP = "ip";
    public static final String SETTING_PORT = "port";
    public static final char SETTING_CHAR = '#';

    @ParameterizedTest
    @ValueSource(strings = {SETTING_IP, SETTING_PORT})
    public void readSetting(String settingName) throws IOException {
        //String settingName = "port";
        String expectedIp = "127.0.0.1";
        String expectedPort = "8000";
        String result = "";
        int chars;
        try (BufferedReader reader = new BufferedReader(new FileReader(SETTING_FILE))) {
            do {
                chars = reader.read(); //прочитать символы
                if (chars == SETTING_CHAR) { //если символ равен #
                    String name = reader.readLine(); //прочить строку после символа #
                    if (name.equals(settingName)) { //если строка совпала
                        while ((result != null)) {
                            result = reader.readLine(); //прочитать информацию
                            break;
                        }
                    }
                }
            } while (chars != -1);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        if (settingName.equals(SETTING_IP)) {
            Assertions.assertEquals(expectedIp, result);
        } else {
            Assertions.assertEquals(expectedPort, result);
        }
    }
}
