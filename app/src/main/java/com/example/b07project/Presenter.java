package com.example.b07project;

import android.util.Log;
import android.view.View;

public class Presenter {

    private Model model;
    private LogInActivity view;

    public Presenter(Model model, LogInActivity view) {
        this.model = model;
        this.view = view;
    }

    public void login(String email, String password) {

        model.getUser(email, password, (User user) -> {
            if (user == null) return;
            if (user instanceof Customer)
                view.redirectToCustomer(user);
            else
                view.redirectToStoreOwner(user);
        });
    }
}
