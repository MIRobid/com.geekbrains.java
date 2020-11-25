import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    Socket clientSocket = null;
    Scanner scanner = new Scanner(System.in);

    public Server() {
        try (ServerSocket serverSocket = new ServerSocket(8189)) {
            System.out.println("Сервер запущен");

            clientSocket = serverSocket.accept();
            System.out.println("Клиент подключился" + clientSocket.getRemoteSocketAddress());
            DataInputStream in = new DataInputStream(clientSocket.getInputStream());
            DataOutputStream out = new DataOutputStream(clientSocket.getOutputStream());

            // Поток на чтение
            Thread threadReader = new Thread(() -> {
                try {
                    while (true) {
                        out.writeUTF(scanner.nextLine());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            });
            threadReader.setDaemon(true);
            threadReader.start();

            while (true) {
                String str = in.readUTF();
                if (str.equals("/close")) {
                    System.out.println("Клиент покинул сервер");
                    out.writeUTF("/close");
                    break;
                } else {
                    System.out.println("Клиент " + str);
                }

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                clientSocket.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}
