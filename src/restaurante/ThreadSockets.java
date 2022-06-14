package restaurante;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.Scanner;


public class ThreadSockets extends Thread{
    ArrayList<Produto> estoqueCliente = new ArrayList<>();
    ArrayList<Produto> carrinho = new ArrayList<>();
    Mesa a = new Mesa();
    String nome;
    private Socket socket;

    public ThreadSockets (Socket s , int codigo){
        this.socket = s;
        a.setCodigo(codigo);
    }

    public void atualizaEstoque(ArrayList<Produto> estoque){
        for(Produto p : estoque){
            estoqueCliente.add(p);
        }
    }
    public void run(){
        try{
            while (true){
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
                    for(Produto p : estoqueCliente){
                        cardapio += p.toString();
                    }
                    saida.writeUTF(cardapio);
                } else if(msg.equals("3")){
                    String codigo = entrada.readUTF();
                    int c = Integer.parseInt(codigo);
                    for (Produto p : estoqueCliente){
                        if (p.getCodido() == c){
                            carrinho.add(p);
                            p.setQtdDisponivel(p.getQtdDisponivel() - 1);
                        }
                    }
                    System.out.println(carrinho.toString());
                } else if (msg.equals("4")) {
                    double total = 0;
                    for (Produto p : carrinho){
                        total += p.getPreco();
                    }
                    saida.writeUTF(Double.toString(total));
                } else if (msg.equals("5")) {
                    break;
                }
            }
        }catch (SocketException e){
            System.out.println("Um cliente se desconectou!");
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
