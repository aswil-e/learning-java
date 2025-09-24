package com.example.learningjava;

import java.time.LocalTime;

public class slot{
    private LocalTime end;
    private String data;

    public LocalTime getend(){
        return end;
    }

    public  void setend(LocalTime end){
        this.end = end;
    }
    
    public String getdata(){
        return data;
    }

    public  void setData(String data){
        this.data= data;
    }

}
