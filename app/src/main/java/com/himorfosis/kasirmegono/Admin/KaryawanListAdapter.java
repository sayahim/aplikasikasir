package com.himorfosis.kasirmegono.Admin;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.himorfosis.kasirmegono.R;

import java.util.List;

public class KaryawanListAdapter extends ArrayAdapter<KaryawanClassData> {

    Context context;
    List<KaryawanClassData> list;


    public KaryawanListAdapter(Context context, List<KaryawanClassData> objects) {

        super(context, R.layout.rowkaryawan, objects);
        this.context = context;
        list = objects;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @SuppressLint("StaticFieldLeak")
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        int getnomor = position + 1;

        if (convertView == null) {

            LayoutInflater layoutInflater;

            layoutInflater = LayoutInflater.from(getContext());
            convertView = layoutInflater.inflate(R.layout.rowkaryawan, null);

        }

        View v = convertView;

        final KaryawanClassData data = list.get(position);

        if (data != null) {

            TextView nohandphone = (TextView) v.findViewById(R.id.nohandphone);
            TextView nomor = (TextView) v.findViewById(R.id.nomor);
            TextView nama = (TextView) v.findViewById(R.id.nama);

            nama.setText(String.valueOf(data.getNama()));
            nomor.setText(String.valueOf(getnomor) +".");
            nohandphone.setText(data.getHandphone());

        }

        return v;

    }
}
