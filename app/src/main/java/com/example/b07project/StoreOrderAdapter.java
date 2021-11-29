package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreOrderAdapter extends ArrayAdapter<StoreOrder> {

    private int resourceID;
    //private ArrayList<StoreOrder> stOrderList;

    public StoreOrderAdapter(Context context, int textViewResourceId, ArrayList<StoreOrder> stOrderList){
        super(context, textViewResourceId, stOrderList);
        resourceID = textViewResourceId;
    }

    private class ViewHolder {
        TextView stOrder;
        CheckBox check;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        StoreOrder st_order = getItem(position);
        View view;
        ViewHolder holder;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceID, parent,false);

            holder = new ViewHolder();
            holder.stOrder = (TextView) convertView.findViewById(R.id.textView3);
            holder.check = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);
        }
        else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }
        return view;
    }
}