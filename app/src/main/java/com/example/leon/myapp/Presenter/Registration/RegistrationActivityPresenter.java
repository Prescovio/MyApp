package com.example.leon.myapp.Presenter.Registration;

import android.content.Context;

import com.example.leon.myapp.App;
import com.example.leon.myapp.Models.Registration.RegistrationActivityModel;
import com.example.leon.myapp.Presenter.UserValidationPresenterBase;

import javax.inject.Inject;

public class RegistrationActivityPresenter extends UserValidationPresenterBase {
    @Inject
    protected RegistrationActivityModel mRegistrationActivityModel;

    public RegistrationActivityPresenter(Context context) {
        App.getApp().getDataComponent().inject(this);
    }
    public boolean userAvailable(String email) {
        return this.mRegistrationActivityModel.userAvailable(email);
    }

    public boolean register(String firstName, String secondName, int age, String email, String password) {
        return this.mRegistrationActivityModel.register(firstName, secondName, age, email, password);
    }
}
