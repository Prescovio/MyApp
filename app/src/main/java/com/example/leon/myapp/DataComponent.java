package com.example.leon.myapp;

import com.example.leon.myapp.Presenter.Login.LoginActivityPresenter;
import com.example.leon.myapp.Presenter.Registration.RegistrationActivityPresenter;
import com.example.leon.myapp.Views.Login.LoginActivity;
import com.example.leon.myapp.Views.Login.LoginFragment;
import com.example.leon.myapp.Views.Registration.RegistrationActivity;
import com.example.leon.myapp.Views.Registration.RegistrationFragment;

import dagger.Component;

/**
 * Created by Leon on 24.03.2018.
 */

@Component (modules = {DataModule.class})
public interface DataComponent {
    void inject(App app);

    void inject(LoginActivity loginActivity);
    void inject(LoginActivityPresenter loginActivityPresenter);

    void inject(LoginFragment loginFragment);
    void inject(RegistrationFragment registrationFragment);

    void inject(RegistrationActivity registrationActivity);
    void inject(RegistrationActivityPresenter registrationActivityPresenter);
}
