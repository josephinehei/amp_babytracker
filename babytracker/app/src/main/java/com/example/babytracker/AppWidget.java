package com.example.babytracker;

import android.app.PendingIntent;
import android.appwidget.AppWidgetProvider;
import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Build;
import android.util.Log;
import android.widget.RemoteViews;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class AppWidget extends AppWidgetProvider {

    private static final String sleepButton = "sleepButtonTag";
    private static final String foodButton = "foodButtonTag";
    private static final String peeButton = "peeButtonTag";
    private static final String poopButton = "poopButtonTag";
    SQLiteOpenHelper dBH;

    @Override
    public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds){
        for(int appWidgetId: appWidgetIds) {
            updateAppWidget(context, appWidgetManager, appWidgetId);
        }
    }


    static void updateAppWidget(Context context, AppWidgetManager appWidgetManager, int appWidgetId) {

        RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);

        views.setOnClickPendingIntent(R.id.widgetButton1, getPendingIntent(context, sleepButton, appWidgetId));
        views.setOnClickPendingIntent(R.id.widgetButton2, getPendingIntent(context, foodButton, appWidgetId));
        views.setOnClickPendingIntent(R.id.widgetButton3, getPendingIntent(context, peeButton, appWidgetId));
        views.setOnClickPendingIntent(R.id.widgetButton4, getPendingIntent(context, poopButton, appWidgetId));

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

        DateFormat df = new SimpleDateFormat("MM/dd");
        String date = df.format(Calendar.getInstance().getTime());
        DateFormat tdf = new SimpleDateFormat("hh:mm a");
        String time = tdf.format(Calendar.getInstance().getTime());
        Calendar timeLater = Calendar.getInstance();
        timeLater.add(Calendar.MINUTE, 30);
        String timePlus = tdf.format(timeLater.getTime());

        dBH = new DatabaseHelper(context);

        if(sleepButton.equals(intent.getAction())){
            Toast.makeText(context, "30 minute sleep recorded", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Sleep");
            ((DatabaseHelper) dBH).insertDataSleep(
                    "Sleep",
                    date,
                    time,
                    timePlus,
                    "added using widget"
            );
        } else if(foodButton.equals(intent.getAction())) {
            Toast.makeText(context, "2oz feeding recorded", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Food");
            ((DatabaseHelper) dBH).insertDataFood(
                    "Food",
                    date,
                    time,
                    2F,
                    "added using widget"
            );
        } else if(peeButton.equals(intent.getAction())) {
            Toast.makeText(context, "pee recorded", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Pee");
            ((DatabaseHelper) dBH).insertDataDiaper(
                    "Diaper",
                    date,
                    time,
                    "pee",
                    "",
                    "added using widget"
            );
        } else if(poopButton.equals(intent.getAction())) {
            Toast.makeText(context, "poop recorded", Toast.LENGTH_SHORT).show();
            Log.w("Widget", "Clicked Poo");
            ((DatabaseHelper) dBH).insertDataDiaper(
                    "Diaper",
                    date,
                    time,
                    "poop",
                    "",
                    "added using widget"
            );
        }
    }
}
