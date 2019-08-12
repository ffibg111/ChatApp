package com.example.androidstudio34;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ItemAdapter extends BaseAdapter {

    LayoutInflater mInflater;

    String[] items;
    String[] prices;
    String[] descriptions;

    public ItemAdapter(Context c, String[] i, String[] p, String[] d) {
        items = i;
        prices = p;
        descriptions = d;
        mInflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getCount() {
        return items.length;
    }

    @Override
    public Object getItem(int i) {
        return items[i];
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {

        View v = mInflater.inflate(R.layout.my_listview_detail, null);
        TextView NameTextView = (TextView) v.findViewById(R.id.itemNameTextView);
        TextView DescTextView = (TextView) v.findViewById(R.id.itemDescTextView);
        TextView PriceTextView = (TextView) v.findViewById(R.id.itemPriceTextView);

        String name = items[i];
        String desc = descriptions[i];
        String cost = prices[i];

        NameTextView.setText(name);
        DescTextView.setText(desc);
        PriceTextView.setText(cost);

        return v;
    }
}
