package restaurante;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;


public class ThreadSockets extends Thread{
    ArrayList<Produto> estoque = new ArrayList<>();
    ArrayList<Produto> carrinho = new ArrayList<>();
    private Socket socket;

    public ThreadSockets (Socket s){
        this.socket = s;
    }

    public void run(){

        estoque.add(new Produto(1,"Suco de Maracuja" ,8.50 , 50));
        estoque.add(new Produto(2,"Strogonoff" ,7 , 100));
        estoque.add(new Produto(3,"Agua" ,2 , 75));

        try{
            //Entrada de dados
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String mensagem = entrada.readUTF();

            if(mensagem.equals("2")){
                String palavra = "";
                for(Produto p : estoque){
                    palavra += p.toString()+"\n";
                }
                saida.writeUTF(palavra);
            } else if (mensagem.equals("3")) {


            } else if (mensagem.equals("4")) {
                saida.writeUTF("se nao funcionar agora eu vou coringar");
            }

        }catch (IOException e){
            System.out.println("Erro: " + e.toString());
        }
    }
}
