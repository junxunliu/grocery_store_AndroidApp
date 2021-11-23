package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    public void sendOrder(View view){
        EditText editTextsid = (EditText) findViewById(R.id.editTextTextSid);
        EditText editTextpid = (EditText) findViewById(R.id.editTextTextPid);
        EditText editTextnum = (EditText) findViewById(R.id.editTextNumber);
        String sid = editTextsid.getText().toString();
        String pid = editTextpid.getText().toString();
        String num = editTextnum.getText().toString();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference();
        Order order = new Order(sid,pid,num);
        ref.child("order").child("1").setValue(order);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.i("b07info","Resume start");
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference("order");
        ValueEventListener listener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.i("b07info", dataSnapshot.toString());
                for(DataSnapshot child:dataSnapshot.getChildren()) {
                    Log.i("b07info", child.toString());
                    Order order = child.getValue(Order.class);
                    Log.i("b07info", order.toString());
                    TextView tv1 = (TextView) findViewById(R.id.textView1);
                    tv1.setText(order.toString());
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Log.w("warning", "loadPost:onCancelled", databaseError.toException());
            }
        };
        ref.addValueEventListener(listener);


    }
}