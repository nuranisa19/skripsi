package com.pusing.pusing;

import android.content.Intent;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.toolbox.StringRequest;
import com.pusing.pusing.DataUserModel.DataUserModel;
import com.pusing.pusing.NetworkRequest.NetworkRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class layout2 extends AppCompatActivity {

    private List<DataUserModel> tagList;
    //penghubung antara data dengan listview
    //ArrayAdapter adapter;
    JSONArray jsonResponse;
    JSONObject obj;
    TextView Nama ;
    TextView Saldo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_layout2);
        //Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        //setSupportActionBar(toolbar);

        Nama = (TextView)findViewById(R.id.TvNama);
        Saldo = (TextView) findViewById(R.id.TvSaldo);


        Button transaksi = (Button) findViewById(R.id.transaksi);
        transaksi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent3 = new Intent(layout2.this, layout3.class);
                startActivity(intent3);
            }
        });

        Process_Data();
    }


    //ini fungsi ambil json
    private void Process_Data() {
        String url = "https://marchmarket.000webhostapp.com/get_data.php?id=1";
        //String request adalah milik dari Volley library bertujuan untuk meyimpan perintah request
        //jadi String ini adalah string yang berisi syntax
        StringRequest jsonObjReq = new StringRequest(Request.Method.POST, url, response -> {

            try {
                jsonResponse = new JSONArray(response);
                //for (int i = 0; i < jsonResponse.length(); i++) {
                    obj = jsonResponse.getJSONObject(0);


                    String id = obj.getString("id");
                    String nama = obj.getString("nama");
                    String saldo=obj.getString("saldo");

              /*  Toast.makeText (this, obj.getString("id"), Toast.LENGTH_SHORT). show();
                Toast.makeText (this, obj.getString("nama"), Toast.LENGTH_SHORT). show();
                Toast.makeText (this, obj.getString("saldo"), Toast.LENGTH_SHORT). show();*/

                Nama.setText("Selamat Datang "+ nama );
                Saldo.setText("Saldo Sekarang "+ saldo );

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
