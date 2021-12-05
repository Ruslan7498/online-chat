package socket;

public class ClientThread implements Runnable {
    public static final String EXIT = "/exit";
    public static final String ONLINE = "online";
    public static final String OFFLINE = "offline";
    public static final Log log = new Log();
    private Net net;
    private String name;
    private String fileName;

    public ClientThread(Net net) {
        this.net = net;
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        try {
            name = net.readLine(); //получить имя клиента
            Thread.currentThread().setName(name);
            fileName = name + ".log"; //установить имя файла логирования
            Server.sendMessage(name, ONLINE); //отправить сообщение, что клиент вошел в чат
            while (true) {
                String message = net.readLine(); //получить сообщение от клиента
                if (message.equals(EXIT)) {
                    Server.sendMessage(name, OFFLINE); //отправить сообщение, что клиент вышел из чата
                    break;
                }
                Server.sendMessage(name, message); //оправить сообщение всем клиентам чата
            }
            Server.removeClient(this); //выйти из чата
        } finally {
            net.close();
        }
    }

    public void sendMessage(String nameClient, String msg) {
        log.writeMessage(fileName, nameClient, msg); //записать сообщение в файл логирования клиента
        net.writeLine(nameClient + ": " + msg); //отправить сообщение клиентам
    }
}
