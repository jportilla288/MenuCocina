package com.carta.dx.menucocina;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.util.Log;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.common.api.GoogleApiClient;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

import static android.R.attr.name;


public class MenuCocina extends AppCompatActivity {
    public ImageButton enviar;
    private TextView preparacion;
    public TextView mostrarped;
    public Chronometer crono;
    private long Time = 0;
    private RequestQueue requestQueue;
    private String consultarUrl =  "http://172.17.0.17/MenuDigitalNueva/Cliente.php";
    ArrayList<Pedido> listapedidos;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_cocina);

        ImageButton mesa1 = (ImageButton) findViewById(R.id.btmesa1);
        final Button m1 = (Button) findViewById(R.id.btcolor1);
        final Button m2 = (Button) findViewById(R.id.btcolor2);
        final Button m3 = (Button) findViewById(R.id.btcolor3);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ImageButton btmesa1 = (ImageButton)findViewById(R.id.btmesa1);

        btmesa1 .setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paso = new Intent(MenuCocina.this, pedido1.class);
                startActivity(paso);

            }
        });

        ImageButton btmesa2 = (ImageButton)findViewById(R.id.btmesa2);

        btmesa2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paso = new Intent(MenuCocina.this, Pedido2.class);
                startActivity(paso);

            }
        });

        ImageButton btmesa3 = (ImageButton)findViewById(R.id.btmesa3);

        btmesa3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent paso = new Intent(MenuCocina.this, Pedido3.class);
                startActivity(paso);

            }
        });




                requestQueue = Volley.newRequestQueue(getApplicationContext());

        Pedido pedidocli = new Pedido();

        Bundle extras = getIntent().getExtras();
        if (getIntent().getSerializableExtra("pedidocliente") != null) {

            pedidocli = (Pedido) extras.getSerializable("pedidocliente");

        }


        //enviar.setEnabled(false);
        //terminado.setEnabled(true)
       JsonArrayRequest req = new JsonArrayRequest(consultarUrl,
                new Response.Listener<JSONArray>() {
                    @Override
                    public void onResponse(JSONArray response) {
                       // Log.d(TAG, response.toString());

                        try {
                           String jsonResponse = "";
                            for (int i = 0; i < response.length(); i++) {

                                JSONObject pedido = (JSONObject) response.get(i);

                                String idPedido = pedido.getString("IdPedido");
                                String idEmpleado = pedido.getString("idEmpleado");
                                JSONObject precio = pedido.getJSONObject("Precio");
                                String duracion = pedido.getString("Duracion");
                                String numMesa = pedido.getString("numero de Mesa");

                                jsonResponse += "IdPedido: " + idPedido + "\n\n";
                                jsonResponse += "idEmpleado: " + idEmpleado + "\n\n";
                                jsonResponse += "Precio: " + precio + "\n\n";
                                jsonResponse += "Duracion: " + duracion + "\n\n\n";
                                jsonResponse += "numero de Mesa: " + numMesa + "\n\n\n";


                                String mesa = pedido.getString("mesa");
                                if(!mesa.equals("")) {
                                    if (mesa.equals("1"))
                                        m1.setBackgroundColor(Color.parseColor("#00ff00"));
                                    if (mesa.equals("1"))
                                        m2.setBackgroundColor(Color.parseColor("#00ff00"));
                                    if (mesa.equals("1"))
                                        m3.setBackgroundColor(Color.parseColor("#00ff00"));
                                }
                            }





                        } catch (JSONException e1) {
                            e1.printStackTrace();
                        }





                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d("error", "Error: " + error.getMessage());
                Toast.makeText(getApplicationContext(),
                        error.getMessage(), Toast.LENGTH_SHORT).show();

            }
        });

      /*  JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.POST,
                consultarUrl, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray pedido = response.getJSONArray("pedido");
                    for (int i = 0; i < pedido.length(); i++) {
                        JSONObject ped = pedido.getJSONObject(i);
                        Pedido mipedido = new Pedido();

                        Log.i("caja",ped.getString("valor"));
                        mipedido.setValor(ped.getString("valor"));
                        mipedido.setProducto(ped.getString("Producto"));

                        listapedidos.add(mipedido); //las agrego a mi pedido


                        String producto = ped.getString("producto");
                        String mesa = ped.getString("mesa");

                        if(!mesa.equals("")){
                            if(mesa.equals("1"))
                                m1.setBackgroundColor(Color.parseColor("#00ff00"));
                            if(mesa.equals("1"))
                                m2.setBackgroundColor(Color.parseColor("#00ff00"));
                            if(mesa.equals("1"))
                                m3.setBackgroundColor(Color.parseColor("#00ff00"));
                        }

                        String precio = ped.getString("precio");



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
        requestQueue.add(jsonObjectRequest);*/

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_menu_cocina, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        Action viewAction = Action.newAction(
                Action.TYPE_VIEW, // TODO: choose an action type.
                "MenuCocina Page", // TODO: Define a title for the content shown.
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
                "MenuCocina Page", // TODO: Define a title for the content shown.
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
