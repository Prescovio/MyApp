package com.example.leon.myapp.Views.Registration;

import com.example.leon.myapp.*;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class RegistrationActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);

        if (savedInstanceState == null)
        {
            FragmentManager fragmentManager = getSupportFragmentManager();
            FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();

            RegistrationFragment registrationFragmentview = RegistrationFragment.newInstance();
            fragmentTransaction.add(R.id.registration_activity_fragment_container, registrationFragmentview);
            fragmentTransaction.commit();
        }
    }
}

