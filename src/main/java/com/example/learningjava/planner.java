package com.example.learningjava;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class planner {
    public days day;
    public List<days> todolist;
    private String filename = "C:\\Users\\DELL\\Desktop\\demo\\src\\main\\java\\com\\example\\todolist.json";
    private String content;
    public String date;
    ObjectMapper mapper;

    public planner(String date){
        try{
            this.content =new String(Files.readAllBytes(Paths.get(filename)));
            mapper = new ObjectMapper();
            todolist = mapper.readValue(content, new TypeReference<List<days>>(){});
            this.date = date;
            
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void addtoplanner(days newDay){
        todolist.add(newDay);
        pushtoplanner();
    }

    public void pushtoplanner(){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), todolist);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public boolean clearentry(String date){
        int index = checkifpresent(date);
        if (index > -1) {
            todolist.remove(index);
            pushtoplanner();
            return true; 
        }
        return false;
        
    }
    
    public boolean edittimeslot(String time, String date, String data){
        int index = checkifpresent(date);
        if (index > -1) {
            List<String> timelist = todolist.get(index).gettime();
            if(timelist.indexOf(time) >= 0){
                todolist.get(index).getData().set(timelist.indexOf(time), data);
                pushtoplanner();
                return true;
            }
        }
        return false;
        
    }
    
    public boolean addtimeslot(String date, String time, String data){
        int index = checkifpresent(date);
        if(index > -1){
            if (todolist.get(index).gettime().indexOf(time) >= 0) {
                System.out.println("**time slot already present**");
                return false;
            }else{
                todolist.get(index).gettime().add(time);
                todolist.get(index).getData().add(data);
                pushtoplanner();
                return true;
            }
            
        }
        System.out.println("**entry not present**");
        return false;
    }

    public int checkifpresent(String querry){
        for (days day : todolist) {
            if (day.getdate().equals(querry)) {
                return todolist.indexOf(day);
            }
        }
        return -1;
    }

    public boolean to_String(String date){
        System.out.println("\n\t** DAY PLANNER **");
        int index = checkifpresent(date);
        if (index > -1) {
            System.out.println(date+" - "+ todolist.get(index).getday());
            for (int i = 0; i < todolist.get(index).getData().size(); i++) {
                System.out.println( todolist.get(index).gettime().get(i)+"\t"+ todolist.get(index).getData().get(i));
            }  
        }else{
            System.out.println(date + " - No entries present");
            return false;
        }
        return true;
        
    }


}
