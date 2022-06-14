package restaurante;

import java.io.*;
import java.net.Socket;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;


public class ThreadSockets extends Thread{
    ArrayList<Produto> estoque = new ArrayList<>();
    ArrayList<Produto> carrinho = new ArrayList<>();
    Mesa a = new Mesa();
    String nome;
    private Socket socket;

    public ThreadSockets (Socket s , int codigo){
        this.socket = s;
        a.setCodigo(codigo);
    }

    public void run(){
        estoque.add(new Produto(1,"Suco de Maracuja" ,8.50 , 50));
        estoque.add(new Produto(2,"Strogonoff" ,7 , 100));
        estoque.add(new Produto(3,"Agua" ,2 , 75));

        try{
            //Entrada de dados
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String msg = entrada.readUTF();

            if(msg.equals("1")){
                String nome = entrada.readUTF();
                a.setNome(nome);
            }
            else if(msg.equals("2")){
                String cardapio = "";
                for(Produto p : estoque){
                    cardapio += p.toString();
                }
                saida.writeUTF(cardapio);
            } else if(msg.equals("3")){
                //soh pra ver se entra nesse if
                System.out.println(msg);
            } else if (msg.equals("4")) {
                double total = 0;
                for (Produto p : carrinho){
                    total += p.getPreco();
                }
                saida.writeUTF(Double.toString(total));
            } else if (msg.equals("5")) {
                a.setSaida();
            }

        }catch (Exception e){
            System.out.println("Erro: " + e.toString());
        }
    }

    public String toString(){
        String palavra = "";
        palavra += "Nome do cliente: " + a.getNome();
        palavra += "\nHorario de entrada: " + a.getEntrada();
        palavra += "\nHorario de saida: " + a.getSaida();
        palavra += "\nTotal da conta: " + a.getTotal();
        palavra += "\nCodigo da mesa: " + a.getCodigo();
        return palavra;
    }
}
