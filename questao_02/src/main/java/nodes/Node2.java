package nodes;

import object.ValoresOperacao;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.math.BigDecimal;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node2 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Iniciando Node 2");
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2222));

        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao valores = (ValoresOperacao) inputStream.readObject();
            System.out.println("Números recebidos: " + valores.getNumero1() + " e " + valores.getNumero2());
            if(valores.getOperacao() == 1){
                executaOp1(valores.getNumero1(), valores.getNumero2());
            }
            socket.close();
        }
    }

    public static void executaOp1(int n1, int n2) {

        double valor = 2 * n1 * n2;
        BigDecimal bigDecimal = new BigDecimal(valor);
        System.out.println("Valor da operação 1: " + bigDecimal.toString());
    }

}
