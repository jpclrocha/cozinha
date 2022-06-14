package restaurante;

import java.io.Serializable;

public class Produto implements Serializable {
    private int codido;
    private String descricao;
    private double preco;
    private int qtdDisponivel;

    public Produto(int codido, String descricao, double preco, int qtdDisponivel) {
        this.codido = codido;
        this.descricao = descricao;
        this.preco = preco;
        this.qtdDisponivel = qtdDisponivel;
    }

    public int getCodido() {
        return codido;
    }

    public void setCodido(int codido) {
        this.codido = codido;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public double getPreco() {
        return preco;
    }

    public void setPreco(double preco) {
        this.preco = preco;
    }

    public int getQtdDisponivel() {
        return qtdDisponivel;
    }

    public void setQtdDisponivel(int qtdDisponivel) {
        this.qtdDisponivel = qtdDisponivel;
    }

    public String toString(){
        String formata = "";
        formata += "Codigo do produto: " + getCodido();
        formata += "\nDescricao do produto: " + getDescricao();
        formata += "\nPreco: " + getPreco();
        return formata;
    }


    public Object getCodigo() {
        return this.codido;
    }
}
