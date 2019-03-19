package com.pusing.pusing.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.pusing.pusing.DataUserModel.DataUserModel;

import java.util.List;

import com.pusing.pusing.R;

/**
 * Created by Abraham Lundy on 3/22/2018
 */

public class CustomAdapter extends ArrayAdapter<DataUserModel> {

    public CustomAdapter(Context context, List<DataUserModel> items) {
        super(context, 0, items);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        // View mengambil convertView
        View v = convertView;

        if (v == null) {
            // Deklarasi inflater agar hasil dari view bentukan bisa dipompa ke dalam tampilan
            LayoutInflater vi;
            // Deklarasi agar dipompa ke dalam context tampilan yang diambil
            vi = LayoutInflater.from(getContext());
            //Tampilan adalah pompaan layout dari item list_item
            v = vi.inflate(R.layout.mr_chooser_list_item, null);
        }

        DataUserModel p = getItem(position);

        if (p != null) {
            TextView tt1=null ;
            TextView tt2 =null;
           // TextView tt3 = v.findViewById(R.id.kartu);
           //ini kalau ngambilnya gambar
            // ImageView view = v.findViewById(R.id.img);

/*
            if(view != null){
//                view.setImageResource(p.getImg());
  //              Picasso.get().load(p.getImg()).into(view);
            }
*/
            if (tt1 != null) {
                tt1.setText(p.getSaldo());
            }

            if (tt2 != null) {
                tt2.setText(p.getNama());
            }
        }

        return v;
    }

}