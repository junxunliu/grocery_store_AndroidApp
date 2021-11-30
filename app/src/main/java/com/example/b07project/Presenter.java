package com.example.b07project;

import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class Presenter {

    private Model model;
    private LogInActivity view;

    public Presenter(Model model, LogInActivity view) {
        this.model = model;
        this.view = view;
    }

    public void login(String email, String password) {

        model.auth(email, password, (User user) -> {

            if (user == null) {
                Toast.makeText(view, "failed to login", Toast.LENGTH_LONG).show();
                return;
            }
            if (user.getUserType().equals("Customer"))
                view.redirectToCustomer(user);
            else
                view.redirectToStoreOwner(user);
        });
    }
}
