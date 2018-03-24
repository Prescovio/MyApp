package com.example.leon.myapp;

import android.app.Application;

/**
 * Created by Leon on 24.03.2018.
 */

public class App extends Application {
    private static App mApp;
    DataComponent mDataComponent;

    public static App getApp() {
        return mApp;
    }

    @Override
    public void onCreate() {
        super.onCreate();

        mApp = this;

        initDataComponent();

        mDataComponent.inject(this);
    }

    private void initDataComponent() {
        mDataComponent = DaggerDataComponent.builder().dataModule(new DataModule(this)).build();
    }

    public DataComponent getDataComponent() {
        return mDataComponent;
    }
}
