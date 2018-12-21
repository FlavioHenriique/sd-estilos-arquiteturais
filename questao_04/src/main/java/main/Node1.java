package main;

import database.Conexao;
import entity.Usuario;
import org.omg.PortableServer.THREAD_POLICY_ID;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.net.Socket;

public class Node1 {

    private static Socket socket;

    public static void main(String[] args) throws IOException {


        System.out.println("Iniciando Node1");
        //escrever100();
        //escrever1000();
        escreverComThread();
    }


    public static void escrever100() throws IOException {

        long tempo = System.currentTimeMillis();
        for (int k = 1; k <= 100; k++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Cem " + k);
            escreverNode2(usuario);
        }
        tempo = System.currentTimeMillis() - tempo;
        System.out.println("Tempo total: " + tempo / 1000 + " segundos");
    }

    public static void escrever1000() throws IOException {

        long tempo = System.currentTimeMillis();
        for (int k = 1; k <= 1000; k++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Mil " + k);
            escreverNode2(usuario);
        }
        tempo = System.currentTimeMillis() - tempo;
        System.out.println("Tempo total: " + tempo / 1000 + " segundos");
    }

    public static void escreverComThread() throws IOException {

        long tempo = System.currentTimeMillis();
        for (int k = 0; k < 10; k++) {
            Usuario usuario = new Usuario();
            usuario.setNome("Mil " + k);
            new EscreveThread(usuario).start();
        }
        tempo = System.currentTimeMillis() - tempo;
        System.out.println("Tempo total: " + tempo / 1000 + " segundos");
    }

    public static void escreverNode2(Usuario usuario) throws IOException {
        socket = new Socket("127.0.0.1", 2222);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
        objectOutputStream.writeObject(usuario);
        byte[] b = new byte[10];
        socket.getInputStream().read(b);
        System.out.println("Recebido: " + new String(b));
        socket.close();
    }


    private static class EscreveThread extends Thread {

        public Usuario usuario;

        public EscreveThread(Usuario usuario) {
            this.usuario = usuario;
        }

        @Override
        public void run() {
            try {
                System.out.println("thread");
                for (int k = 1; k <= 100; k++) {
                    escreverNode2(usuario);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}


