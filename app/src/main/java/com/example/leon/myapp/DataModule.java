package com.example.leon.myapp;

import android.app.Application;
import android.app.Service;
import android.content.Context;

import com.example.leon.myapp.Presenter.Login.LoginActivityPresenter;
import com.example.leon.myapp.Views.Login.LoginActivityView;

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
    public LoginActivityView provideLoginActivityView() {
        return new LoginActivityView();
    }

    @Provides
    public LoginActivityPresenter provideLoginActivityPresenter(Context context) {
        return new LoginActivityPresenter(context);
    }
}
