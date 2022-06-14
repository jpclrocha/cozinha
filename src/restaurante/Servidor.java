package restaurante;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Scanner;


public class Servidor {
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
            System.out.println("Produto cadastrado!");
        } else if(opcao.equals("2")){
            for (ThreadSockets t : conectados){
                System.out.println(t.toString());
            }
        }else if(opcao.equals("3")){
            System.out.println("Voce escolheu sair");
            ss.close();
        }
    }
    static ArrayList<ThreadSockets> conectados = new ArrayList<>();
    //Servidor = cozinha
    public static void main(String[] args) throws IOException {
        try{
            //Abrir servidor para conexão
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("A porta 8000 foi aberta");
            int codigo = 0;
            while (!serverSocket.isClosed()){
                //Esperar conexão
                Socket socket = serverSocket.accept();
                ThreadSockets thread = new ThreadSockets(socket , codigo);
                while (true){
                    thread.atualizaEstoque(estoqueServidor);
                }

                conectados.add(thread);
                codigo += 1;
                thread.start();
                menuServidor(thread,serverSocket);
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

