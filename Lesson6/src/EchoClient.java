import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class EchoClient {
    private static final String SERVER_ADDR = "localhost";
    private static final int SERVER_PORT = 8189;
    Scanner scanner = new Scanner(System.in);
    Socket socket=null;

    public EchoClient() {
        try {
            socket = new Socket(SERVER_ADDR, SERVER_PORT);
            System.out.println("Подключен к серверу");
            DataInputStream in = new DataInputStream(socket.getInputStream());
            DataOutputStream out = new DataOutputStream(socket.getOutputStream());

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
                if (str.equalsIgnoreCase("/close")) {
                    System.out.println("Потеряно соединение с сервером");
                    out.writeUTF("/close");
                    break;
                } else {
                    System.out.println("Сервер " + str);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                socket.close();
            } catch (IOException | NullPointerException e) {
                e.printStackTrace();
            }
        }
    }
}