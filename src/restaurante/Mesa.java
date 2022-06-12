package restaurante;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Mesa implements Serializable {
    ArrayList<Produto> pedidos = new ArrayList<>();
    private String nome;
    private String entrada;
    private String saida;

    public Mesa(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.entrada = dtf.format(now);
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEntrada() {
        return entrada;
    }

    public void setEntrada(String entrada) {
        this.entrada = entrada;
    }

    public String getSaida(){
        return this.saida;
    }

    public void setSaida(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.saida = dtf.format(now);
    }

    public void adicionaPedido(int codigo, ArrayList<Produto> estoque){
        for(Produto p : estoque){
            if(p.getCodido() == codigo){
                pedidos.add(p);
            }else{
                System.out.println("Nao existe um produto com esse codigo");
            }
        }
    }

    public double getConta(){
        double total = 0;
        for(Produto p : pedidos){
            total += p.getCodido();
        }
        return total;
    }

    public String toString(){
        String palavra = "";
        palavra += "Nome do cliente: "+getNome();
        palavra += "Horario de entrada: "+getEntrada();
        return palavra;
    }

}
