package android.revendacarros;

import android.os.Bundle;
import android.revendacarros.Model.Carro;
import android.revendacarros.Model.CarroImplements;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class RelatorioView extends AppCompatActivity {
    ArrayList<String> modelos;
    ArrayAdapter<String> adapter;
    List<Carro> carros;
    ListView vendidos;
    TextView totalCarros;
    TextView totalVendas;
    CarroImplements carroImplements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_relatorio);

        totalCarros = (TextView) findViewById(R.id.totalCarros);
        totalVendas = (TextView) findViewById(R.id.totalVendas);

        carroImplements =(CarroImplements)getIntent().getSerializableExtra("vendidos");
        carros = carroImplements.getLista();
        modelos = new ArrayList<>();
        int tam = carros.size();

        for (int i = 0; i < tam; i++) {
            modelos.add(carros.get(i).getModelo());
        }
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,modelos);
        vendidos = (ListView) findViewById(R.id.listaVendidos);
        vendidos.setAdapter(adapter);
        double total = 0;
        for (int i = 0; i < tam; i++) {
            String valor = carros.get(i).getPreco();
            valor = valor.replace(".","");
            valor = valor.replace(",",".");
            total += Double.valueOf(valor);
        }
        totalCarros.setText("Total de carros: "+tam);
        totalVendas.setText("Total de vendas: "+total);

    }
}
