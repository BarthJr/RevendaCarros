package android.revendacarros;

import android.content.Intent;
import android.os.Bundle;
import android.revendacarros.Model.Carro;
import android.revendacarros.Model.CarroImplements;
import android.support.v7.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.Timer;
import java.util.TimerTask;

public class MainActivity extends AppCompatActivity implements Response.Listener,Response.ErrorListener{
    public static final String REQUEST_TAG = "MainActivity";
    RequestQueue requestQueue;

    CarroImplements carroImplements;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    @Override
    protected void onStart() {
        super.onStart();
        requestQueue=CustomVolleyRequestQueue.getmInstance(this.getApplicationContext()).getmRequestQueue();
        carroImplements = new CarroImplements();
        String JsonURL = "https://dl.dropboxusercontent.com/s/d24im9i7e3tczls/carros.json";
        final CustomJSONObjectRequest jsonRequest = new CustomJSONObjectRequest(Request.Method.GET,
                JsonURL,
                new JSONObject(),this,this);
        jsonRequest.setTag(REQUEST_TAG);
        requestQueue.add(jsonRequest);
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(requestQueue !=null) {
            requestQueue.cancelAll(REQUEST_TAG);
        }
    }

    @Override
    public void onErrorResponse(VolleyError error) {

    }

    @Override
    public void onResponse(Object response) {
        try {
            JSONArray array = ((JSONObject) response).getJSONArray("carros");
            int tam = array.length();
            for(int i=0; i<tam; i++) {

                JSONObject carroObj = (JSONObject)array.get(i);
                Carro carro = new Carro();
                carro.setAno(carroObj.getString("ano"));
                carro.setCor(carroObj.getString("cor"));
                carro.setFabricante(carroObj.getString("fabricante"));
                carro.setFoto(carroObj.getString("foto"));
                carro.setModelo(carroObj.getString("modelo"));
                carro.setPreco(carroObj.getString("preco"));
                carroImplements.addCarro(carro);
            }

        }
        catch (JSONException e) {
            e.printStackTrace();
        }
        finally {
            new Timer().schedule(new TimerTask() {
                @Override
                public void run() {
                    goToMasterView();
                }
            }, 1000);
        }

    }

    public void goToMasterView () {
        Intent it = new Intent(this, MasterView.class);
        it.putExtra("carroImplements", carroImplements);
        startActivity(it);
        finish();
    }

}
