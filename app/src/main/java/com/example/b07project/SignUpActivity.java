package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView banner;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword,
    editTextStoreName, editTextStoreAddress;
    private CheckBox checkBoxStoreOwner;
    private Button buttonSignUp;
    private ProgressBar progressBar;

    private FirebaseAuth mAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        banner = (TextView) findViewById(R.id.banner);
        banner.setOnClickListener(this);

        editTextEmail = (EditText) findViewById(R.id.email);
        editTextFirstName = (EditText) findViewById(R.id.firstName);
        editTextLastName = (EditText) findViewById(R.id.lastName);
        editTextPassword = (EditText) findViewById(R.id.password);
        editTextStoreName = (EditText) findViewById(R.id.storeName);
        editTextStoreAddress = (EditText) findViewById(R.id.storeAddress);
        editTextStoreName.setVisibility(View.GONE);
        editTextStoreAddress.setVisibility(View.GONE);

        buttonSignUp = (Button) findViewById(R.id.buttonSignup);
        buttonSignUp.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        checkBoxStoreOwner = (CheckBox) findViewById(R.id.checkboxStoreOwner);

        checkBoxStoreOwner.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                checkBoxChecked();
            }
        });

        mAuth = FirebaseAuth.getInstance();

    }

    private void checkBoxChecked() {
        if (checkBoxStoreOwner.isChecked()) {
            editTextStoreName.setVisibility(View.VISIBLE);
            editTextStoreAddress.setVisibility(View.VISIBLE);
        } else {
            editTextStoreName.setVisibility(View.GONE);
            editTextStoreAddress.setVisibility(View.GONE);
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.banner:
                startActivity(new Intent(this,LogInActivity.class));
                break;
            case R.id.buttonSignup:
                signUp();
                break;
        }
    }

    private void signUp() {
        String firstName = editTextFirstName.getText().toString().trim();
        String lastName = editTextLastName.getText().toString().trim();
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String storeName = editTextStoreName.getText().toString().trim();
        String storeAddress = editTextStoreAddress.getText().toString().trim();

        // validate
        if (firstName.isEmpty()) {
            editTextFirstName.setError("First name is required!");
            editTextFirstName.requestFocus();
            return;
        }

        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
            return;
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Invalid email!");
            editTextEmail.requestFocus();
            return;
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
            return;
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 characters");
            editTextPassword.requestFocus();
            return;
        }

        progressBar.setVisibility(View.VISIBLE);

        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (checkBoxStoreOwner.isChecked()) {
                                if (storeName.isEmpty()) {
                                    editTextStoreName.setError("Store name is required!");
                                    editTextStoreName.requestFocus();
                                    return;
                                }

                                if (storeAddress.isEmpty()) {
                                    editTextStoreAddress.setError("Store address is required!");
                                    editTextStoreAddress.requestFocus();
                                    return;
                                }
                                User user = new StoreOwner(email, firstName, lastName, storeName, storeAddress);
                                user.setUserType("StoreOwner");
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child("Store Owners").child(mAuth.getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, R.string.storeOwner_register_success, Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirect to login or dashboard
                                            startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
                                        } else {
                                            Toast.makeText(SignUpActivity.this, R.string.storeOwner_register_failed, Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            } else {
                                User user = new User(email, firstName, lastName);
                                user.setUserType("Customer");
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child("Customers").child(mAuth.getCurrentUser().getUid()) // get current sign up user id
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, R.string.customer_register_success, Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirect to login or dashboard
                                            startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                                        } else {
                                            Toast.makeText(SignUpActivity.this, R.string.customer_register_failed, Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}