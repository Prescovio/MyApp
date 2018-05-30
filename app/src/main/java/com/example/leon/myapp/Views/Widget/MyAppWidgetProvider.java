package com.example.leon.myapp.Views.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

import com.example.leon.myapp.Platform.Services.Widget.WidgetUpdateIntentService;
import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Main.MainActivity;

public class MyAppWidgetProvider extends AppWidgetProvider {
    private static int count;

    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
        final int N = appWidgetIds.length;

        for (int i=0; i<N; i++) {
            int appWidgetId = appWidgetIds[i];

            Intent startAppIntent = new Intent(context, MainActivity.class);
            PendingIntent startAppPendingIntent = PendingIntent.getActivity(context, appWidgetId, startAppIntent, PendingIntent.FLAG_UPDATE_CURRENT);

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.custom_my_app_widget_provider);

            views.setOnClickPendingIntent(R.id.app_widget_test_button, startAppPendingIntent);
            views.setTextViewText(R.id.app_widget_test_text_view, String.valueOf(count++));

            appWidgetManager.updateAppWidget(appWidgetId, views);

            //create update service with a timer - android only updates the widget every 30 minutes
            Intent widgetUpdateIntentService = new Intent(context, WidgetUpdateIntentService.class);
            context.startService(widgetUpdateIntentService);
        }
    }

    public void onEnabled(Context context) {
        super.onEnabled(context);
    }

    public void onDisabled(Context context) {
        super.onDisabled(context);
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
    }

    public void onDeleted(Context context, int[] appWidgetIds) {
        super.onDeleted(context, appWidgetIds);
    }
}
