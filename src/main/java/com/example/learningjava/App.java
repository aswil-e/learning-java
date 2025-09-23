package com.example.learningjava;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

public class App {
    static LocalDate today ;
    static Scanner input = new Scanner(System.in);
    static LocalDate datetoday = LocalDate.now();
    static planner dayPlanner;
    public static void main(String[] args) {
        try{
    dayPlanner = new planner(datetoday);
    }catch(Exception e){
        System.out.println("sumn aynt right");
    }
        String choice = "";
        
        view(datetoday);

        while (!choice.toUpperCase().equals("E")) {
            LocalDate date;
            System.out.println("\nwould you like to:- ");
            System.out.print(" A: Create New Enrtry.\n B: view specific date.\n C: Edit todays entry\n E: Exit \n : ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    try{
                        date = datevalidation();
                        dayPlanner.boolpresencecheck(date);
                        LocalTime time = timevalidation();String data = dataentry(); 
                        dayPlanner.addtoplanner(date, time, data);
                        view(date);
                    } catch (Exceptionpack e) {
                        System.err.println("Error: " + e.getMessage());
                    }
                
                case "B":
                    date = datevalidation();
                    view(date);
                    changes(date);
                    break;
                
                case "C":
                    date = datetoday;
                    view(date);
                    changes(date);
                    break;

                case "E":
                    break;
                                
                default:
                    System.out.println("**Invalid Input! Try Again.**");
                    break;

                    
            }}
        }
     
    public static void changes(LocalDate date){
        String choice = "";
        while (!choice.toUpperCase().equals("E")) {
            System.out.println("\nwould you like to:- ");
            System.out.print(" A: Clear this entry.\n B: Edit time slot.\n C: Add new data.\n D: Delete time slot.\n E: Home.\n  :");
            LocalTime time;String data;
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    try{
                        dayPlanner.clearentry(date);
                        System.out.println("**ENTRY CLEARED**");
                    }catch(Exceptionpack e){
                        System.err.println("Error: "+ e.getMessage());
                    }  
                    view(date);
                    break;

                case "B":
                    try{
                        dayPlanner.boolpresencecheck(date);
                        time = timevalidation(); data = "";                 
                        dayPlanner.edittimeslot(time, date, dataentry());
                    }catch(Exceptionpack e){
                        System.err.println( "Error: " + e.getMessage());
                    }
                    view(date);
                    break;

                case "C":
                    time = timevalidation();data = dataentry();
                    try{
                        dayPlanner.addtimeslot(date,time, data);
                        today = LocalDate.now(); 
                        
                    }catch(Exceptionpack e){
                        System.err.println( "Error: " + e.getMessage());
                    };
                    view(date);
                    break;
                
                case "D":
                    time = timevalidation();
                    try {
                        System.out.println("**deleted time slot [" + time + "]["+dayPlanner.Deletetimeslot(date, time)+"]**");
                    }catch(Exceptionpack e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;

                case "E":
                    view(datetoday);
                    break;
                default:
                    System.out.println("**Invalid Input! Try Again.**");
                    break;
            }
        }
    }

    public static void view(LocalDate date) {
        try {
            dayPlanner.toString(date);
        } catch (Exceptionpack e) {
            System.err.println(e.getMessage());
        }
        
    }

    public static LocalDate datevalidation(){
        LocalDate date = null;
        int day = 0, month = 0, year = 0;
        try{
            System.out.println("\nWhat is the date:-");
            System.out.print("\tday: ");
            day = input.nextInt();
            System.out.print("\tmonth: ");
            month = input.nextInt();
            System.out.print("\tyear: ");
            year = input.nextInt();
            input.nextLine();
            date = LocalDate.parse(String.format("%04d-%02d-%02d", year,  month, day));
            today = LocalDate.of(year, month, day);

        }catch(DateTimeException e){
            System.err.println("Error : " + e.getMessage());
            return datevalidation();
        }
        return date;
    }
    //add the error text and stopage ting
    public static LocalTime timevalidation(){
        int hours = -1, minutes = -1;
        LocalTime time = null;
        try{
            System.out.println("\nwhat is the time(24Hr):- ");
            System.out.print("\tHours:  ");
            hours = input.nextInt();
            System.out.print("\tminutes: ");
            minutes = input.nextInt();
            time = LocalTime.parse(String.format("%02d:%02d", hours, minutes));
            
        }catch(DateTimeException e){
            System.out.println("Error : "+ e.getMessage());
        } 
        return time;
    }

    public static String dataentry(){
        input.nextLine();
        System.out.print("\nEnter data:- \n\t- ");
        return input.nextLine();
    }
        

}

    

    