package com.example.learningjava;
import java.io.*;
import java.nio.file.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class planner {
    public days day;
    private Map<LocalDate, days> plannerMap = new TreeMap<>();
    private Map<LocalTime, String> timeslotMap;
    public List<days> todolist;
    private String filename = "C:\\Users\\DELL\\Desktop\\demo\\src\\main\\java\\com\\example\\todolist.json";
    private String content;
    public LocalDate date;
    ObjectMapper mapper;

    public planner(LocalDate date){
        try{
            this.content =new String(Files.readAllBytes(Paths.get(filename)));
            mapper = new ObjectMapper();
            mapper.registerModule(new JavaTimeModule());
            todolist = mapper.readValue(content, new TypeReference<List<days>>(){});
            this.date = date;
            for (days d : todolist) {
                plannerMap.put(d.getdate(), d);
            }
            //mapper(date);
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public void addtoplanner(LocalDate date, LocalTime time, String data){
        List<LocalTime> timelist = new ArrayList<LocalTime>();List<String> datalist = new ArrayList<String>(); 
        DayOfWeek day = date.getDayOfWeek(); 
        days newDay = new days();
        newDay.setdate(date);
        newDay.setday(day.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        newDay.setavailability(false);
        timelist.add(time);
        datalist.add(data);
        newDay.setTime(timelist);
        newDay.setData(datalist);
        plannerMap.put(date, newDay);
        pushtoplanner();
    }

    public void pushtoplanner(){
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), plannerMap.values());
        }catch(Exception e){
            System.out.println(e.toString());
        }
    }

    public boolean clearentry(LocalDate date)throws Exceptionpack{
        if (plannerMap.containsKey(date)) {
            plannerMap.remove(date);
            pushtoplanner();
            return true; 
        }
        throw new Exceptionpack("**entry empty**");
        
    }

    private void mapper(LocalDate date, Map<LocalTime, String> timeslotMap){
        int i = 0;
        for (LocalTime time : plannerMap.get(date).gettime()) {
            timeslotMap.put(time, plannerMap.get(date).getData().get(i++));
        }
    }

    public void add(LocalDate date){
        List<LocalTime> time = plannerMap.get(date).gettime();
        List<String> data = plannerMap.get(date).getData();
        for (Map.Entry<LocalTime, String> entry : timeslotMap.entrySet()) {
            time.add(entry.getKey());
            data.add(entry.getValue());
        }
    }

    public boolean edittimeslot(LocalTime time, LocalDate date, String data) throws Exceptionpack{
        if (plannerMap.containsKey(date)) {
            timeslotMap = new TreeMap<>();
            mapper(date, timeslotMap);
            if(timeslotMap.containsKey(time)){
                timeslotMap.put(time, data);
                plannerMap.get(date).getData().set(plannerMap.get(date).gettime().indexOf(time), data);
                pushtoplanner();
                return true;
            }
        }
        throw new Exceptionpack("time slot **"+ time +"** not present");
        
    }
    
    public boolean addtimeslot(LocalDate date, LocalTime time, String data) throws Exceptionpack{
        timeslotMap = new TreeMap<>();
        if(!plannerMap.containsKey(date)){
            addtoplanner(date, time, data);
            timeslotMap.put(time, data);
            return true;
        }else{
            mapper(date, timeslotMap);
            if (!timeslotMap.containsKey(time)){             
                plannerMap.get(date).gettime().add(time);
                plannerMap.get(date).getData().add(data);
                timeslotMap.put(time,data);
                pushtoplanner();
                return true;
            }
            throw new Exceptionpack("time slot **"+ time +"** already present with data : "+ timeslotMap.get(time));
        }
    }

    public boolean boolpresencecheck(LocalDate querry){
        if (plannerMap.containsKey(querry)) {
            return true;
        }
        return false;
        
    }

    public String Deletetimeslot(LocalDate date, LocalTime time) throws Exceptionpack{
        if(plannerMap.containsKey(date)){
            timeslotMap = new TreeMap<>();
            mapper(date, timeslotMap);
            if (timeslotMap.containsKey(time)){
                int index = plannerMap.get(date).gettime().indexOf(time);
                String deleteddata = plannerMap.get(date).getData().get(index);
                plannerMap.get(date).getData().remove(index);
                plannerMap.get(date).gettime().remove(time);
                timeslotMap.remove(time);
                pushtoplanner();
                return deleteddata;
            }
            throw new Exceptionpack("**time slot not present**");
        }
        throw new Exceptionpack("**entry not present**");
    }

    public boolean toString(LocalDate date) throws Exceptionpack{
        System.out.println("\n\t** DAY PLANNER **");
        if (plannerMap.containsKey(date)) {
            System.out.println(date+" : "+ plannerMap.get(date).getday());
            for (int i = 0; i < plannerMap.get(date).getData().size(); i++) {
                System.out.println( plannerMap.get(date).gettime().get(i)+"\t-"+ plannerMap.get(date).getData().get(i));
            }
            return true;  
        }
        throw new Exceptionpack(date + " : No entries present");
        
    }


}
