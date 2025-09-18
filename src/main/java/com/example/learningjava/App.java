package com.example.learningjava;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import javax.xml.crypto.Data;

public class App {
    static LocalDate today;
    public static void main(String[] args) {
        List<days> todoList;
        int index;
        LocalDate datetoday = LocalDate.now();
        DateTimeFormatter f1 = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        planner dayPlanner = new planner(datetoday.format(f1));
        boolean run = true;
        String choice;
        Scanner input = new Scanner(System.in);
        dayPlanner.to_String(datetoday.format(f1));

        while (run) {
            System.out.println("\nwould you like to:- ");
            System.out.print(" A: Create New Enrtry.\n B: view specific date.\n E: Exit \n : ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    String dateA = datevalidation();
                    List<String> time = new ArrayList<String>(), data = new ArrayList<String>(); 
                    time.add(timevalidation());
                    data.add(dataentry());
                    dayPlanner.addtoplanner(addtoplanner(dateA, time, data));
                    dayPlanner.to_String(dateA);
                    break;
                
                case "B":
                    String dateB = datevalidation();
                    dayPlanner.to_String(dateB);
                    break;

                case "E":
                    run = false;
                    break;
                
                default:
                    break;

                    
            }}
        }
    //add validation errorstatement
    public static String datevalidation(){
        Scanner input = new Scanner(System.in);
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
            date = String.format("%02d/%02d/%04d", month,  day, year);
            input.nextLine();
        }
        today = LocalDate.of(year, month, day);
        return date;
    }

    //add error statement
    public static String timevalidation(){
        Scanner input = new Scanner(System.in);
        int hours = -1, minutes = -1;
        while(hours < 0 || hours > 23 || minutes < 0 || minutes > 59){
            System.out.println("\nwhat is the time(24Hr):- ");
            System.out.print("\tHours:  ");
            hours = input.nextInt();
            System.out.print("\tminutes: ");
            minutes = input.nextInt();
        }
        String time = String.format("%02d:%02d", hours, minutes);  
        return time;
    }

    public static String dataentry(){
        Scanner input = new Scanner(System.in);
        System.out.print("\nEnter data:- \n\t- ");
        String data = input.nextLine();
        return data;
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

    

    