package com.example.leon.myapp;

import android.app.Application;
import android.content.Context;

import com.example.leon.myapp.Models.Login.LoginActivityModel;
import com.example.leon.myapp.Models.Registration.RegistrationActivityModel;
import com.example.leon.myapp.Presenter.Login.LoginActivityPresenter;
import com.example.leon.myapp.Presenter.Registration.RegistrationActivityPresenter;

import dagger.Module;
import dagger.Provides;

/**
 * Created by Leon on 24.03.2018.
 */

@Module
public class DataModule {
    Application mApplication;

    public DataModule(Application application) {
        this.mApplication = application;
    }

    @Provides
    public Context provideContext() {
        return this.mApplication.getBaseContext();
    }

    @Provides
    public LoginActivityPresenter provideLoginActivityPresenter(Context context) {
        return new LoginActivityPresenter(context);
    }

    @Provides
    public LoginActivityModel provideLoginActivityModel(Context context) {
        return new LoginActivityModel(context);
    }

    @Provides
    public RegistrationActivityPresenter provideRegistrationActivityPresenter(Context context) {
        return new RegistrationActivityPresenter(context);
    }

    @Provides
    public RegistrationActivityModel provideRegistrationActivityModel(Context context) {
        return new RegistrationActivityModel(context);
    }

}
