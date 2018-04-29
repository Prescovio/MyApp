package com.example.leon.myapp.Views.Main.Practice.Notification;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.leon.myapp.*;
import com.example.leon.myapp.Views.Login.LoginActivity;
import com.example.leon.myapp.Views.Main.MainActivity;
import com.example.leon.myapp.Views.Registration.RegistrationActivityView;

public class NotificationActivity extends AppCompatActivity {
    private static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        Button createNotification = (Button)findViewById(R.id.btnCreateNotification);
        createNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateNotificationClick(view);
            }
        });

        Button createProgressbarNotification = (Button)findViewById(R.id.btnCreateProgressbarNotification);
        createProgressbarNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateProgressbarNotificationClick(view);
            }
        });

        Button createExpandedNotification = (Button)findViewById(R.id.btnCreateExpandedNotification);
        createExpandedNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateExpandedNotificationClick(view);
            }
        });

        Button createCustomNotification = (Button)findViewById(R.id.btnCreateCustomNotification);
        createCustomNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateCustomNotificationClick(view);
            }
        });
    }

    /**
     * creates notification
     * @param view
     */
    public void onCreateNotificationClick(View view) {
        // Create an explicit intent for an Activity in your app
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, mainIntent, 0);

        Intent registrationIntent = new Intent(getApplicationContext(), RegistrationActivityView.class);
        PendingIntent registrationPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, registrationIntent, 0);

        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        PendingIntent loginPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, loginIntent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.mika)
                .setContentTitle("Content Title")
                .setContentText("Content")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                .setContentIntent(mainPendingIntent)
                .addAction(R.drawable.mika, getString(R.string.activity_main_title), mainPendingIntent)
                .addAction(R.drawable.mika, getString(R.string.activity_registration_title), registrationPendingIntent)
                .addAction(R.drawable.mika, getString(R.string.activity_login_title), loginPendingIntent)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT)
                .setCategory(NotificationCompat.CATEGORY_MESSAGE)
                .setVisibility(NotificationCompat.VISIBILITY_PRIVATE)
                //.setPublicVersion
                .setOnlyAlertOnce(true)
                .setAutoCancel(true);
        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //notification sadly doesn't disapear on action click
        //have not found a proper solution

        notificationManager.notify(count++, mBuilder.build());
    }

    /**
     * creates progressbar notification
     * @param view
     */
    public void onCreateProgressbarNotificationClick(View view) {
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.mika)
                .setOngoing(false)
                .setAutoCancel(true)
                .setColor(Color.RED)
                .setPriority(NotificationCompat.PRIORITY_DEFAULT);

        final NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        // Issue the initial notification with zero progress
        mBuilder.setProgress(100, 0, false);
        notificationManager.notify(count, mBuilder.build());

        //what needs to be done to update progress

        Thread thread = new Thread() {
            int currentCount = count++;
            public void run() {
                // a potentially  time consuming task
                try {
                    int countTime = 0;

                    while (countTime < 100)
                    {
                        if (Thread.interrupted())
                            return;
                        Thread.sleep(1000);
                        countTime += 10;
                        mBuilder.setContentText(String.valueOf(countTime))
                                .setProgress(100,countTime,false);
                        notificationManager.notify(currentCount, mBuilder.build());
                    }

                    // When done, update the notification one more time to remove the progress bar
                    mBuilder.setContentText("Download complete")
                            .setProgress(0,0,false);
                    notificationManager.notify(currentCount, mBuilder.build());

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
        thread.start();

        //thread.interrupt();
        //notificationManager.cancel(count-1);
    }

    /**
     * creates expanded notification
     * @param view
     */
    public void onCreateExpandedNotificationClick(View view) {
        Bitmap myBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.mika);
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext())
                .setSmallIcon(R.drawable.mika)
                .setContentTitle("Content Title")
                .setContentText("Content")
                .setLargeIcon(myBitmap)
                //.setStyle(new NotificationCompat.BigPictureStyle().bigPicture(myBitmap).bigLargeIcon(null))
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."));
                //.setStyle(new NotificationCompat.InboxStyle().addLine("First line message summary").addLine("Second line message summary"))

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);

        //notification sadly doesn't disapear on action click
        //have not found a proper solution

        notificationManager.notify(count++, mBuilder.build());
    }

    /**
     * creates custom notification
     * @param view
     */
    public void onCreateCustomNotificationClick(View view) {
    }

    //TODO custom notification

    //TODO group notifications
}
