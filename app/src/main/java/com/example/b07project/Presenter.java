package com.example.b07project;

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
                view.failedToLogIn();
                return;
            }
            if (user.getUserType().equals("Customer"))
                view.redirectToCustomer(user);
            else
                view.redirectToStoreOwner(user);
        });
    }
}
