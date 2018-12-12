import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Node1 {
    public static void main(String[] args) throws IOException {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um número diferente de 0");
        int acao = scanner.nextInt();

        while(acao != 0){
            Socket socket = new Socket("127.0.0.1", 2222);
            int numero1 = random.nextInt(100);
            int numero2 = random.nextInt(100);
            List<Integer> numeros = new ArrayList<>();
            numeros.add(numero1);
            numeros.add(numero2);

            System.out.println("Enviando números para o Node2");
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            oos.writeObject(numeros);

            socket.close();
            System.out.println("Digite um número diferente de 0");
            acao = scanner.nextInt();
        }
    }
}
