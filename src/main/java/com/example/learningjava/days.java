package com.example.learningjava;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.*;

public class days {
    private boolean availability;
    private LocalDate date;
    private String day;
    private List<LocalTime> time ;
    private List<String> data ;

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

    public List<LocalTime> gettime(){
        return time;
    }

    public  void setTime(List<LocalTime> time){
        this.time = time;
    }

    public List<String> getData(){
        return data;
    }

    public  void setData(List<String> data){
        this.data= data;
    }

}