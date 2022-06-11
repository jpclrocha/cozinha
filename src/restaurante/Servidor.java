package restaurante;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Servidor {
    //Servidor = cozinha
    public static void main(String[] args) throws IOException {
        //Abrir servidor para conexão
        ServerSocket serverSocket = new ServerSocket(8000);
        System.out.println("A porta 8000 foi aberta");

        while (true){
            //Esperar conexão
            Socket socket = serverSocket.accept();

            ThreadSockets thread = new ThreadSockets(socket);
            thread.start();
        }
    }
}

