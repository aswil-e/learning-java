package com.example.learningjava;

import java.time.*;
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
                        if (!dayPlanner.boolpresencecheck(date)) {
                            LocalTime starttime = timevalidation("Start"), endtime  = timevalidation("End");String data = dataentry(); 
                            dayPlanner.addtoplanner(date, starttime, endtime, data);
                            view(date);
                            changes(date);
                            break;
                        }
                        System.out.println("Error: Entry already present");
                        
                    } catch (Exception e) {
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
            LocalTime starttime, endTime;String data;
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
                        starttime = timevalidation("Start"); data = "";                 
                        dayPlanner.edittimeslot(starttime, dataentry(), date);
                    }catch(Exceptionpack e){
                        System.err.println( "Error: " + e.getMessage());
                    }
                    view(date);
                    break;

                case "C":
                    starttime = timevalidation("Start");data = dataentry();
                    try{
                        endTime = timevalidation("End");
                        dayPlanner.addtimeslot(date,starttime, endTime, data);
                        today = LocalDate.now(); 
                        
                    }catch(Exceptionpack e){
                        System.err.println( "Error: " + e.getMessage());
                    };
                    view(date);
                    break;
                
                case "D":
                    starttime = timevalidation("Start");
                    try {
                        System.out.println("**deleted time slot [" + starttime + "]["+dayPlanner.Deletetimeslot(date, starttime)+"]**");
                    }catch(Exceptionpack e){
                        System.out.println("Error: " + e.getMessage());
                    }
                    view(date);
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
        while (date ==  null) {
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
            }    
        }

        return date;
    }
    
    public static LocalTime timevalidation(String S){
        int hours = -1, minutes = -1;
        LocalTime time = null;
        while (time == null) {
            try{
                System.out.println("\nwhat is the "+S+" time(24Hr):- ");
                System.out.print("\tHours:  ");
                hours = input.nextInt();
                System.out.print("\tminutes: ");
                minutes = input.nextInt();
                input.nextLine();
                time = LocalTime.parse(String.format("%02d:%02d", hours, minutes));
            
            }catch(DateTimeException e){
                System.out.println("Error : check time format");
            }
        }
         
        return time;
    }

    public static String dataentry(){
        input.nextLine();
        System.out.print("\nEnter data:- \n\t- ");
        return input.nextLine();
    }
        

}

    

    