import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2222));
        System.out.println("Node2 iniciado");
        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao valores = (ValoresOperacao) inputStream.readObject();
            if (valores.getOperacao() == 1){
                chamaOp1(valores);
            }else{
                passaOp2(valores);
            }
            socket.close();
        }
    }

    public  static void chamaOp1(ValoresOperacao valores) throws IOException {
        System.out.println("Chama op1 em Node4");
        Socket socket = new Socket("127.0.0.1", 2224);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(valores);
        outputStream.flush();
        socket.close();
    }

    public  static void passaOp2(ValoresOperacao valores) throws IOException {
        System.out.println("Passando dados da op2 para Node3");
        Socket socket = new Socket("127.0.0.1", 2223);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(valores);
        outputStream.flush();
        socket.close();
    }
}
