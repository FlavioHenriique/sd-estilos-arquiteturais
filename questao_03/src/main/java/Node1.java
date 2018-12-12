import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Node1 {

    private static Random random;

    public static void main(String[] args) throws IOException {

        System.out.println("Node1 iniciado");
        random = new Random();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Operação 1 ou 2?");
        int acao = scanner.nextInt();

        while (acao > 0 && acao < 3) {
            if (acao == 1) {
                escreveEmNodes(2223, 1);
            } else {
                escreveEmNodes(2222, 2);
            }
            System.out.println("Operação 1 ou 2?");
            acao = scanner.nextInt();
        }
    }

    public static void escreveEmNodes(int porta, int operacao) throws IOException {
        Socket socket = new Socket("127.0.0.1", porta);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(numeros(operacao));
        outputStream.flush();
        socket.close();
    }

    public static ValoresOperacao numeros(int operacao) {
        ValoresOperacao valores = new ValoresOperacao();
        valores.setNumero1(random.nextInt(10));
        valores.setNumero2(random.nextInt(10));
        valores.setOperacao(operacao);

        return valores;
    }
}
