import javax.xml.bind.ValidationEventLocator;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Node4 iniciado");
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2223));

        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao valores = (ValoresOperacao) inputStream.readObject();
            if (valores.getOperacao() == 1) {
                passaOp1(valores);
            } else {
                chamaOp2(valores);
            }
            socket.close();
        }
    }

    public static void passaOp1(ValoresOperacao valores) throws IOException {
        System.out.println("Passando dados da op1 para Node2");
        Socket socket = new Socket("127.0.0.1",2222);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(valores);
        outputStream.flush();
        socket.close();
    }

    public static void chamaOp2(ValoresOperacao valores) throws IOException {
        System.out.println("Chama op2 em Node4");
        Socket socket = new Socket("127.0.0.1",2224);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(valores);
        outputStream.flush();
        socket.close();
    }


}
