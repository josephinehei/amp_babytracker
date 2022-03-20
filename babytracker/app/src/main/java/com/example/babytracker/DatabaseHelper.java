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
    private static final int DB_VERSION = 1;

    //Table Names
    private static final String SLEEP_TABLE = "SLEEP_TABLE";
    private static final String FOOD_TABLE = "FOOD_TABLE";
    private static final String DIAPER_TABLE = "DIAPER_TABLE";

    //Creating tables
    private static final String CREATE_SLEEP_TABLE = "CREATE TABLE " + SLEEP_TABLE
            + "("
            + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "CATEGORY TEXT, "
            + "DATE TEXT, "
            + "STARTTIME TEXT, "
            + "STOPTIME TEXT, "
            + "NOTES TEXT "
            + ")";

    private static final String CREATE_FOOD_TABLE = "CREATE TABLE " + FOOD_TABLE
            + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "CATEGORY TEXT, "
            + "DATE TEXT, "
            + "TIME TEXT, "
            + "OUNCES REAL, "
            + "NOTES TEXT " + ")";

    private static final String CREATE_DIAPER_TABLE = "CREATE TABLE " + DIAPER_TABLE
            + "(" + "_id INTEGER PRIMARY KEY AUTOINCREMENT, "
            + "CATEGORY TEXT, "
            + "DATE TEXT, "
            + "TIME TEXT, "
            + "DIAPERTYPE TEXT, "
            + "DIAPERCOLOR TEXT, "
            + "NOTES TEXT " + ")";

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
            db.execSQL(CREATE_SLEEP_TABLE);
            db.execSQL(CREATE_FOOD_TABLE);
            db.execSQL(CREATE_DIAPER_TABLE);
        }
        if(oldVersion < 2){}
    }

    public boolean insertDataSleep(String category, String date, String startTime, String stopTime, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("startTime", startTime);
        cv.put("stopTime", stopTime);
        cv.put("notes", notes);
        db.insert("SLEEP_TABLE", null, cv);
        return true;
    }

    public boolean insertDataFood(String category, String date, String time, Float ounces, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("ounces", ounces);
        cv.put("notes", notes);
        db.insert("FOOD_TABLE", null, cv);
        return true;
    }

    public boolean insertDataDiaper(String category, String date, String time, String diaperType, String diaperColor, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("diaperType", diaperType);
        cv.put("diaperColor", diaperColor);
        cv.put("notes", notes);
        long i = db.insert("DIAPER_TABLE", null, cv);
        return true;
    }

    public boolean updateTrackerSleep(Integer id, String category, String date, String startTime, String stopTime,String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("startTime", startTime);
        cv.put("stopTime", stopTime);
        cv.put("notes", notes);
        db.update("SLEEP_TABLE", cv, "_id = ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateTrackerFood(Integer id, String category, String date, String time, Float ounces, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("ounces", ounces);
        cv.put("notes", notes);
        db.update("FOOD_TABLE", cv, "_id = ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public boolean updateTrackerDiaper(Integer id, String category, String date, String time, String diaperType, String diaperColor, String notes) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("category", category);
        cv.put("date", date);
        cv.put("time", time);
        cv.put("diaperType", diaperType);
        cv.put("diaperColor", diaperColor);
        cv.put("notes", notes);
        db.update("DIAPER_TABLE", cv, "_id = ? ", new String[] {Integer.toString(id)});
        return true;
    }

    public int deleteSleepDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer countDeleted = db.delete("SLEEP_TABLE", "_id=?", new String[]{Integer.toString(id)});
        return countDeleted;
    }

    public int deleteFoodDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer countDeleted = db.delete("FOOD_TABLE", "_id=?", new String[]{Integer.toString(id)});
        return countDeleted;
    }

    public int deleteDiaperDataById(int id) {
        SQLiteDatabase db = this.getWritableDatabase();
        Integer countDeleted = db.delete("DIAPER_TABLE", "_id=?", new String[]{Integer.toString(id)});
        return countDeleted;
    }

    public List<SleepTracker> getAllSleepRecords() {
        List<SleepTracker> list = new ArrayList<SleepTracker>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from sleep_table", null);
        if(cursor.moveToFirst()){
            do{
                SleepTracker et = new SleepTracker();
                et.setId(Integer.parseInt(cursor.getString(0)));
                et.setCategory(cursor.getString(1));
                et.setDate(cursor.getString(2));
                et.setStartTime(cursor.getString(3));
                et.setStopTime(cursor.getString(4));
                et.setNotes(cursor.getString(5));
                list.add(et);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public List<FoodTracker> getAllFoodRecords() {
        List<FoodTracker> list = new ArrayList<FoodTracker>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from food_table", null);
        if(cursor.moveToFirst()){
            do{
                FoodTracker et = new FoodTracker();
                et.setId(Integer.parseInt(cursor.getString(0)));
                et.setCategory(cursor.getString(1));
                et.setDate(cursor.getString(2));
                et.setTime(cursor.getString(3));
                et.setOunces(Float.parseFloat(cursor.getString(4)));
                et.setNotes(cursor.getString(5));
                list.add(et);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public List<DiaperTracker> getAllDiaperRecords() {
        List<DiaperTracker> list = new ArrayList<DiaperTracker>();
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("select * from diaper_table", null);
        if(cursor.moveToFirst()){
            do{
                DiaperTracker et = new DiaperTracker();
                et.setId(Integer.parseInt(cursor.getString(0)));
                et.setCategory(cursor.getString(1));
                et.setDate(cursor.getString(2));
                et.setTime(cursor.getString(3));
                et.setDiaperType(cursor.getString(4));
                et.setDiaperColor(cursor.getString(5));
                et.setNotes(cursor.getString(6));
                list.add(et);
            } while(cursor.moveToNext());
        }
        return list;
    }

    public SleepTracker getSleepRecord(Integer id) {
        SleepTracker record = new SleepTracker();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("select * from sleep_table where _id=%s", id);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setCategory(cursor.getString(1));
                record.setDate(cursor.getString(2));
                record.setStartTime(cursor.getString(3));
                record.setStopTime(cursor.getString(4));
                record.setNotes(cursor.getString(5));
            } while(cursor.moveToNext());
        }
        return record;
    }

    public FoodTracker getFoodRecord(Integer id) {
        FoodTracker record = new FoodTracker();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("select * from food_table where _id=%s", id);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setCategory(cursor.getString(1));
                record.setDate(cursor.getString(2));
                record.setTime(cursor.getString(3));
                record.setOunces(Float.parseFloat(cursor.getString(4)));
                record.setNotes(cursor.getString(5));
            } while(cursor.moveToNext());
        }
        return record;
    }

    public DiaperTracker getDiaperRecord(Integer id) {
        DiaperTracker record = new DiaperTracker();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = String.format("select * from diaper_table where _id=%s", id);
        Cursor cursor = db.rawQuery(query, null);
        if(cursor.moveToFirst()){
            do{
                record.setId(Integer.parseInt(cursor.getString(0)));
                record.setCategory(cursor.getString(1));
                record.setDate(cursor.getString(2));
                record.setTime(cursor.getString(3));
                record.setDiaperType(cursor.getString(4));
                record.setDiaperColor(cursor.getString(5));
                record.setNotes(cursor.getString(6));
            } while(cursor.moveToNext());
        }
        return record;
    }
}
