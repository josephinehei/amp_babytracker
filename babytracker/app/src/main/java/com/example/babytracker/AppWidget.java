package com.example.babytracker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.util.Log;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.Toast;

public class AppWidget extends AppWidgetProvider {

    private static final String ButtonClick1 = "ButtonClickTag1";
    private static final String ButtonClick2 = "ButtonClickTag2";
    private static final String ButtonClick3 = "ButtonClickTag3";

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        final int N = appWidgetIds.length;
        for(int i = 0; i < N; i++) {
            updateAppWidget(context, appWidgetManager, appWidgetIds[i]);
        }
    }

    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {
        //CharSequence widgetText = widgetButtonProviderConfigureActivity.loadTitlePref(context, appWidgetId);
        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
        views.setOnClickPendingIntent(R.id.widgetButton1, getPendingIntent(context, ButtonClick1, appWidgetId));
        views.setOnClickPendingIntent(R.id.widgetButton2, getPendingIntent(context, ButtonClick2, appWidgetId));
        views.setOnClickPendingIntent(R.id.widgetButton3, getPendingIntent(context, ButtonClick3, appWidgetId));
        appWidgetManager.updateAppWidget(appWidgetId, views);
    }

    static protected PendingIntent getPendingIntent(Context context, String action, int appWidgetId){
        Intent intent = new Intent(context, AppWidget.class);
        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, appWidgetId);
        intent.setAction(action);
        if(Build.VERSION.SDK_INT>= Build.VERSION_CODES.S){
            return PendingIntent.getBroadcast(context, 0, intent, PendingIntent.FLAG_MUTABLE);
        } else {
            return PendingIntent.getBroadcast(context, 0, intent, 0);
        }
    }

    public void onReceive(Context context, Intent intent) {
        super.onReceive(context, intent);
        if(ButtonClick1.equals(intent.getAction())){
            Toast.makeText(context, "Button1", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Sleep");
        } else if(ButtonClick2.equals(intent.getAction())) {
            Toast.makeText(context, "Button2", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Food");
        } else if(ButtonClick3.equals(intent.getAction())) {
            Toast.makeText(context, "Button3", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Diaper");
        }
    }
}
