import java.io.*;
import java.util.*;

public class practice {
    public static void main(String[] args) {
        System.out.println("**DAY PLANNER**");
        planner_visual();
    }    

    public static String planner_visual(){
        String output = "";
        try{
            FileReader file = new FileReader("todolist.txt");
            Scanner read = new Scanner(file);
            String line = read.nextLine();
            do{
                line = read.nextLine();
                if(line.equals("xx"))return "No plans today";
                output += line;
            }while (!line.equals("\""));
            read.close();
        
        } catch(FileNotFoundException e){
            return "FILE NOT FOUND";
        }
        
        return output;
    }
}
