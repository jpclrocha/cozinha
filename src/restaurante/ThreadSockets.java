package restaurante;

import java.io.*;
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
        ArrayList<Mesa> mesasRestaurante = new ArrayList<>();
        ArrayList<Produto> carrinho = new ArrayList<>();
        estoque.add(new Produto(1,"Suco de Maracuja" ,8.50 , 50));
        estoque.add(new Produto(2,"Strogonoff" ,7 , 100));
        estoque.add(new Produto(3,"Agua" ,2 , 75));

        try{
            //Entrada de dados
            DataOutputStream saida = new DataOutputStream(socket.getOutputStream());
            DataInputStream entrada = new DataInputStream(socket.getInputStream());
            String msg = entrada.readUTF();

            if(msg.equals("2")){
                String cardapio = "";
                for(Produto p : estoque){
                    cardapio += p.toString();
                }
                saida.writeUTF(cardapio);
            } else if(msg.equals("3")){
                //soh pra ver se entra nesse if
                System.out.println(msg);
                /*
                int codigoInt = Integer.parseInt(codigo);
                for (Produto p : estoque){
                    if (p.getCodido() == codigoInt){
                        carrinho.add(p);
                        System.out.println(p);
                    }
                }
                 */
            } else if (msg.equals("4")) {
                double total = 0;
                for (Produto p : carrinho){
                    total += p.getPreco();
                }
                saida.writeUTF(Double.toString(total));
            }

        }catch (IOException e){
            System.out.println("Erro: " + e.toString());
        }
    }
}
