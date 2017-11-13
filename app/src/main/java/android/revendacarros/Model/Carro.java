package android.revendacarros.Model;

import java.io.Serializable;

/**
 * Created by barth on 10/11/17.
 */

public class Carro implements Serializable {
    private int id;
    private String foto, modelo, fabricante, ano, cor, preco;

    public Carro() {}

    public Carro(int id, String ano, String foto, String modelo, String fabricante, String cor, String preco) {
        this.id = id;
        this.ano = ano;
        this.foto = foto;
        this.modelo = modelo;
        this.fabricante = fabricante;
        this.cor = cor;
        this.preco = preco;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAno() {
        return ano;
    }

    public void setAno(String ano) {
        this.ano = ano;
    }

    public String getFoto() {
        return foto;
    }

    public void setFoto(String foto) {
        this.foto = foto;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getFabricante() {
        return fabricante;
    }

    public void setFabricante(String fabricante) {
        this.fabricante = fabricante;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getPreco() {
        return preco;
    }

    public void setPreco(String preco) {
        this.preco = preco;
    }
}