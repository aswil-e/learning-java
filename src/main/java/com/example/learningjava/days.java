package com.example.learningjava;
import java.util.*;

public class days {
    private boolean availability;
    private String date;
    private String day;
    private List<String> time;
    private List<String> data;

    public days(){
       
    }

    public String getdate(){
        return date;
    }

    public  void setdate(String date){
        this.date = date;
    }
    
    public boolean isavailability(){
        return availability;
    }

    public  void setavailability(boolean availability){
        this.availability = availability;
    }

    public String getday(){
        return day;
    }

    public  void setday(String day){
        this.day = day;
    }

    public List<String> gettime(){
        return time;
    }

    public  void setTime(List<String> time){
        this.time = time;
    }

    public List<String> getData(){
        return data;
    }

    public  void setData(List<String> data){
        this.data = data;
    }

    /*

    public void delete_data(String date){
        if(checkifpresent(date) > 0){
            for (int i = todoList.indexOf(date)+ No_OF_ELEMENTS; i > todoList.indexOf(date); i--) {
                todoList.remove(i);    
            }
        }
    }

    //add validation for choice options
    public void edit(String date){
        int index = checkifpresent(date);
        String choice = "";
        while (!choice.toUpperCase().equals("H")) {
        if (index > 0) {
            System.out.print("\nwould you like to:- \n\tA: edit existing time stamp\n\tB: Add new time stamp\n\tH: Home\n\t: ");
            Scanner input = new Scanner(System.in);
            choice = input.nextLine();
            
            switch (choice.toUpperCase()) {
                case "A":
                    String time = addtime();
                    while (!todoList.get(index).equals("\"")) {
                        System.out.println(todoList.get(index + 3).substring(2,7));
                        if(todoList.get(index + 3).substring(2,7).equals(time)){
                            System.out.print("\nEnter new data:- \n\t- ");
                            String data = input.nextLine();
                            todoList.set(index + 3, "* " + time + "-\t" + data);
                            break;
                        }
                        index++;
                    }
                    plannerfile.addtoplanner();
                    to_String();
                    break;
                
                case "B":
                    adddata();
                    while (!todoList.get(index+3).equals("\"")) {
                        if(todoList.get(index).startsWith("*") && Integer.parseInt(this.time.replace(":", "").strip()) == Integer.parseInt(todoList.get(index).substring(2,7))){
                            System.out.println("Time Stamp present");
                            index++;
                        } 
                    }if (todoList.get(index).equals("\"")) {
                            todoList.add(todoList.lastIndexOf("\"")-1, this.data);
                            
                        }
                    plannerfile.addtoplanner();
                    break;

                case "H":
                    break;
            }
        }
    }
    }*/

    
    
}
