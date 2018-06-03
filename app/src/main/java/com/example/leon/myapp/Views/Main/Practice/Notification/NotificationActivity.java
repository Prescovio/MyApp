package com.example.leon.myapp.Views.Main.Practice.Notification;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.Build;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.leon.myapp.*;
import com.example.leon.myapp.Views.Login.LoginActivity;
import com.example.leon.myapp.Views.Main.MainActivity;
import com.example.leon.myapp.Views.Registration.RegistrationActivity;

public class NotificationActivity extends AppCompatActivity {
    private String CHANNEL_ID = "com.example.leon.myapp.NOTIFICATION_CHANNEL";
    private static int count = 1;

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

        Button createGroupNotification = (Button)findViewById(R.id.btnCreateGroupNotification);
        createGroupNotification.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                onCreateGroupNotificationClick(view);
            }
        });

        this.createNotificationChannel();
    }

    /**
     * creates notification
     * @param view
     */
    public void onCreateNotificationClick(View view) {
        // Create an explicit intent for an Activity in your app
        Intent mainIntent = new Intent(getApplicationContext(), MainActivity.class);
        PendingIntent mainPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, mainIntent, 0);

        Intent registrationIntent = new Intent(getApplicationContext(), RegistrationActivity.class);
        PendingIntent registrationPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, registrationIntent, 0);

        Intent loginIntent = new Intent(getApplicationContext(), LoginActivity.class);
        PendingIntent loginPendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, loginIntent, 0);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
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
        final NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
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
        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
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
        RemoteViews notificationLayout = new RemoteViews(getPackageName(), R.layout.custom_notification);
        RemoteViews notificationLayoutExpanded = new RemoteViews(getPackageName(), R.layout.custom_notification_expanded);

        NotificationCompat.Builder mBuilder = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.mika)
                .setStyle(new NotificationCompat.DecoratedCustomViewStyle())
                .setCustomContentView(notificationLayout)
                .setCustomBigContentView(notificationLayoutExpanded);

        NotificationManager notificationManager = (NotificationManager)getSystemService(NOTIFICATION_SERVICE);
        notificationManager.notify(count++, mBuilder.build());
    }

    /**
     * creates group notification
     * @param view
     */
    public void onCreateGroupNotificationClick(View view) {
        int SUMMARY_ID = 0;
        String GROUP_NOTIFICATION_KEY = "com.example.leon.myapp.GROUP_NOTIFICATION_KEY";

        Notification notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.mika)
                .setContentTitle("First Title")
                .setContentText("First Content")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                .setGroup(GROUP_NOTIFICATION_KEY)
                .build();

        Notification second_notification = new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.mika)
                .setContentTitle("Second Title")
                .setContentText("Second Content")
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                .setGroup(GROUP_NOTIFICATION_KEY)
                .build();

        Notification summaryNotification =  new NotificationCompat.Builder(getApplicationContext(), CHANNEL_ID)
                .setSmallIcon(R.drawable.mika)
                .setContentTitle("Summary Content Title")
                .setContentText("Summary Content")
                .setSmallIcon(R.drawable.mika)
                .setStyle(new NotificationCompat.BigTextStyle().bigText("Much longer text that cannot fit one line..."))
                .setGroup(GROUP_NOTIFICATION_KEY)
                .setGroupSummary(true)
                .build();

        NotificationManagerCompat notificationManager = NotificationManagerCompat.from(this);
        notificationManager.notify(count++, notification);
        notificationManager.notify(count++, second_notification);
        notificationManager.notify(SUMMARY_ID, summaryNotification);
    }

    private void createNotificationChannel() {
        if (Build.VERSION.SDK_INT >= 26) {
            CharSequence name = getString(R.string.notification_channel_name);
            String description = getString(R.string.notification_channel_description);

            int importance = NotificationManager.IMPORTANCE_HIGH;
            NotificationChannel channel = new NotificationChannel(CHANNEL_ID, name, importance);
            channel.enableLights(true);
            channel.setLightColor(R.color.light_color);
            channel.setVibrationPattern(new long[] { 5, 10, 5});
            channel.setDescription(description);

            NotificationManager notificationManager = getSystemService(NotificationManager.class);
            notificationManager.createNotificationChannel(channel);
        }
    }
}
