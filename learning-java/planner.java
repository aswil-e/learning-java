import java.io.*;
import java.util.*;

public class planner {
    public ArrayList<String> todolist = new ArrayList<>();
    private String filename = "todolist.txt";

    public planner(){
        try{
            BufferedReader reader = new BufferedReader(new FileReader(filename));
            String line;
            while ((line = reader.readLine()) != null) {
                todolist.add(line);
            }
            reader.close();
        }catch(IOException e){
            System.out.println("planner could not readfile");
        }
    }

    public void addtoplanner(){
        try{
            BufferedWriter writer = new BufferedWriter(new FileWriter(filename, false));
            for (String l : todolist) {
                System.out.println(l);
                writer.write(l);
                writer.newLine();
            }
            writer.close();
        }catch(IOException e){
            System.out.println("error: " + e.getMessage());
        }
    }

    public ArrayList troubleshoot(){
        return todolist;
    }


}
