import java.util.*;

public class practice {
    public static void main(String[] args) {
        String default_date = "08/28/2025";
        boolean run = true;
        Scanner input = new Scanner(System.in);
        days dayplanner = new days(default_date);
        dayplanner.to_String();
        String choice = "";

        while(run){
            System.out.println("\nwould you like to:- ");
            System.out.print(" A: Create New Enrtry.\n B: view specific date.\n E: Exit \n : ");
            choice = input.nextLine();
            switch (choice.toUpperCase()) {
                case "A":
                    dayplanner.addtoplanner();
                    dayplanner.to_String();
                    break;
                
                case "B":
                    System.out.println("\nWhat is the date:-");
                    String date = "";
                    int day = 0, month = 0, year = 0;
                    while(date.isEmpty()){
                        System.out.print("\tday: ");
                        day = input.nextInt();
                        System.out.print("\tmonth: ");
                        month = input.nextInt();
                        System.out.print("\tyear: ");
                        year = input.nextInt();
                        input.nextLine();

                        if (day < 1 ||  day > 31 || month < 1 || month > 12 || year < 2025) {
                            System.out.println("!!Invalid Input.Try Again!!");
                        }else{
                            date = String.format("%02d/%02d/%04d", month,  day, year);
                            days retrive = new days(date);
                            retrive.to_String();
                            
                        }
                    }
                    break;

                case "E":
                    run = false;
                    break;
                
                default:
                    break;

                    
            }
        }
        
}   
}
