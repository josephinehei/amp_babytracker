package com.example.babytracker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

public class AppWidget extends AppWidgetProvider {

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        for(int appWidgetId : appWidgetIds) {
//            Intent intent = new Intent(context, MainActivity.class);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent, 0 );

            RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);
            views.setOnClickPendingIntent(R.id.widgetButton1, getPendingIntent(context, 0));
            views.setOnClickPendingIntent(R.id.widgetButton2, getPendingIntent(context, 0));
            views.setOnClickPendingIntent(R.id.widgetButton3, getPendingIntent(context, 0));

            appWidgetManager.updateAppWidget(appWidgetId, views);
        }
    }

    private PendingIntent getPendingIntent(Context context, Integer value){
        Intent intent = new Intent(context, MainActivity.class);
        return PendingIntent.getActivity(context, value, intent, 0);
    }


}
