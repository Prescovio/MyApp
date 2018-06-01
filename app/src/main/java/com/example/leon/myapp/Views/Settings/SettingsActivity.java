package com.example.leon.myapp.Views.Settings;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.leon.myapp.R;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getFragmentManager().beginTransaction().replace(android.R.id.content, new SettingsFragment()).commit();
    }

}
