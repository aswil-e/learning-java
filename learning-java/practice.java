import java.io.*;
import java.util.*;

public class practice {
    public static void main(String[] args) {
        System.out.println("**DAY PLANNER**");
        planner_visual();
    }    

    public static void planner_visual(){
        try{
            FileReader file = new FileReader("todolist.txt");
            Scanner read = new Scanner(file);
            String line = ""; 
            while (!line.equals("\"")){
                line = read.nextLine();
                if(line.trim().equals("xx")){
                    System.out.println("No plans today");
                    break;
                }
                if(line.endsWith("#")){
                    System.out.println(line);
                }
            }
            read.close();
        
        } catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }
    }

    public static void addtoplanner(){
        try{
            FileWriter file = new FileWriter("todolist.txt");
            Scanner input = new Scanner(System.in);
            String day = "", data = "";
            int hours = 0, minutes = 0;
            Boolean correctdata = false;

            while (!correctdata) {
                System.out.print("Day of the weak: ");
                day += input.nextLine();
                switch (day.toLowerCase().strip()) {
                    case "monday":
                        file.write("Monday\n");
                        correctdata = true;
                        break;
                    case "tuesday":
                        file.write("Tuesday\n");
                        correctdata = true;
                        break;
                    case "wednesday":
                        file.write("Wednesday\n");
                        correctdata = true;
                        break;
                    case "thursday":
                        file.write("Thursday\n");
                        correctdata = true;
                        break;
                    case "friday":
                        file.write("Friday\n");
                        correctdata = true;
                        break;
                    case "saturday":
                        file.write("Saturday\n");
                        correctdata = true;
                        break;
                    case "sunday":
                        file.write("Sunday\n");
                        correctdata = true;
                        break;
                    default:
                        System.out.println("invalid information. Try again");
                    
                }
            }
            
            System.out.println("\nTime:-");
            System.out.print("Hour(24HR system): ");
            hours = input.nextInt();
            while (hours < 0 || hours > 23) {
                System.out.println("invalid input");
                hours = input.nextInt();
            }
            
            System.out.print("minutes: ");
            minutes = input.nextInt();
            while (minutes < 0 || minutes > 59) {
                System.out.println("invalid input");
                minutes = input.nextInt();
            }

            data += String.format("%02d", hours);
            data += ":" + String.format("%02d", minutes);
            file.write(data);
            file.close();
            input.close();
        }catch(IOException e) {
            System.out.println("An error occurred.");
        }
    }
}
