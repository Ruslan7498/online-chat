package socket;

import java.io.*;
import java.net.ServerSocket;
import java.util.ArrayList;

public class Server {
    public static final String SETTING_FILE = "settings.txt";
    public static final String SETTING_PORT = "port";
    public static final char SETTING_CHAR = '#';
    public static final String FILE_LOG = "server.log";
    public static final String NAME_SERVER = "server";
    public static final Setting setting = new Setting();
    public static ArrayList<ClientThread> clients = new ArrayList<>();
    public static final Log log = new Log();

    public static void main(String[] args) {
        int port = Integer.parseInt(setting.readSetting(SETTING_FILE, SETTING_PORT, SETTING_CHAR)); //получить номер порта из файла настроек
        try (ServerSocket server = new ServerSocket(port)) { //создание серверного сокета на определенном порту
            log.writeMessage(FILE_LOG, NAME_SERVER, "started"); //записать сообщение в файл логирования
            while (true) {
                Net net = new Net(server); // ожидание подключения
                ClientThread client = new ClientThread(net); //создание подключения к серверу
                clients.add(client); //добавление клиента в чат (список клиентов)
                new Thread(client).start(); //обрабатывание клиента в новом потоке
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    // рассылка сообщения всем клинтоам в чате (списке клиентов)
    public static void sendMessage(String nameClient, String msg) {
        log.writeMessage(FILE_LOG, nameClient, msg); //записать сообщение в файл логирования сервера
        //System.out.println(msg);
        for (ClientThread client : clients) {
            client.sendMessage(nameClient, msg);
        }
    }

    // удаление клиента из чата (списка клиентов)
    public static void removeClient(ClientThread client) {
        clients.remove(client);
    }
}
