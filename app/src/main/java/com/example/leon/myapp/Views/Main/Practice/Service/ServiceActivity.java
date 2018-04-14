package com.example.leon.myapp.Views.Main.Practice.Service;

import android.content.Intent;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leon.myapp.*;

public class ServiceActivity extends AppCompatActivity {
    Intent serviceIntent;
    Intent intentServiceIntent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        serviceIntent = new Intent(this, MyService.class);
        intentServiceIntent = new Intent(this, MyIntentService.class);

        Button startService = (Button)findViewById(R.id.btnStartService);
        startService.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onStartServiceClick(view);
            }
        });

        Button stopService = (Button)findViewById(R.id.btnStopService);
        stopService.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onStopServiceClick(view);
            }
        });
    }

    /**
     * starts MyService
     * @param view
     */
    public void onStartServiceClick(View view) {
        startService(serviceIntent);
        startService(intentServiceIntent);

        //TODO foreground service
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O)
        {
            startForegroundService(new Intent(this, MyIntentService.class));
        }

        //TODO bound service

        //TODO Job Scheduler
    }

    /**
     * stops MyService
     * @param view
     */
     public void onStopServiceClick(View view) {
        stopService(serviceIntent);
        stopService(intentServiceIntent);
    }
}
