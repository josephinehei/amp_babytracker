package com.example.babytracker;

public class SleepTracker {
    private Integer _id;
    private String category;
    private String date;
    private String startTime;
    private String stopTime;
    private String notes;

    public SleepTracker(Integer _id, String category, String date, String startTime, String stopTime, String notes){
        super();
        this._id = _id;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.notes = notes;
    }

    public SleepTracker() {

    }

    public Integer getId(){
        return _id;
    }

    public void setId(Integer _id){
        this._id = _id;
    }

    public String getCategory(){
        return category;
    }

    public void setCategory(String category){
        this.category = category;
    }

    public String getDate(){
        return date;
    }

    public void setDate(String date){
        this.date = date;
    }

    public String getStartTime(){
        return startTime;
    }

    public void setStartTime(String startTime){
        this.startTime = startTime;
    }

    public String getStopTime(){
        return stopTime;
    }

    public void setStopTime(String startTime){
        this.stopTime = stopTime;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
