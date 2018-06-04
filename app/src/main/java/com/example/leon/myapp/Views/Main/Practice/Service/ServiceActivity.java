package com.example.leon.myapp.Views.Main.Practice.Service;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Build;
import android.os.IBinder;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leon.myapp.*;
import com.example.leon.myapp.Platform.Services.ServiceActivity.MyBoundService;
import com.example.leon.myapp.Platform.Services.ServiceActivity.MyIntentService;
import com.example.leon.myapp.Platform.Services.ServiceActivity.MyService;

public class ServiceActivity extends AppCompatActivity {
    private Intent mServiceIntent;
    private Intent mIntentServiceIntent;
    private Intent mBoundServiceIntent;

    private boolean mBound;
    private MyBoundService mBoundService;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);

        mServiceIntent = new Intent(this, MyService.class);
        mIntentServiceIntent = new Intent(this, MyIntentService.class);
        mBoundServiceIntent = new Intent(this, MyBoundService.class);

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
     * starts MyServices
     * @param view
     */
    public void onStartServiceClick(View view) {
        startService(mServiceIntent);
        startService(mIntentServiceIntent);

        //TODO foreground service
        if (Build.VERSION.SDK_INT >= 26)
        {
            startForegroundService(new Intent(this, MyIntentService.class));
        }

        bindService(mBoundServiceIntent, mConnection, Context.BIND_AUTO_CREATE);

        //TODO Job Scheduler
    }

    /**
     * stops MyServices
     * @param view
     */
     public void onStopServiceClick(View view) {
        stopService(mServiceIntent);
        stopService(mIntentServiceIntent);
        unbindService(mConnection);
    }

    /** Defines callbacks for service binding, passed to bindService() */
    private ServiceConnection mConnection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName className, IBinder service) {
            // We've bound to LocalService, cast the IBinder and get LocalService instance
            MyBoundService.LocalBinder binder = (MyBoundService.LocalBinder) service;
            mBoundService = binder.getService();
            mBound = true;
        }

        @Override
        public void onServiceDisconnected(ComponentName arg0) {
            mBound = false;
        }
    };
}
