package com.example.learningjava;
import java.time.LocalDate;
import java.util.*;

public class days {
    private boolean availability;
    private LocalDate date;
    private String day;
    private Map<String, slot> timeslots;
    

    public days(){
       
    }

    public LocalDate getdate(){
        return date;
    }

    public  void setdate(LocalDate date){
        this.date = date;
    }
    
    public boolean isavailability(){
        return availability;
    }

    public  void setavailability(boolean availability){
        this.availability = availability;
    }

    public String getday(){
        return day;
    }

    public  void setday(String day){
        this.day = day;
    }

    public void settimeslots(Map<String, slot> timeslots){
        this.timeslots = timeslots;
    }

    public Map<String, slot> gettimeslots(){
        return this.timeslots;
    }

}