package com.example.babytracker;

public class BabyTracker {
    private Integer _id;
    private String category;
    private String date;
    private String startTime;
    private String stopTime;
    private Float ounces;
    private String diaperType;
    private String diaperColor;
    private String notes;

    public BabyTracker(Integer _id, String category, String date, String startTime, String stopTime, Float ounces, String diaperType, String diaperColor, String notes){
        super();
        this._id = _id;
        this.category = category;
        this.date = date;
        this.startTime = startTime;
        this.stopTime = stopTime;
        this.ounces = ounces;
        this.diaperType = diaperType;
        this.diaperColor = diaperColor;
        this.notes = notes;
    }

    public BabyTracker() {

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

    public Float getOunces(){ return ounces; }

    public void setOunces(Float ounces){
        this.ounces = ounces;
    }

    public String getDiaperType(){
        return diaperType;
    }

    public void setDiaperType(String diaperType){
        this.diaperType = diaperType;
    }

    public String getDiaperColor(){
        return diaperColor;
    }

    public void setDiaperColor(String diaperColor){
        this.diaperColor = diaperColor;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
