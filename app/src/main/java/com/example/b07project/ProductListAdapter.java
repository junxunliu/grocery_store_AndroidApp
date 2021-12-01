package com.example.b07project;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;


import com.google.firebase.database.ValueEventListener;

import java.util.List;

public class ProductListAdapter extends ArrayAdapter<Product> {

    private Context context;
    private int resource;

    public ProductListAdapter(Context context, int resource, List<Product> objects) {
        super(context, resource, objects);
        this.context = context;
        this.resource = resource;
    }

    private class ViewHolder {
        TextView tvProductName;
        TextView tvBrandName;
        TextView tvPrice;
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        String Name = getItem(position).name;
        String Brand = getItem(position).brand;
        String Price = getItem(position).price;


        ViewHolder holder;

        if (convertView == null) {

            LayoutInflater inflater = LayoutInflater.from(context);
            convertView = inflater.inflate(resource, parent, false);
            holder = new ViewHolder();

            holder.tvProductName = (TextView) convertView.findViewById(R.id.tvProductName);
            holder.tvBrandName = (TextView) convertView.findViewById(R.id.tvBrandName);
            holder.tvPrice = (TextView) convertView.findViewById(R.id.tvPrice);

            convertView.setTag(holder);

        } else {
            holder = (ViewHolder) convertView.getTag();
        }

        holder.tvProductName.setText(Name);
        holder.tvBrandName.setText(Brand);
        holder.tvPrice.setText(Price);

        return convertView;
    }


}
