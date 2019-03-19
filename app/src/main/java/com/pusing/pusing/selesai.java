package com.pusing.pusing;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.pusing.pusing.NetworkRequest.NetworkRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Map;

public class selesai extends AppCompatActivity {

    String nomor = "082218637521" ;
    String pesan = "Pembayaran Anda sukses";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_selesai);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        /*FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });*/
    }
    private void Process_Data() {
        String url = "https://rest.nexmo.com/sms/json?api_key=b904ea72c&api_secret=qrtdVCl9frtOfhMq&to="+nomor+"&from=marchmarket&text="+pesan;
        Toast.makeText (this, url, Toast.LENGTH_SHORT). show();
        //String request adalah milik dari Volley library bertujuan untuk meyimpan perintah request
        //jadi String ini adalah string yang berisi syntax
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url, response -> {

            try {
                JSONArray jsonResponse = new JSONArray(response);
                //for (int i = 0; i < jsonResponse.length(); i++) {
                JSONObject obj = jsonResponse.getJSONObject(0);

                Toast.makeText (this, obj.getString("jsonResponse"), Toast.LENGTH_SHORT). show();
               /* Toast.makeText (this, obj.getString("jumlah_barang"), Toast.LENGTH_SHORT). show();
                Toast.makeText (this, obj.getString("Total"), Toast.LENGTH_SHORT). show();

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

}
