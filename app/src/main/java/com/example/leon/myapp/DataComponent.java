package com.example.leon.myapp;

import com.example.leon.myapp.Presenter.Login.LoginActivityPresenter;
import com.example.leon.myapp.Presenter.Registration.RegistrationActivityPresenter;
import com.example.leon.myapp.Views.Login.LoginActivityView;
import com.example.leon.myapp.Views.Registration.RegistrationActivityView;

import dagger.Component;

/**
 * Created by Leon on 24.03.2018.
 */

@Component (modules = {DataModule.class})
public interface DataComponent {
    void inject(App app);

    void inject(LoginActivityView loginActivityView);
    void inject(LoginActivityPresenter loginActivityPresenter);

    void inject(RegistrationActivityView registrationActivityView);
    void inject(RegistrationActivityPresenter registrationActivityPresenter);
}
