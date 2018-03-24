package com.example.leon.myapp.Presenter.Login;

import android.content.Context;

import com.example.leon.myapp.Models.Login.LoginActivityModel;
import com.example.leon.myapp.Presenter.UserValidationPresenterBase;

import javax.inject.Inject;

public class LoginActivityPresenter extends UserValidationPresenterBase {
    private LoginActivityModel mLoginActivityModel;

    @Inject
    public LoginActivityPresenter(Context context) {
        this.mLoginActivityModel = new LoginActivityModel(context);
    }

    public boolean login(String email, String password) {
        return this.mLoginActivityModel.login(email, password);
    }
}
