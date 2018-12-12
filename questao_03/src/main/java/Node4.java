import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node4 {
    public static void main(String[] args) throws IOException, ClassNotFoundException {

        System.out.println("Node4 iniciado");
        ServerSocket server = new ServerSocket();
        server.bind(new InetSocketAddress("127.0.0.1", 2224));

        while (true) {
            Socket socket = server.accept();
            ObjectInputStream inputStream = new ObjectInputStream(socket.getInputStream());
            ValoresOperacao valores = (ValoresOperacao) inputStream.readObject();
            if(valores.getOperacao() == 1){
                executaOp1(valores);
            }else{
                executaOp2(valores);
            }
            socket.close();
        }
    }

    public static void executaOp1(ValoresOperacao valores) {
        int soma = valores.getNumero1() + valores.getNumero2();
        System.out.println("Resultado da Op1: "+ soma);
    }

    public static void executaOp2(ValoresOperacao valores) {
        int diferenca = valores.getNumero1() - valores.getNumero2();
        System.out.println("Resultado da Op2: "+ diferenca);
    }
}
