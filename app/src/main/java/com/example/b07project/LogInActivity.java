package com.example.b07project;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class LogInActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String REMEMBER = "remember";
    private static final String EMAIL = "email";
    private static final String PASSWORD = "password";
    private SharedPreferences preferences;
    private SharedPreferences.Editor editor;

    private EditText editTextEmail, editTextPassword;
    private Button buttonLogin;
    private CheckBox checkBoxRemember;
    private TextView textViewSignup;

//    private FirebaseAuth mAuth;

    private Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        editTextEmail = (EditText) findViewById(R.id.editTextEmail);
        editTextPassword =  (EditText) findViewById(R.id.editTextPassword);
        buttonLogin = (Button) findViewById(R.id.buttonLogin);
        buttonLogin.setOnClickListener(this);
        checkBoxRemember = (CheckBox) findViewById(R.id.checkBoxRemember);
        textViewSignup = (TextView) findViewById(R.id.signup);
        textViewSignup.setOnClickListener(this);

        preferences = getSharedPreferences("test", MODE_PRIVATE);
        editor = preferences.edit();

//        mAuth = FirebaseAuth.getInstance();

        presenter = new Presenter(new Model(), this);

        checkSharePreferences();
    }

    private void checkSharePreferences() {
        Boolean remember = preferences.getBoolean(REMEMBER, false);
        String email = preferences.getString(EMAIL, "");
        String password = preferences.getString(PASSWORD, "");

        editTextEmail.setText(email);
        editTextPassword.setText(password);
        checkBoxRemember.setChecked(remember);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.buttonLogin:
                logIn();
                break;
            case R.id.signup:
                startActivity(new Intent(this, SignUpActivity.class));
                break;
        }
    }

    private void logIn() {
        String email = editTextEmail.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();

        editor.putBoolean(REMEMBER, checkBoxRemember.isChecked());
        editor.putString(EMAIL, checkBoxRemember.isChecked() ? email : "");
        editor.putString(PASSWORD, checkBoxRemember.isChecked() ? password : "");
        editor.apply();

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

        presenter.login(email, password);

//        mAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
//            @Override
//            public void onComplete(@NonNull Task<AuthResult> task) {
//                // check if it's store owner or customer
//                // here is customer
//                if (task.isSuccessful()) {
//                    startActivity(new Intent(LogInActivity.this, CustomerProductActivity.class));
//                } else {
//                    Toast.makeText(LogInActivity.this, "Fail to login!", Toast.LENGTH_LONG).show();
//                }
//            }
//        });
    }

    public void redirectToCustomer(User user) {
        Intent intent = new Intent(this, CustomerProductActivity.class);
        intent.putExtra("Customer", user);
        startActivity(intent);
    }

    public void redirectToStoreOwner(User user) {
        Intent intent = new Intent(this, StoreOwnerMainPageActivity.class);
        intent.putExtra("Store Owner", user);
        startActivity(intent);
    }
}