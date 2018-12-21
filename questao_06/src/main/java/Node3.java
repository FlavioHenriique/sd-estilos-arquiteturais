import java.io.IOException;
import java.net.Socket;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class Node3 {

    private static DAO dao;

    public static void main(String[] args) throws IOException, SQLException {

        System.out.println("Iniciando Node1");
        dao = new DAO();
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite um nome para inserir no banco");
        String opcao = scanner.next();

        while (!opcao.equals("exit")) {
            dao.salvar(opcao);
            System.out.println("Digite um nome para inserir no banco");
            opcao = scanner.next();
        }

    }
}
