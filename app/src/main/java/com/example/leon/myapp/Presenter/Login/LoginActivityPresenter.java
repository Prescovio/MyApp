package com.example.leon.myapp.Presenter.Login;

import android.content.Context;

import com.example.leon.myapp.App;
import com.example.leon.myapp.Models.Login.LoginActivityModel;
import com.example.leon.myapp.Presenter.UserValidationPresenterBase;

import javax.inject.Inject;

public class LoginActivityPresenter extends UserValidationPresenterBase {
    @Inject
    protected LoginActivityModel mLoginActivityModel;

    public LoginActivityPresenter(Context context) {
        App.getApp().getDataComponent().inject(this);
    }

    public boolean login(String email, String password) {
        return this.mLoginActivityModel.login(email, password);
    }
}
