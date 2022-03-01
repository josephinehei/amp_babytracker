package com.example.babytracker;

public class FoodTracker {
    private Integer _id;
    private String category;
    private String date;
    private String time;
    private Float ounces;
    private String notes;

    public FoodTracker(Integer _id, String category, String date, String time, Float ounces, String notes){
        super();
        this._id = _id;
        this.category = category;
        this.date = date;
        this.time = time;
        this.ounces = ounces;
        this.notes = notes;
    }

    public FoodTracker() {

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

    public Float getOunces(){ return ounces; }

    public void setOunces(Float ounces){
        this.ounces = ounces;
    }

    public String getNotes(){
        return notes;
    }

    public void setNotes(String notes){
        this.notes = notes;
    }
}
