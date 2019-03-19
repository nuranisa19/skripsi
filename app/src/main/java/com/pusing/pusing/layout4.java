package com.pusing.pusing;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.pusing.pusing.NetworkRequest.NetworkRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

import static android.support.v7.media.MediaControlIntent.EXTRA_MESSAGE;

public class layout4 extends AppCompatActivity {

    String message;
    TextView nama ;
    TextView jumlah;
    TextView harga;
    TextView Total;
    Bundle link;
    Button Bayar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout4);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        nama = (TextView)findViewById(R.id.nama);
        jumlah = (TextView) findViewById(R.id.jumlah);
        harga = (TextView) findViewById(R.id.harga);
        Total = (TextView) findViewById(R.id.total);

       Bayar = (Button) findViewById(R.id.bayar);
       /* Bayar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent4 = new Intent(layout4.this, selesai.class);
                startActivity(intent4);
            }
        });
*/
        //
        link=getIntent().getExtras();
        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    Process_Data();
    }

    //ini fungsi ambil json
    private void Process_Data() {
        String url = link.getCharSequence("url").toString();
        Toast.makeText (this, url, Toast.LENGTH_SHORT). show();
        //String request adalah milik dari Volley library bertujuan untuk meyimpan perintah request
        //jadi String ini adalah string yang berisi syntax
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url, response -> {

            try {
                JSONArray jsonResponse = new JSONArray(response);
                //for (int i = 0; i < jsonResponse.length(); i++) {
                JSONObject obj = jsonResponse.getJSONObject(0);


                String id = obj.getString("id");
                String Nama_Barang = obj.getString("nama_barang");
                String Jumlah_Barang =obj.getString("jumlah_barang");
                String Harga_Barang =obj.getString("harga_barang");
                String Total_Barang =obj.getString("Total");

               /* Toast.makeText (this, obj.getString("nama_barang"), Toast.LENGTH_SHORT). show();
                Toast.makeText (this, obj.getString("jumlah_barang"), Toast.LENGTH_SHORT). show();
                Toast.makeText (this, obj.getString("Total"), Toast.LENGTH_SHORT). show();
*/
                nama.setText("Nama Barang : "+ Nama_Barang);
                jumlah.setText("Jumlah Barang: "+ Jumlah_Barang );
                harga.setText("Harga Barang: "+ Harga_Barang );
                Total.setText("Total: "+ Total_Barang);




                /*             Toast.makeText (this, nama, Toast.LENGTH_SHORT). show();
                 */
                //}
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }, error -> Log.d("DEBUG", "error: " + error)) {
            //Jika gagal maka coba pindah ke dalam mode MAP / pemetaan pdengan string parameter
            protected Map<String, String> getParams() {
                String id = "001";
                Map<String, String> map = new HashMap<>();
                //masukkan nilai ke dalam varibel id
                map.put("id", id);
                return null;
            }
        };

        //String jsonObj perintah dikirim
        NetworkRequest.getInstance(getApplicationContext()).addToRequestQueue(jsonObjReq);
    }

    public void onClickBayar(View view) {
        Intent intent4 = new Intent(layout4.this, selesai.class);
        this.finish();
        startActivity(intent4);
    }
}
