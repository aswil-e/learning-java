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
    static DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    static planner dayPlanner = new planner(datetoday.format(f1));
    public static void main(String[] args) {
        boolean run = true;
        String choice;
        
        dayPlanner.to_String(datetoday.format(f1));

        while (run) {
            System.out.println("\nwould you like to:- ");
            System.out.print(" A: Create New Enrtry.\n B: view specific date.\n C: Edit todays entry\n E: Exit \n : ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    String dateA = datevalidation();
                    if(dayPlanner.boolpresencecheck(dateA)){
                        List<String> time = new ArrayList<String>(), data = new ArrayList<String>(); 
                        time.add(timevalidation());
                        data.add(dataentry());
                        dayPlanner.addtoplanner(addtoplanner(dateA, time, data));
                        dayPlanner.to_String(dateA);
                    }else{
                        System.out.println("**entry already present**");
                    }
                    break;
                
                case "B":
                    String dateB = datevalidation();
                    dayPlanner.to_String(dateB);
                    changes(dateB);
                    break;
                
                case "C":
                    String dateC = datetoday.format(f1);
                    changes(dateC);
                    break;

                case "E":
                    run = false;
                    break;
                                
                default:
                    System.out.println("**Invalid Input! Try Again.**");
                    break;

                    
            }}
        }
     
    public static void changes(String date){
        String choice = "";
        while (!choice.toUpperCase().equals("E")) {
            System.out.println("\nwould you like to:- ");
            System.out.print(" A: Clear this entry.\n B: Edit time slot.\n C: Add new data.\n D: Delete time slot.\n E: Home.\n  :");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    if(dayPlanner.clearentry(date)){;
                        System.out.println("**ENTRY CLEARED**");
                        dayPlanner.to_String(date);
                    }else{
                       System.out.println( "**entry already empty**");
                       dayPlanner.to_String(date);
                    }
                    break;

                case "B":
                    
                    if(dayPlanner.boolpresencecheck(date)){
                        String time = timevalidation(), data = "";
                        if(!dayPlanner.edittimeslot(time, date, data)){
                            System.out.println("**time slot not present**");
                        }
                        dayPlanner.edittimeslot(time, date, dataentry());
                    }else{
                        System.out.println( "**entry empty**");
                    }
                    dayPlanner.to_String(date);
                    break;

                case "C":
                    String timeC = timevalidation(), dataenter = dataentry();
                    if(!dayPlanner.addtimeslot(date,timeC, dataenter)){
                        List<String> timeclist = new ArrayList<String>(), data = new ArrayList<String>();
                        today = LocalDate.now(); 
                        timeclist.add(timeC);
                        data.add(dataenter);
                        dayPlanner.addtoplanner(addtoplanner(date, timeclist, data));
                        
                    };
                    dayPlanner.to_String(date);
                    break;
                
                case "D":
                    String timeD = timevalidation();
                    if (dayPlanner.Deletetimeslot(date, timeD)) {
                        System.out.println("**deleted time slot [" + timeD + "]**");
                    }
                    System.out.println("**time slot not present**");
                    ;
                    break;

                case "E":

                    dayPlanner.to_String(datetoday.format(f1));
                    break;
                default:
                    System.out.println("**Invalid Input! Try Again.**");
                    break;
            }
        }
    }

    //what if the month has less or more days
    public static String datevalidation(){
        String date = "";
        int day = 0, month = 0, year = 0;
        while(day < 1 ||  day > 31 || month < 1 || month > 12 || year < 2025){
            System.out.println("\nWhat is the date:-");
            System.out.print("\tday: ");
            day = input.nextInt();
            System.out.print("\tmonth: ");
            month = input.nextInt();
            System.out.print("\tyear: ");
            year = input.nextInt();
            date = String.format("%02d/%02d/%04d", day,  month, year);
            input.nextLine();
            if (day < 1 ||  day > 31 || month < 1 || month > 12 || year < 2025) System.out.println("**Invalid Input! Try Again.**");

        }
        today = LocalDate.of(year, month, day);
        return date;
    }

    public static String timevalidation(){
        int hours = -1, minutes = -1;
        while(hours < 0 || hours > 23 || minutes < 0 || minutes > 59){
            System.out.println("\nwhat is the time(24Hr):- ");
            System.out.print("\tHours:  ");
            hours = input.nextInt();
            System.out.print("\tminutes: ");
            minutes = input.nextInt();
            if (hours < 0 || hours > 23 || minutes < 0 || minutes > 59) System.out.println("**Invalid Input! Try Again.**");
        }
        String time = String.format("%02d:%02d", hours, minutes);  
        return time;
    }

    public static String dataentry(){
        input.nextLine();
        System.out.print("\nEnter data:- \n\t- ");
        return input.nextLine();
    }

    public static days addtoplanner(String date, List<String> time, List<String> data){
        DayOfWeek day = today.getDayOfWeek(); 
        days newDay = new days();
        newDay.setdate(date);
        newDay.setday(day.getDisplayName(TextStyle.FULL, Locale.ENGLISH));
        newDay.setavailability(false);
        newDay.setTime(time);
        newDay.setData(data);
        return newDay;
    }
        

}

    

    