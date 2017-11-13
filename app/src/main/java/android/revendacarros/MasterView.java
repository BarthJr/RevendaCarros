package android.revendacarros;

import android.content.Intent;
import android.os.Bundle;
import android.revendacarros.Model.Carro;
import android.revendacarros.Model.CarroImplements;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MasterView extends AppCompatActivity {
    private static CarroAdapter carroAdapter;
    CarroImplements carroImplements;
    List<Carro> carros, vendidos;
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_master_view);
        carroImplements = (CarroImplements) getIntent().getSerializableExtra("carroImplements");
        carros = new ArrayList<>();
        carros = carroImplements.getLista();
        vendidos = new ArrayList<>();
    }

    @Override
    protected void onStart() {
        super.onStart();

        listView = (ListView) findViewById(R.id.carros);

        carroAdapter = new CarroAdapter(getApplicationContext(), carros);
        listView.setAdapter(carroAdapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent it = new Intent(MasterView.this,DetailView.class);
                it.putExtra("carro", carros.get(position));
                it.putExtra("position", position);
                startActivityForResult(it, 1);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.relat:
                goToRelatorioView();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }


    public void onActivityResult(int requestCode, int resultCode, Intent it) {
        super.onActivityResult(requestCode, resultCode, it);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            int carroVendido = it.getIntExtra("carroVendido", 0);
            vendidos.add(carros.get(carroVendido));
            carros.remove(carroVendido);
        }
    }


    public void goToRelatorioView() {
        Intent intent = new Intent(this, RelatorioView.class);
        CarroImplements vendidos = new CarroImplements();
        vendidos.setLista(this.vendidos);
        intent.putExtra("vendidos",vendidos);
        startActivity(intent);

    }
}
