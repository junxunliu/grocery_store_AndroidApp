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

public class StoreOrderAdapter extends ArrayAdapter<Order> {

    private ArrayList<Order> stOrderList;
    //TextView tv_order;

    public StoreOrderAdapter(Context context, int textViewResourceId, ArrayList<Order> stOrderList){
        super(context, textViewResourceId, stOrderList);
        this.stOrderList = new ArrayList<Order>();
        this.stOrderList.addAll(stOrderList);
    }

    private class ViewHolder {
        TextView stOrder;
        CheckBox check;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        ViewHolder holder = null;

        if (convertView == null) {
            LayoutInflater vi = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = vi.inflate(R.layout.layout_store_order, null);

            //tv_order = convertView.findViewById(R.id.tv_order_display);
            holder = new ViewHolder();
            holder.stOrder = (TextView) convertView.findViewById(R.id.tv_order_display);
            holder.check = (CheckBox) convertView.findViewById(R.id.checkBox);
            convertView.setTag(holder);

            ViewHolder finalHolder = holder;
            holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    if(cb.isChecked()){
                        Order order = stOrderList.get(position);
                        order.setStatus(true);
                        Toast.makeText(getContext().getApplicationContext(),"Order from " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName() + " is " + order.displayStatus(),Toast.LENGTH_LONG).show();
                        finalHolder.stOrder.setText(order.toString());
                    }
                    else if(!cb.isChecked()){
                        Order order = stOrderList.get(position);
                        order.setStatus(false);
                        Toast.makeText(getContext().getApplicationContext(),"Order from " + order.getCustomer().getFirstName() + " " + order.getCustomer().getLastName() + " is " + order.displayStatus(),Toast.LENGTH_LONG).show();
                        finalHolder.stOrder.setText(order.toString());
                    }
                }
            });
        }
        else {
            holder = (ViewHolder) convertView.getTag();
        }

        Order order = stOrderList.get(position);
        holder.check.setChecked(order.getStatus());
        holder.stOrder.setText(order.toString());

        return convertView;
    }
}