package com.example.androidstudio44;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ItemAdapter extends BaseAdapter {

    private LayoutInflater mInflater;

    Map<String, Double> map;
    List<String> names;
    List<Double> prices;

    public ItemAdapter(Context c, Map<String, Double> m) {

        map = m;
        names = new ArrayList<String>(map.keySet());
        prices = new ArrayList<Double>(map.values());

        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

    }

    @Override
    public int getCount() {
        return map.size();
    }

    @Override
    public Object getItem(int position) {
        return names.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View v = mInflater.inflate(R.layout.item_layout, null);
        TextView nTextView = (TextView) v.findViewById(R.id.nameTextView);
        TextView pTextView = (TextView) v.findViewById(R.id.priceTextView);

        nTextView.setText(names.get(position));
        pTextView.setText("$" + prices.get(position).toString());

        return v;
    }
}
