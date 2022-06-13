package restaurante;

import java.io.Serializable;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Mesa implements Serializable {

    private int codigo;
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

    public void adicionaProduto(Produto p){
        pedidos.add(p);
    }
    public double getTotal(){
        double total = 0;
        for (Produto p : pedidos){
            total += p.getPreco();
        }
        return total;
    }

    public void setSaida(){
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDateTime now = LocalDateTime.now();
        this.saida = dtf.format(now);
    }

    public Object getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }
}
