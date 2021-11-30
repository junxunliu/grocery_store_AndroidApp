package com.example.b07project;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class StoreOrderAdapter extends ArrayAdapter<StoreOrder> {

    private ArrayList<StoreOrder> stOrderList;
    private int resourceID;
    //private ArrayList<StoreOrder> stOrderList;

    public StoreOrderAdapter(Context context, int textViewResourceId, ArrayList<StoreOrder> stOrderList){
        super(context, textViewResourceId, stOrderList);
        resourceID = textViewResourceId;
        this.stOrderList = new ArrayList<StoreOrder>();
        this.stOrderList.addAll(stOrderList);
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

            /*holder.check.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    CheckBox cb = (CheckBox) v;
                    StoreOrder order = (StoreOrder) cb.getTag();
                    order.setCheck(cb.isChecked());
                }
            });*/
        }
        else {
            view = convertView;
            holder = (ViewHolder) convertView.getTag();
        }

        StoreOrder order = stOrderList.get(position);
        holder.check.setChecked(order.isChecked());
        holder.stOrder.setText(order.toString());
        return view;
    }
    /*private void checkButtonClick() {
        Button complete = (Button) findViewById(R.id.button2);
        myButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {

                StringBuffer responseText = new StringBuffer();
                responseText.append("The following were selected...\n");

                ArrayList<Country> countryList = dataAdapter.countryList;
                for(int i=0;i<countryList.size();i++){
                    Country country = countryList.get(i);
                    if(country.isSelected()){
                        responseText.append("\n" + country.getName());
                    }
                }

                Toast.makeText(getApplicationContext(),
                        responseText, Toast.LENGTH_LONG).show();

            }
        });

    }*/
}