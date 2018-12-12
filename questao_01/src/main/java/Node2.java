import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {


        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 2222));
        while (true) {
            Socket socket = server.accept();

            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());
            List<Integer> numeros = (List<Integer>) ois.readObject();
            System.out.println("Recebido do Node1: ");
            numeros.forEach(System.out::println);

            if (numeros.get(0) != numeros.get(1)) {
                System.out.println("Enviando para o Node3 os dois números");
                escreverEmNode3(numeros);
            } else {
                System.out.println("Enviando para o Node3 o valor 0");
                numeros.clear();
                numeros.add(0);
                escreverEmNode3(numeros);
            }
            socket.close();
        }
    }

    public static void escreverEmNode3(List<Integer> numeros) throws IOException {
        Socket socket = new Socket("127.0.0.1",2221);
        ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
        oos.writeObject(numeros);
    }
}
