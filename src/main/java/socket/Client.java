package socket;

import java.util.Scanner;

public class Client {
    public static final String SETTING_FILE = "settings.txt";
    public static final String SETTING_IP = "ip";
    public static final String SETTING_PORT = "port";
    public static final char SETTING_CHAR = '#';
    public static final String EXIT = "/exit";

    public static final Scanner scanner = new Scanner(System.in);
    public static final Setting setting = new Setting();

    public static void main(String[] args) {
        Net net = null;
        String ip = setting.readSetting(SETTING_FILE, SETTING_IP, SETTING_CHAR); //получить IP из файла настроек
        int port = Integer.parseInt(setting.readSetting(SETTING_FILE, SETTING_PORT, SETTING_CHAR)); //получить номер порта из файла настроек
        try {
            net = new Net(ip, port); //подключение к серверу
            System.out.println("What your name?");
            String name = scanner.nextLine(); //получить имя клиента
            net.writeLine(name); //отправить имя клиента на сервер
            while (true) {
                System.out.println("Enter your message (/exit):");
                String message = scanner.nextLine(); //получить сообщение
                if (message.equals(EXIT)) {
                    net.writeLine(message);
                    break;
                }
                net.writeLine(message); //отправить сообщение на сервер
                //String response = net.readLine();
                //System.out.println(response);
            }
        } finally {
            net.close();
        }
    }
}
