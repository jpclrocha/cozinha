package restaurante;


import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;


public class Servidor {
    static ArrayList<String> mesas = new ArrayList<>();
    //Servidor = cozinha
    public static void main(String[] args) throws IOException {
        try{
            //Abrir servidor para conexão
            ServerSocket serverSocket = new ServerSocket(8000);
            System.out.println("A porta 8000 foi aberta");
            int codigo = 0;
            while (true){
                //Esperar conexão
                Socket socket = serverSocket.accept();

                ThreadSockets thread = new ThreadSockets(socket , codigo);
                mesas.add(thread.toString());
                System.out.println(mesas.toString());
                codigo += 1;
                thread.start();
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}

