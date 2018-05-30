package com.example.leon.myapp.Platform.Services.Widget;

import android.app.IntentService;
import android.appwidget.AppWidgetManager;
import android.content.ComponentName;
import android.content.Intent;
import android.widget.RemoteViews;
import android.widget.Toast;

import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Widget.MyAppWidgetProvider;

import java.util.Timer;
import java.util.TimerTask;

public class WidgetUpdateIntentService  extends IntentService {
    private static int count;
    private Timer mBackgroundTimer;
    private AppWidgetManager mAppWidgetManager;
    private ComponentName mComponentName;

    public WidgetUpdateIntentService() {
        super("WidgetUpdateIntentService");

        this.mBackgroundTimer = new Timer();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Toast.makeText(this, "update widget intent service starting", Toast.LENGTH_SHORT).show();

        mAppWidgetManager = AppWidgetManager.getInstance(this);
        mComponentName = new ComponentName(getApplicationContext(), MyAppWidgetProvider.class);

        return super.onStartCommand(intent, flags, startId);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        mBackgroundTimer.schedule(new TimerTask() {
            @Override
            public void run() {
                int[] widgetIds = mAppWidgetManager.getAppWidgetIds(mComponentName);

                for (int widgetId : widgetIds) {
                    RemoteViews views = new RemoteViews(getPackageName(), R.layout.custom_my_app_widget_provider);
                    views.setTextViewText(R.id.app_widget_test_text_view, String.valueOf(count));

                    mAppWidgetManager.updateAppWidget(widgetId, views);
                }
                count++;
            }
        }, 0, 10000);
    }
}