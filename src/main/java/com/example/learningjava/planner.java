package com.example.learningjava;
import java.io.*;
import java.nio.file.*;
import java.util.*;
import java.time.*;

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
        try{
            mapper.writerWithDefaultPrettyPrinter().writeValue(new File(filename), todolist);
        }catch(Exception e){
            System.out.println(e.toString());
        }

    }

    public int checkifpresent(String querry){
        for (int index = 0; index < todolist.size(); index++) {
            if (todolist.get(index).getdate().equals(querry)) {
                return index;
            }
        }
        return -1;
    }

    public void to_String(String date){
        System.out.println("\n\t** DAY PLANNER **");
        int index = checkifpresent(date);
        if (index > -1) {
            System.out.println(date+" - "+ todolist.get(index).getday());
            for (int i = 0; i < todolist.get(index).getData().size(); i++) {
                System.out.println( todolist.get(index).gettime().get(i)+"\t"+ todolist.get(index).getData().get(i));
            }  
        }else{
             System.out.println(date + " - No entries present");

        }
        
        
    }


}
