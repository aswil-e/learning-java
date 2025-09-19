package com.example.learningjava;
import java.util.*;

public class days {
    private boolean availability;
    private String date;
    private String day;
    private List<String> time;
    private List<String> data;

    public days(){
       
    }

    public String getdate(){
        return date;
    }

    public  void setdate(String date){
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

    public List<String> gettime(){
        return time;
    }

    public  void setTime(List<String> time){
        this.time = time;
    }

    public List<String> getData(){
        return data;
    }

    public  void setData(List<String> data){
        this.data = data;
    }

}