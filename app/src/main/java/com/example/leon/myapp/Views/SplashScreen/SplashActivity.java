package com.example.leon.myapp.Views.SplashScreen;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Login.LoginActivityView;
import com.example.leon.myapp.Views.Main.MainActivity;
import com.example.leon.myapp.Views.Main.Practice.NavigationDrawer.CustomNavigationDrawerActivity;

public class SplashActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        //Intent intent = new Intent(this, LoginActivityView.class);
        Intent intent = new Intent(this, MainActivity.class);
        //Intent intent = new Intent(this, CustomNavigationDrawerActivity.class);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
    }

    @Override
    protected void onRestart() {
        super.onRestart();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
