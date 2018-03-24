package com.example.leon.myapp.Presenter.Registration;

import android.content.Context;

import com.example.leon.myapp.Models.Registration.RegistrationActivityModel;
import com.example.leon.myapp.Presenter.UserValidationPresenterBase;

public class RegistrationActivityPresenter extends UserValidationPresenterBase {
    private RegistrationActivityModel mRegistrationActivityModel;

    public RegistrationActivityPresenter(Context context) {
        this.mRegistrationActivityModel = new RegistrationActivityModel(context);
    }
    public boolean userAvailable(String email) {
        return this.mRegistrationActivityModel.userAvailable(email);
    }

    public boolean register(String firstName, String secondName, int age, String email, String password) {
        return this.mRegistrationActivityModel.register(firstName, secondName, age, email, password);
    }
}
