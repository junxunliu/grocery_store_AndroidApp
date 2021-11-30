package com.example.b07project;

import android.os.Build;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.function.Consumer;

public class Model {
    private DatabaseReference usersRef;
    private static Model instance;
    public Model() {

    }

    public static void getUser(String email, String password, Consumer<User> callback) {
        FirebaseAuth.getInstance().signInWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // get current user id
                            String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
                            FirebaseDatabase.getInstance().getReference("UserTypes").child(userId).child("userType")
                                    .addListenerForSingleValueEvent(new ValueEventListener() {
                                        @RequiresApi(api = Build.VERSION_CODES.N)
                                        @Override
                                        public void onDataChange(@NonNull DataSnapshot snapshot) {
                                            String userType = snapshot.getValue(String.class);

                                            if (userType == null) {
                                                Log.d("Model", "Failed");
                                                callback.accept(null);
                                            }

                                            else if (userType.equals("Store Owner")) {
                                                StoreOwner storeOwner = new StoreOwner();
                                                storeOwner.setId(userId);
                                                callback.accept(storeOwner);
                                            }
                                            else if (userType.equals("Customer")) {
                                                Customer customer = new Customer();
                                                customer.setId(userId);
                                                callback.accept(customer);
                                            }
                                        }

                                        @Override
                                        public void onCancelled(@NonNull DatabaseError error) {

                                        }
                                    });
                        }
                    }
                });
    }
    public void gUser(String userID, Consumer<User> callback) {
        usersRef.child(userID).addListenerForSingleValueEvent(new ValueEventListener() {
            @RequiresApi(api = Build.VERSION_CODES.N)
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                User user = snapshot.getValue(User.class);
                callback.accept(user);
            }
            @Override
            public void onCancelled(@NonNull  DatabaseError error) {}
        });
    }
    public static Model getInstance() {
        if (instance == null)
            instance = new Model();
        return instance;
    }
}
