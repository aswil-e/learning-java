package com.example.learningjava;
import java.io.*;
import java.nio.file.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.*;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

public class planner {
    public days day;
    private Map<LocalDate, days> plannerMap = new TreeMap<>();
    private Map<String, slot> timeslotMap;
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

    public void addtoplanner(LocalDate date, LocalTime starttime, LocalTime endtime, String data) throws Exceptionpack{
        Map<String, slot> timelist = new TreeMap<>(); 
        if (date.isBefore(LocalDate.now())) {
            slot newslot = new slot();
            DayOfWeek day = date.getDayOfWeek(); 
            days newDay = new days();
            newDay.setdate(date);
            newDay.setday(day.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
            newDay.setavailability(false);

            newslot.setend(endtime);
            newslot.setData(data);

            timelist.put(starttime.format(DateTimeFormatter.ofPattern("HH:mm")), newslot);
            newDay.settimeslots(timelist);
            plannerMap.put(date, newDay);
            pushtoplanner(); 
        }
        throw new Exceptionpack("**can not add to old date**");
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

    private Map<String, slot> mapper(LocalDate date){
        return plannerMap.get(date).gettimeslots();
        
    }

    public boolean edittimeslot(LocalTime starttime, String data, LocalDate date) throws Exceptionpack{
        if (plannerMap.containsKey(date)) {
            timeslotMap = mapper(date);
            if(timeslotMap.containsKey(starttime.format(DateTimeFormatter.ofPattern("HH:mm")))){
                timeslotMap.get(starttime.format(DateTimeFormatter.ofPattern("HH:mm"))).setData(data);
                pushtoplanner();
                return true;
            }
            throw new Exceptionpack("time slot **"+ starttime +"** not present");
        }
        throw new Exceptionpack("**entry not present**");
    }
    
    public boolean addtimeslot(LocalDate date, LocalTime starttime, LocalTime endtime, String data) throws Exceptionpack{
        if(!plannerMap.containsKey(date)){
            timeslotMap = new TreeMap<>();
            addtoplanner(date, starttime, endtime, data);
            return true;
        }else{
            timeslotMap = mapper(date);
            if (!timeslotMap.containsKey(starttime.format(DateTimeFormatter.ofPattern("HH:mm")))){
                if (starttime.isAfter(LocalTime.now())) {
                    slot slot = new slot(); 
                    slot.setend(endtime);
                    slot.setData(data);            
                    timeslotMap.put(starttime.format(DateTimeFormatter.ofPattern("HH:mm")), slot);
                    pushtoplanner();
                    return true;
                }
                throw new Exceptionpack("**cant add slot before current time**");
            }
            throw new Exceptionpack("time slot **"+ starttime + "->" + endtime +"** already present with data : "+ timeslotMap.get(starttime.format(DateTimeFormatter.ofPattern("HH:mm"))).getdata());
        }
    }

    public void timecheck(LocalTime timeadded, LocalTime starttime, LocalTime endtime){

    }

    public boolean boolpresencecheck(LocalDate querry){
        if (plannerMap.containsKey(querry)) {
            return true;
        }
        return false;
        
    }

    public String Deletetimeslot(LocalDate date, LocalTime starttime) throws Exceptionpack{
        if(plannerMap.containsKey(date)){
            timeslotMap = mapper(date);
            if (timeslotMap.containsKey(starttime.format(DateTimeFormatter.ofPattern("HH:mm")))){
                String deleteddata = timeslotMap.get(starttime.format(DateTimeFormatter.ofPattern("HH:mm"))).getdata();
                timeslotMap.remove(starttime.format(DateTimeFormatter.ofPattern("HH:mm")));
                if (timeslotMap.size() ==0){clearentry(date);}
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
            timeslotMap = mapper(date);
            System.out.println(date+" : "+ plannerMap.get(date).getday());
            for (Map.Entry<String, slot> entry : timeslotMap.entrySet()) {
                System.out.println( entry.getKey() +"->"+entry.getValue().getend()+"\t-"+ entry.getValue().getdata());
            }
                
            return true;  
        }
        throw new Exceptionpack(date + " : No entries present");
        
    }


}
