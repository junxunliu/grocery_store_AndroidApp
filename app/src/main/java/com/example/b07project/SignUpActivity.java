package com.example.b07project;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.FirebaseDatabase;

public class SignUpActivity extends AppCompatActivity implements View.OnClickListener{

    private TextView banner;
    private EditText editTextFirstName, editTextLastName, editTextEmail, editTextPassword;
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

        buttonSignUp = (Button) findViewById(R.id.buttonSignup);
        buttonSignUp.setOnClickListener(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        progressBar.setVisibility(View.GONE);

        checkBoxStoreOwner = (CheckBox) findViewById(R.id.checkboxStoreOwner);

        mAuth = FirebaseAuth.getInstance();

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

        // validate
        if (firstName.isEmpty()) {
            editTextFirstName.setError("First name is required!");
            editTextFirstName.requestFocus();
        }
        if (email.isEmpty()) {
            editTextEmail.setError("Email is required!");
            editTextEmail.requestFocus();
        }

        if (!Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            editTextEmail.setError("Invalid email!");
            editTextEmail.requestFocus();
        }

        if (password.isEmpty()) {
            editTextPassword.setError("Password is required!");
            editTextPassword.requestFocus();
        }

        if (password.length() < 6) {
            editTextPassword.setError("Password should be at least 6 characters");
            editTextPassword.requestFocus();
        }


        progressBar.setVisibility(View.VISIBLE);
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            if (checkBoxStoreOwner.isChecked()) {
                                User user = new User(email, firstName, lastName);
                                user.setUserType("StoreOwner");
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(mAuth.getCurrentUser().getUid())
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "Store owner registered successfully!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirect to login or dashboard
                                            startActivity(new Intent(SignUpActivity.this,LogInActivity.class));
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Failed to create a store owner", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            } else {
                                User user = new User(email, firstName, lastName);
                                user.setUserType("Customer");
                                FirebaseDatabase.getInstance().getReference("Users")
                                        .child(mAuth.getCurrentUser().getUid()) // get current sign up user id
                                        .setValue(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                                    @Override
                                    public void onComplete(@NonNull Task<Void> task) {
                                        if (task.isSuccessful()) {
                                            Toast.makeText(SignUpActivity.this, "Customer registered successfully!", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);

                                            // redirect to login or dashboard
                                            startActivity(new Intent(SignUpActivity.this, LogInActivity.class));
                                        } else {
                                            Toast.makeText(SignUpActivity.this, "Failed to create a customer", Toast.LENGTH_LONG).show();
                                            progressBar.setVisibility(View.GONE);
                                        }
                                    }
                                });
                            }
                        } else {
                            Toast.makeText(SignUpActivity.this, "Failed to sign up", Toast.LENGTH_LONG).show();
                            progressBar.setVisibility(View.GONE);
                        }
                    }
                });
    }

}