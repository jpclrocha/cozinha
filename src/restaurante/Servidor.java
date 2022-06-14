package restaurante;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Servidor {
    static ArrayList<ThreadSockets> conectados = new ArrayList<>();
    static ArrayList<Produto> estoqueServidor = new ArrayList<>();

    public static void menuServidor(ThreadSockets thread, ServerSocket ss) throws IOException {
        System.out.println("1 - Cadastrar novo produto \n2 - Listar mesas \n3 - Sair");
        Scanner input = new Scanner(System.in);
        String opcao = input.nextLine();

        if (opcao.equals("1")){
            System.out.println("Digite a descricao do produto:");
            String descricao = input.nextLine();

            System.out.println("Digite o codigo do produto:");
            int codigo = input.nextInt();


            System.out.println("Digite o preco do produto:");
            double preco = input.nextDouble();

            System.out.println("Digite a quantidade disponivel do produto:");
            int qtdDisponivel = input.nextInt();


            Produto p = new Produto(codigo,descricao,preco,qtdDisponivel);
            estoqueServidor.add(p);
            menuServidor(thread , ss);
        } else if(opcao.equals("2")){
            for (ThreadSockets t : conectados){
                System.out.println(t.toString());
            }
        }else if(opcao.equals("3")){
            System.out.println("Voce escolheu sair");
            ss.close();
        }
    }
    //Servidor = cozinha
    public static void main(String[] args) throws IOException {
        estoqueServidor.add(new Produto(1,"Suco de Maracuja" ,8.50 , 50));
        estoqueServidor.add(new Produto(2,"Strogonoff" ,7 , 100));
        estoqueServidor.add(new Produto(3,"Agua" ,2 , 75));
        try{
            //Abrir servidor para conexão
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("A porta 8000 foi aberta");
            int codigo = 0;
            while (!serverSocket.isClosed()){
                if (codigo == 0){

                    //Esperar conexão
                    Socket socket = serverSocket.accept();
                    ThreadSockets thread = new ThreadSockets(socket , codigo);
                    conectados.add(thread);
                    for (ThreadSockets att : conectados){
                        att.atualizaEstoque(estoqueServidor);
                    }
                    codigo += 1;
                    thread.start();
                    menuServidor(thread,serverSocket);
                }else{
                    for (ThreadSockets att : conectados){
                        att.atualizaEstoque(estoqueServidor);
                    }
                    //Esperar conexão
                    Socket socket = serverSocket.accept();
                    ThreadSockets thread = new ThreadSockets(socket , codigo);
                    conectados.add(thread);
                    codigo += 1;
                    thread.start();
                    menuServidor(thread,serverSocket);
                }

            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

