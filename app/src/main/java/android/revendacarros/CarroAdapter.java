package android.revendacarros;

import android.content.Context;
import android.revendacarros.Model.Carro;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.android.volley.toolbox.ImageLoader;
import com.android.volley.toolbox.NetworkImageView;

import java.util.List;

/**
 * Created by barth on 05/11/17.
 */

public class CarroAdapter extends ArrayAdapter<Carro> {

    private final Context context;
    private final List<Carro> carros;
    private ImageLoader imageLoader;


    public CarroAdapter(Context context, List<Carro> data) {
        super (context,R.layout.linha, data);
        this.context = context;
        this.carros = data;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Carro carro = getItem(position);

        LayoutInflater inflater = LayoutInflater.from(getContext());
        View rowView = inflater.inflate(R.layout.linha, parent, false);

        TextView txtModelo = (TextView) rowView.findViewById(R.id.modelo);
        TextView txtPreco = (TextView) rowView.findViewById(R.id.preco);
        NetworkImageView imgFoto = rowView.findViewById(R.id.foto);

        txtModelo.setText(carro.getModelo());
        txtPreco.setText(carro.getPreco());
        imageLoader = CustomVolleyRequestQueue.getmInstance(this.getContext())
                .getImageLoader();
        imageLoader.get(carro.getFoto(), ImageLoader.getImageListener(imgFoto,
                R.mipmap.ic_launcher, android.R.drawable
                        .ic_dialog_alert));
        imgFoto.setImageUrl(carro.getFoto(),imageLoader);

        return rowView;
    }
}
