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
                System.out.println(line);
            }
            read.close();
        
        } catch(FileNotFoundException e){
            System.out.println("FILE NOT FOUND");
        }
        
        
    }
}
