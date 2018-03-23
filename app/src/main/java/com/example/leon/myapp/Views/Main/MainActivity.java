package com.example.leon.myapp.Views.Main;
import com.example.leon.myapp.*;
import com.example.leon.myapp.Presenter.Main.MainActivityPresenter;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {
    private MainActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        presenter = new MainActivityPresenter();
    }
}
