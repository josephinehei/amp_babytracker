package com.example.babytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.List;


public class DatabaseHelper extends SQLiteOpenHelper{

    private static final String DB_NAME = "babytracker";
    private static final int DB_VERSION = 2;

    public DatabaseHelper(Context context){
        super(context, DB_NAME, null, DB_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        updateMyDatabase(db, 0, DB_VERSION);
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        updateMyDatabase(db, oldVersion, newVersion);
    }

    private void updateMyDatabase(SQLiteDatabase db, int oldVersion, int newVersion){
        if(oldVersion < 1){
            db.execSQL("CREATE TABLE BABY ("
                    + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "CATEGORY TEXT, "
                    + "DATE TEXT, "
                    + "STARTTIME TEXT, "
                    + "STOPTIME TEXT, "
                    + "OUNCES REAL, "
                    + "DIAPERTYPE TEXT, "
                    + "DIAPERCOLOR TEXT, "
                    + "NOTES TEXT);");
        }
        if(oldVersion < 2){}
    }

    public boolean insertData(String category, String date, String startTime, String stopTime, Float ounces, String diaperType, String diaperColor, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("startTime", startTime);
        cv.put("stopTime", stopTime);
        cv.put("ounces", ounces);
        cv.put("diaperType", diaperType);
        cv.put("diaperColor", diaperColor);
        cv.put("notes", notes);
        db.insert("BABY", null, cv);
        return true;
    }

    public boolean updateTacker(Integer id, String category, String date, String startTime, String stopTime, Float ounces, String diaperType, String diaperColor, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("startTime", startTime);
        cv.put("stopTime", stopTime);
        cv.put("ounces", ounces);
        cv.put("diaperType", diaperType);
        cv.put("diaperColor", diaperColor);
        cv.put("notes", notes);
        db.update("BABY", cv, "_id = ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public Cursor getDataById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from baby where _id="+id+"", null);
        return cursor;
    }

    public int deleteDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer countDeleted = db.delete("BABY", "_id=?", new String[]{Integer.toString(id)});
        return countDeleted;
    }

    public List<BabyTracker> getAllRecords() {
        List<BabyTracker> list = new ArrayList<BabyTracker>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from baby", null);
        if(cursor.moveToFirst()){
            do{
                BabyTracker et = new BabyTracker();
                et.setId(Integer.parseInt(cursor.getString(0)));
                et.setCategory(cursor.getString(1));
                et.setDate(cursor.getString(2));
                et.setStartTime(cursor.getString(3));
                et.setStopTime(cursor.getString(4));
                et.setOunces(Float.parseFloat(cursor.getString(5)));
                et.setDiaperType(cursor.getString(6));
                et.setDiaperColor(cursor.getString(7));
                et.setNotes(cursor.getString(8));
                list.add(et);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public BabyTracker getRecord(Integer id) {
        BabyTracker record = new BabyTracker();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("select * from baby where _id=%s", id);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setCategory(cursor.getString(1));
                record.setDate(cursor.getString(2));
                record.setStartTime(cursor.getString(3));
                record.setStopTime(cursor.getString(4));
                record.setOunces(Float.parseFloat(cursor.getString(5)));
                record.setDiaperType(cursor.getString(6));
                record.setDiaperColor(cursor.getString(7));
                record.setNotes(cursor.getString(8));
            } while(cursor.moveToNext());
        }
        return record;
    }
}
