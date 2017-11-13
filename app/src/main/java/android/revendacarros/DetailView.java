package android.revendacarros;

import android.content.Intent;
import android.os.Bundle;
import android.revendacarros.Model.Carro;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

public class DetailView extends AppCompatActivity {
    TextView modelo, ano, cor, preco, fabricante;
    Carro carro;
    int position;
    ImageLoader imageLoader;
    NetworkImageView networkImageView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_view);
        modelo = (TextView) findViewById(R.id.modelo);
        ano = (TextView) findViewById(R.id.ano);
        cor = (TextView) findViewById(R.id.cor);
        preco = (TextView) findViewById(R.id.preco);
        fabricante = (TextView) findViewById(R.id.fabricante);
        networkImageView = (NetworkImageView) findViewById(R.id.imageView);

        carro = (Carro) getIntent().getSerializableExtra("carro");
        position = getIntent().getIntExtra("position",0);

        modelo.setText(carro.getModelo());
        ano.setText(carro.getAno());
        cor.setText(carro.getCor());
        preco.setText(carro.getPreco());
        fabricante.setText(carro.getFabricante());
        imageLoader = CustomVolleyRequestQueue.getmInstance(this.getApplicationContext())
                .getImageLoader();
        imageLoader.get(carro.getFoto(), ImageLoader.getImageListener(networkImageView,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        networkImageView.setImageUrl(carro.getFoto(), imageLoader);

    }


    public void vender(View view) {
        Intent it = new Intent();
        it.putExtra("carroVendido", position);
        setResult(RESULT_OK, it);
        finish();

    }
}
