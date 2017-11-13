package android.revendacarros.Model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by barth on 10/11/17.
 */

public class CarroImplements implements Serializable {
    private List<Carro> carros = new ArrayList<>();
    private List<Carro> vendidos = new ArrayList<>();


    public CarroImplements() {
    }

    public List<Carro> getVendidos() {
        return vendidos;
    }

    public void setVendidos(List<Carro> vendidos) {
        this.vendidos = vendidos;
    }

    public CarroImplements(List<Carro> carros) {
        this.carros = carros;
    }
    public void addCarro(Carro carro) {
        carros.add(carro);
    }

    public List<Carro> getLista() {
        return carros;
    }

    public void setLista(List<Carro> carros) {
        this.carros = carros;
    }
}
