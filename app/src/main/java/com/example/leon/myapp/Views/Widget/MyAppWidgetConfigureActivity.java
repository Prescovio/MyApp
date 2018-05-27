package com.example.leon.myapp.Views.Widget;

import android.app.PendingIntent;
import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.RemoteViews;

import com.example.leon.myapp.R;
import com.example.leon.myapp.Views.Main.MainActivity;

public class MyAppWidgetConfigureActivity extends AppCompatActivity {
    private int mAppWidgetId;
    private static int count;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_app_widget_configure);

        //get intent that called the activity
        Intent intent = getIntent();
        Bundle extras = intent.getExtras();

        if (extras != null) {
            mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
        }

        Button btnCreateWidget = findViewById(R.id.app_widget_configure_create_button);
        btnCreateWidget.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                configurationFinished();
            }
        });
    }

    private void configurationFinished() {
        if (Build.VERSION.SDK_INT >= 23) {
            AppWidgetManager appWidgetManager = getApplicationContext().getSystemService(AppWidgetManager.class);

            Intent intent = new Intent(getApplicationContext(), MainActivity.class);
            PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

            RemoteViews views = new RemoteViews(this.getPackageName(), R.layout.custom_my_app_widget_provider);
            views.setOnClickPendingIntent(R.id.app_widget_test_button, pendingIntent);
            views.setTextViewText(R.id.app_widget_test_text_view, String.valueOf(count++));

            appWidgetManager.updateAppWidget(mAppWidgetId, views);

            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_OK, resultValue);
        } else {
            Intent resultValue = new Intent();
            resultValue.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
            setResult(RESULT_CANCELED, resultValue);
        }

        finish();
    }
}
