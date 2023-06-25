package com.example.sushigo;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class PelangganAdapter extends BaseAdapter {
    Activity activity;
    List<Data> items;
    private LayoutInflater inflater;

    public PelangganAdapter(Activity activity, List<Data> items) {
        this.activity = activity;
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (inflater == null)
            inflater = (LayoutInflater) activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) convertView = inflater.inflate(R.layout.list, null);

        TextView nama = (TextView) convertView.findViewById(R.id.nama);
        TextView telepon = (TextView) convertView.findViewById(R.id.telepon);
        TextView alamat = (TextView) convertView.findViewById(R.id.alamat);

        Data data = items.get(position);

        nama.setText(data.getNama());
        telepon.setText(data.getTelepon());
        alamat.setText(data.getAlamat());

        return convertView;
    }
}


