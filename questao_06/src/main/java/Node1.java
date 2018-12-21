import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node1 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Iniciando Node1");

        ServerSocket serverSocket = new ServerSocket();
        serverSocket.bind(new InetSocketAddress("127.0.0.1", 2221));
        while (true) {
            Socket socket = serverSocket.accept();
            Entidade notificacao = (Entidade) new ObjectInputStream(socket.getInputStream()).readObject();
            System.out.println("Inserido no banco pelo Node3: " + notificacao.toString());
            socket.close();
        }
    }
}
