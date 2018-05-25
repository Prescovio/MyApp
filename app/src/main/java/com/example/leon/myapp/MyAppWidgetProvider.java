package com.example.leon.myapp;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.leon.myapp.Views.Main.MainActivity;

public class MyAppWidgetProvider extends AppWidgetProvider {
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent intent = new Intent(context, MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.custom_my_app_widget_provider);
            views.setOnClickPendingIntent(R.id.app_widget_test_button, pendingIntent);

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    public void onAppWidgetOptionsChanged() {
        //TODO optionschange
    }

    public void onEnabled(Context context) {

    }

    public void onDisabled(Context context) {

    }

    public void onReceive(Context context, Intent intent) {

    }

    public void onDeleted(Context context, int[] appWidgetIds) {

    }
}
