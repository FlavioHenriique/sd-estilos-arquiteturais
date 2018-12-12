import object.ValoresOperacao;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Random;
import java.util.Scanner;

public class Client {

    private static Random random;

    public static void main(String[] args) throws IOException {
        random = new Random();

        Scanner scanner = new Scanner(System.in);
        System.out.println("Qual node deseja usar? (1, 2 ou 3)");
        int acao = scanner.nextInt();
        System.out.println("Digite qual operação deseja. 1 ou 2 ?");
        int operacao = scanner.nextInt();

        while(acao <4 && acao > 0){
            // 2221 -> nodes.Node1    2222 -> nodes.Node2   2223 -> nodes.Node3
            Socket socket = new Socket("127.0.0.1",2220 + acao);

            System.out.println("Chamando operação em Node"+ acao);
            ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
            outputStream.writeObject(geraValoresOperacao(operacao));

            socket.close();
            System.out.println("Qual node deseja usar? (1, 2 ou 3)");
            acao = scanner.nextInt();
            System.out.println("Digite qual operação deseja. 1 ou 2 ?");
            operacao = scanner.nextInt();
        }
    }

    public static ValoresOperacao geraValoresOperacao(int operacao){

        ValoresOperacao valores = new ValoresOperacao();
        valores.setNumero1(random.nextInt(10));
        valores.setNumero2(random.nextInt(10));
        valores.setOperacao(operacao);
        return  valores;
    }
}
