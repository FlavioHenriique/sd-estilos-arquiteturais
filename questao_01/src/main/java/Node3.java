import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.List;

public class Node3 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("localhost", 2221));
        while(true){
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            List<Integer> numeros = (List<Integer>) inputStream.readObject();
            if(numeros.size() == 1){
                System.out.println("Números iguais");
            }else{
                int num1 = numeros.get(0);
                int num2 = numeros.get(1);

                double resultado = Math.pow(num1,num1) + Math.pow(num2, num2);
                System.out.println("Resultado: "+ resultado);
            }

        }
    }
}
