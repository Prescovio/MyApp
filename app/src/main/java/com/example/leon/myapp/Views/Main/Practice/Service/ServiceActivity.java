package com.example.leon.myapp.Views.Main.Practice.Service;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leon.myapp.*;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

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
        startService(new Intent(this, MyService.class));
        startService(new Intent(this, MyIntentService.class));
    }

    /**
     * stops MyService
     * @param view
     */
     public void onStopServiceClick(View view) {
        stopService(new Intent(this, MyService.class));
        stopService(new Intent(this, MyIntentService.class));
    }
}
