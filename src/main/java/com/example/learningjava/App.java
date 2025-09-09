// package com.example.java.learningjava;
// import java.io.*;
// import java.util.*;

// public class App {
//     private String availability;
//     public String date;
//     public String time;
//     public String day = "?????";
//     private String data = "";
//     private planner plannerfile = new planner();
//     private ArrayList<String> todoList = plannerfile.todolist;

//     public days(String date){
//         int index = checkifpresent(date);
//             if(index >= 0){
//                     this.date = date;
//                     this.day = todoList.get(index + 2);
//                     while (!todoList.get(index).equals("\"")) {
//                         if (todoList.get(index).startsWith("*")){
//                             this.data += todoList.get(index).substring(2,todoList.get(index).length()) + "\n";
//                         }
//                         index++;
//                     }
//             }else{
//                     this.date = date;
//                     this.data = "??:??     \tNo entry present.";
//                 }
//     }

//     public days(){
       
//     }

//     public String availability_get(){
//         return this.availability;
//     }

//     public  void availability_set(String availability){
//         this.availability = availability;
//     }

//     public String data_get(){
//         return this.data;
//     }

//     public  void data_set(String data){
//         this.data = data;
//     }

//     private int checkifpresent(String input){
//         for (int i = 0; i < todoList.size(); i++) {
//             if (todoList.get(i).equals(input)) {
//                 return i;
//             }
//         }
//         return -1;
//     }

//     public void addtoplanner(){
//         adddate();
//         if(checkifpresent(date) < 0){
//         adddata();
//         todoList.remove(todoList.size()-1);
//         todoList.add(this.date);
//         todoList.add("00");
//         todoList.add(this.day);
//         todoList.add( "* " + this.data);
//         todoList.add("\"");
//         todoList.add("**END**");
//         plannerfile.addtoplanner();
//         }else{
//             System.out.println("Entry already present.");
//         }
//     }

//     public void adddate(){
//         boolean correctday = false; 
//         Scanner input = new Scanner(System.in);
//         while (!correctday) {
//             System.out.print("\nwhat is the day of the weak.\n\tA: Monday\tB: Tuesday\n\tC: Wednesday\tD: Thursday\n\tE: Friday\tF: Saturday\n\tG: Sunday \n\t:- ");
//             String dayofweek = input.nextLine();  
//             switch (dayofweek.toUpperCase()) {
//                 case "A":
//                     this.day = "Monday";
//                     correctday = true;
//                     break;

//                 case "B":
//                     this.day = "Tuesday";
//                     correctday = true;
//                     break;

//                 case "C":
//                     this.day = "Wednesday";
//                     correctday = true;
//                     break;
            
//                 case "D":
//                     this.day = "Thursday";
//                     correctday = true;
//                     break;
            
//                 case "E":
//                     this.day = "Friday";
//                     correctday = true;
//                     break;
            
//                 case "F":
//                     this.day = "Saturday";
//                     correctday = true;
//                     break;

//                 case "G":
//                     this.day = "Sunday";
//                     correctday = true;
//                     break;

//                 default:
//                     break;
//           } }
        
//         int day = 0, month = 0, year = 0;
//         while(day < 1 ||  day > 31 || month < 1 || month > 12 || year < 2025){
//             System.out.println("\nWhat is the date:-");
//             System.out.print("\tday: ");
//             day = input.nextInt();
//             System.out.print("\tmonth: ");
//             month = input.nextInt();
//             System.out.print("\tyear: ");
//             year = input.nextInt();
//             this.date = String.format("%02d/%02d/%04d", month,  day, year);
            
//         }
//     }

//     public String addtime(){
//         Scanner input = new Scanner(System.in);
//         System.out.println("\nwhat is the time(24Hr):- ");
//         int hours = -1, minutes = -1;
//         while(hours < 0 || hours > 23 || minutes < 0 || minutes > 59){
//             System.out.print("\tHours:  ");
//             hours = input.nextInt();
//             System.out.print("\tminutes: ");
//             minutes = input.nextInt();
//         }
//         String time = String.format("%02d:%02d", hours, minutes);  
//         this.time = time;
//         return time;
//     }

//     public void adddata(){
//         Scanner input = new Scanner(System.in);
//         System.out.print("\nEnter data:- \n\t- ");
//         String data = input.nextLine();
//         this.data = "* " + addtime() + "-\t" + data;
//     }

//    /* public void delete_data(String date){
//         if(checkifpresent(date) > 0){
//             for (int i = todoList.indexOf(date)+ No_OF_ELEMENTS; i > todoList.indexOf(date); i--) {
//                 todoList.remove(i);    
//             }
//         }
//     }*/

//     //add validation for choice options
//     public void edit(String date){
//         int index = checkifpresent(date);
//         String choice = "";
//         while (!choice.toUpperCase().equals("H")) {
//         if (index > 0) {
//             System.out.print("\nwould you like to:- \n\tA: edit existing time stamp\n\tB: Add new time stamp\n\tH: Home\n\t: ");
//             Scanner input = new Scanner(System.in);
//             choice = input.nextLine();
            
//             switch (choice.toUpperCase()) {
//                 case "A":
//                     String time = addtime();
//                     while (!todoList.get(index).equals("\"")) {
//                         System.out.println(todoList.get(index + 3).substring(2,7));
//                         if(todoList.get(index + 3).substring(2,7).equals(time)){
//                             System.out.print("\nEnter new data:- \n\t- ");
//                             String data = input.nextLine();
//                             todoList.set(index + 3, "* " + time + "-\t" + data);
//                             break;
//                         }
//                         index++;
//                     }
//                     plannerfile.addtoplanner();
//                     to_String();
//                     break;
                
//                 case "B":
//                     adddata();
//                     while (!todoList.get(index+3).equals("\"")) {
//                         if(todoList.get(index).startsWith("*") && Integer.parseInt(this.time.replace(":", "").strip()) == Integer.parseInt(todoList.get(index).substring(2,7))){
//                             System.out.println("Time Stamp present");
//                             index++;
//                         } 
//                     }if (todoList.get(index).equals("\"")) {
//                             todoList.add(todoList.lastIndexOf("\"")-1, this.data);
                            
//                         }
//                     plannerfile.addtoplanner();
//                     break;

//                 case "H":
//                     break;
//             }
//         }
//     }
//     }

//     public void to_String(){
//         System.out.println("\n\t** DAY PLANNER **");
//         System.out.printf("%-10s\t%-10s\n", date, day);
//         System.out.println(data_get());
        
//     }
    
// }
