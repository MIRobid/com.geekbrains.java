import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class MyServer {
    private final int PORT = 8189;

    private List<ClientHandler> clients;
    private AuthService authService;

    public AuthService getAuthService() {
        return authService;
    }

    public MyServer() {
        try (ServerSocket server = new ServerSocket(PORT)) {
            authService = new BaseAuthService();
            authService.start();
            clients = new ArrayList<>();
            while (true) {
                System.out.println("Сервер ожидает подключения");
                Socket socket = server.accept();
                System.out.println("Клиент подключился");
                new ClientHandler(this, socket);
            }
        } catch (IOException e) {
            System.out.println("Ошибка в работе сервера");
        } finally {
            if (authService != null) {
                authService.stop();
            }
        }
    }

    public synchronized boolean isNickBusy(String nick) {
        for (ClientHandler o : clients) {
            if (o.getName().equals(nick)) {
                return true;
            }
        }
        return false;
    }

    public void broadcastMsg(ClientHandler sender, String msg) {
        String message = String.format("%s : %s", sender.getNickname(), msg);
        for (ClientHandler o : clients) {
            o.sendMsg(msg);
        }
    }

    public void broadcastMsg(ClientHandler sender, String reciever, String msg) {
        String message = String.format("[%s] private [%s] : %s", sender.getNickname(), msg);
        for (ClientHandler c : clients) {
            if (c.getNickname().equals(reciever)) {
                c.sendMsg(message + "\n");
                if(c.equals(sender)){
                    sender.sendMsg(message);
                }
                return;
            }
        }

        public synchronized void unsubscribe (ClientHandler o){
            clients.remove(o);
        }

        public synchronized void subscribe (ClientHandler o){
            clients.add(o);
        }
    }
}