package com.example.babytracker;

public class DiaperTracker {
    private Integer _id;
    private String category;
    private String date;
    private String time;
    private String diaperType;
    private String diaperColor;
    private String notes;

    public DiaperTracker(Integer _id, String category, String date, String time, String diaperType, String diaperColor, String notes){
        super();
        this._id = _id;
        this.category = category;
        this.date = date;
        this.time = time;
        this.diaperType = diaperType;
        this.diaperColor = diaperColor;
        this.notes = notes;
    }

    public DiaperTracker() {

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

    public String getTime(){
        return time;
    }

    public void setTime(String time){
        this.time = time;
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
