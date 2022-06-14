package restaurante;


import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

    public static void menu(Socket socket) throws IOException{
        while (true){
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            System.out.println("1 - Cadastrar Mesa \n2 - Cardapio \n3 - Fazer Pedido \n4 - Pedir conta \n5 - Sair");
            Scanner input = new Scanner(System.in);
            System.out.println("Sua escolha:");
            String numero = input.nextLine();
            if(numero.equals("1")){
                saida.writeUTF("1");
                System.out.println("Digite seu nome para cadastrar na mesa:");
                String nome = input.nextLine();
                saida.writeUTF(nome);

            }else if (numero.equals("2")){
                saida.writeUTF("2");
                String cardapio = entrada.readUTF();
                System.out.println(cardapio);

            }else if(numero.equals("3")){
                saida.writeUTF("3");
                System.out.println("Digite o codigo do produto a ser pedido: ");
                String codigo = input.nextLine();
                saida.writeUTF(codigo);

            }else if (numero.equals("4")) {
                saida.writeUTF("4");
                System.out.println(entrada.readUTF());

            }else if (numero.equals("5")) {
                break;
            }
        }
    }

    public static void main(String[] args) throws IOException, InterruptedException {
        //Abrir a conexão
        Socket socket = new Socket("127.0.0.1",8000);
        menu(socket);

    }
}
