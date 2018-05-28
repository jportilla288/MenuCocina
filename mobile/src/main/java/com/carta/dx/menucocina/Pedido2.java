package com.carta.dx.menucocina;

import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class Pedido2 extends AppCompatActivity {
    public TextView preparacion;
    private RequestQueue requestQueue;
    String pedudi;
    private String consultarUrl ="http://172.17.0.17/MenuDigitalNueva/consultar_pedido_mesaTodos.php";
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pedido2);


        final TextView preparacion= (TextView)findViewById(R.id.preparaciontexto);


        requestQueue = Volley.newRequestQueue(getApplicationContext());

        Pedido pedidocli = new Pedido();
        ArrayList<Platos> p1 = new ArrayList<Platos>();

        Bundle extras = getIntent().getExtras();
        if(getIntent().getSerializableExtra("pedidocliente")!=null){

            //p1= (ArrayList<Platos>) extras.getSerializable("pedidocliente");
            pedidocli = (Pedido) extras.getSerializable("pedidocliente");

          /*  try {
                 tooodo = new JSONArray(pedidocli);
            } catch (JSONException e) {
                e.printStackTrace();
            }*/

            assert pedidocli != null;



//for para que me muestre la cantidad y los precios totales por platos
            int totalEntradas=0;
            int totalplatoFuerte=0;
            int totalBebidas=0;
            int totalPostres=0;

            if(pedidocli.getEntradas()!=null) {
                Log.i("precioo",pedidocli.getEntradas().get(0).getPrecio()+"");
                pedudi =pedudi+pedidocli.getEntradas().get(0).getCantidad()+" "+pedidocli.getEntradas().get(0).getProducto()+" "+pedidocli.getEntradas().get(0).getPrecio();

                for (int i = 0; i < pedidocli.getEntradas().size(); i++) {

                    totalEntradas = pedidocli.getEntradas().get(i).getPrecio() + totalEntradas;
                }

                pedudi=pedudi+"\n "+"total entradas "+totalEntradas+"\n ";
            }


            if(pedidocli.getPlatoFuerte()!=null) {

                pedudi = pedudi + pedidocli.getPlatoFuerte().get(0).getCantidad() + " " + pedidocli.getPlatoFuerte().get(0).getProducto() + " " + pedidocli.getPlatoFuerte().get(0).getPrecio();

                for (int i = 0; i < pedidocli.getPlatoFuerte().size(); i++) {

                    totalplatoFuerte = pedidocli.getPlatoFuerte().get(i).getPrecio() + totalplatoFuerte;
                }
                pedudi = pedudi + "total plato fuerte " + totalplatoFuerte + "\n ";
            }



            if(pedidocli.getBebidas()!=null) {

                pedudi = pedidocli.getBebidas().get(0).getCantidad() + " " + pedidocli.getBebidas().get(0).getProducto() + " " + pedidocli.getBebidas().get(0).getPrecio();

                for (int i = 0; i < pedidocli.getBebidas().size(); i++) {

                    totalBebidas = pedidocli.getBebidas().get(i).getPrecio() + totalBebidas;
                }
                pedudi = pedudi + "total Bebidas " + totalBebidas + "\n ";
            }

            if(pedidocli.getPostres()!=null) {

                pedudi = pedidocli.getPostres().get(0).getCantidad() + " " + pedidocli.getPostres().get(0).getProducto() + " " + pedidocli.getPostres().get(0).getPrecio();


                for (int i = 0; i < pedidocli.getPostres().size(); i++) {

                    totalPostres = pedidocli.getPostres().get(i).getPrecio() + totalPostres;
                }
                pedudi = pedudi + "total postres " + totalEntradas + "\n ";
            }


            pedudi=pedudi.replace("null","");
            pedudi=pedudi.replace("0","");
            pedudi=pedudi + '\n'+" Valor Total = "+pedidocli.getValor();


        }
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                consultarUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray pedido = response.getJSONArray("pedido");
                    for (int i = 0; i < pedido.length(); i++) {
                        JSONObject ped = pedido.getJSONObject(i);

                        String producto = ped.getString("producto");
                        String mesa = ped.getString("mesa");

                        String precio = ped.getString("precio");


                        preparacion.setText(producto + " " + " "  + "");
                    }


                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {

            }
        });
        requestQueue.add(jsonObjectRequest);


        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Pedido2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.carta.dx.menucocina/http/host/path")
        );
        AppIndex.AppIndexApi.start(client, viewAction);
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "Pedido2 Page", // TODO: Define a title for the content shown.
                // TODO: If you have web page content that matches this app activity's content,
                // make sure this auto-generated web page URL is correct.
                // Otherwise, set the URL to null.
                Uri.parse("http://host/path"),
                // TODO: Make sure this auto-generated app deep link URI is correct.
                Uri.parse("android-app://com.carta.dx.menucocina/http/host/path")
        );
        AppIndex.AppIndexApi.end(client, viewAction);
        client.disconnect();
    }
}
