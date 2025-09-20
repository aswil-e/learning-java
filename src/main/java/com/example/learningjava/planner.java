package com.example.learningjava;
import java.io.*;
import java.nio.file.*;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public class planner {
    public days day;
    private Map<String, days> plannerMap = new TreeMap<>();
    private Map<String, String> timeslotMap = new TreeMap<>();
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
            for (days d : todolist) {
                plannerMap.put(d.getdate(), d);
            }
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void addtoplanner(days newDay){
        plannerMap.put(newDay.getdate(), newDay);
        pushtoplanner();
    }

    public void pushtoplanner(){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), plannerMap.values());
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public boolean clearentry(String date){
        if (plannerMap.containsKey(date)) {
            plannerMap.remove(date);
            pushtoplanner();
            return true; 
        }
        return false;
        
    }

    public void mapper(String date){
        int i = 0;
        for (String time : plannerMap.get(date).gettime()) {
            timeslotMap.put(time, plannerMap.get(date).getData().get(i++));
        }
    }

    public void add(String date, Map<String, String> timeslotMap){
        List<String> time = plannerMap.get(date).gettime();
        List<String> data = plannerMap.get(date).getData();
        for (Map.Entry<String, String> entry : timeslotMap.entrySet()) {
            time.add(entry.getKey());
            data.add(entry.getValue());
        }
    }

    public boolean edittimeslot(String time, String date, String data){
        if (plannerMap.containsKey(date)) {
            if(timeslotMap.containsKey(time)){
                timeslotMap.put(time, data);
                add(date, timeslotMap);
                pushtoplanner();
                return true;
            }
        }
        return false;
        
    }
    
    public boolean addtimeslot(String date, String time, String data){
        if(plannerMap.containsKey(date)){
            if (plannerMap.containsKey(time)) {
                System.out.println("**time slot already present**");
                return false;
            }else{
                timeslotMap.put(time, data);
                add(date, timeslotMap);
                pushtoplanner();
                return true;
            }
        }
        return false;
    }

    public boolean boolpresencecheck(String querry){
        if (plannerMap.containsKey(querry)) {
            return true;
        }
        return false;
    }

    public boolean Deletetimeslot(String date, String time){
        if (timeslotMap.containsKey(time)){
            plannerMap.get(date).gettime().remove(time);
            plannerMap.get(date).getData().remove(timeslotMap.get(time));
            timeslotMap.remove(time);
            pushtoplanner();
            return true;
        }
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
        if (plannerMap.containsKey(date)) {
            System.out.println(date+" - "+ plannerMap.get(date).getday());
            for (int i = 0; i < plannerMap.get(date).getData().size(); i++) {
                System.out.println( plannerMap.get(date).gettime().get(i)+"\t"+ plannerMap.get(date).getData().get(i));
            }  
        }else{
            System.out.println(date + " - No entries present");
            return false;
        }
        return true;
        
    }


}
