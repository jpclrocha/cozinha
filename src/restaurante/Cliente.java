package restaurante;


import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {
    public static void menu(Socket socket) throws IOException {
        DataInputStream entrada = new DataInputStream(socket.getInputStream());
        DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
        System.out.println("1 - Cadastrar Mesa \n2 - Cardápio \n3 - Fazer Pedido \n4 - Pedir conta \n5 - Sair");
        Scanner input = new Scanner(System.in);
        System.out.println("Sua escolha:");
        String numero = input.nextLine();
        if(numero.equals("1")){
            System.out.println("Digite seu nome para cadastrar uma mesa: ");
            String nome = input.nextLine();
            saida.writeUTF(nome);
        }else if (numero.equals("2")){
            saida.writeUTF("2");
            String cardapio = entrada.readUTF();
            System.out.println(cardapio);

        }else if(numero.equals("3")){
            saida.writeUTF("3");

        }else if (numero.equals("4")) {
            saida.writeUTF("4");
            String a = entrada.readUTF();
            System.out.println(a);

        }else if (numero.equals("5")) {
            System.out.println("Obrigado pelo visita!");
            entrada.close();
            saida.close();
            socket.close();
        }
    }

    public static void main(String[] args) throws IOException {
        //Abrir a conexão
        Socket socket = new Socket("127.0.0.1",8000);
        while (true){
            menu(socket);
        }

        /*
            Scanner input = new Scanner(System.in);
            String nome = input.nextLine();
            saida.writeUTF(nome);
            String novaMensagem = entrada.readUTF();
            System.out.println(novaMensagem);
         */

    }
}
