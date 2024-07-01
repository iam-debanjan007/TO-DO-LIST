import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


class clear{       
  public void cls() { //Method to clear terminal for both Windows & Unix based Os (Linux, MAC OS)
    try {
        if (System.getProperty("os.name").contains("Windows")) {
            new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
          } else {
            System.out.print("\033[H\033[2J");
            System.out.flush();
          }
        } catch (Exception e) {
      }
   }
}

class TaskManager extends clear{

   Scanner sc = new Scanner(System.in);

   //Method of Add Tasks
    public void addTask(){
        cls();
        System.out.println("\n     -------------------------");
        System.out.println("     Enter task Name: ");
        String task = sc.nextLine();
        try {
            File fl = new File("tasks\\"+task);
            if (fl.createNewFile()) {
            System.out.println("     Task created: " + fl.getName());
             } else {
             System.out.println("     Task already exists.");
             System.err.println("Enter Any Key to Go Back");
             sc.nextLine();
             } 

            System.out.println("\n     Enter Task Details");
            String details = sc.nextLine();

            try (FileWriter myWriter = new FileWriter("tasks\\"+task)) {
                myWriter.write(details);
            }
          } catch (IOException e) {
            System.out.println("     An error occurred. \n");
            try {
              Thread.sleep(3000);
            } catch (InterruptedException e1) {
            }
        }
    }

   //Method Of View Tasks
  public void viewTask(){
    cls();
    File path = new File("tasks"); 
    File[] files = path.listFiles();
    
    if (files != null){
      System.out.println("\n     ------List of Tasks------");
      for (File file : files){
         System.out.println("             "+file.getName());
        }
        System.err.println("     -------------------------");
      }else{
         System.out.println("No Tasks Available \n Add some Task first");
         System.err.println("\n Enter Any Key to Go Back");
          sc.nextLine();
      }
    
    System.err.println("\n Enter the Task Name for Task Details : ");
    String stask = sc.nextLine();
    cls();
    System.err.println("\n------------TASK: "+stask+"------------\n");
   
        try (FileInputStream fis = new FileInputStream("tasks\\"+stask)) {
            int f;
            //System.out.println("");
            System.out.print("");
            while ((f=fis.read())!=-1){
                System.err.print((char)f);
            }
            System.out.println("\n");
            System.err.println("------------END OF TASK------------ \n");
            System.err.println("Enter Any Key to Go Back");
            sc.nextLine();
        }catch (Exception e) {
    }
  }

//Method of Delete Tasks
  public void DelTask(){
    cls();
    
    File path = new File("tasks"); 
    File[] files = path.listFiles();
    if (files != null){
      System.out.println("\n     ------List of Tasks------");
      for (File file : files){
         System.out.println("           "+file.getName());
      }
      System.err.println("     -------------------------");
   }else{
    System.out.println("No Tasks Available \n Add some Task first");
   }
    

    System.err.println("\n Enter the Task Name to Delete : ");
    String dtask = sc.nextLine();
    try {
        File fs = new File("tasks\\"+dtask);
        if (fs.delete()){
          System.err.println(fs.getName()+" is Deleted \n ");
          System.err.println("Enter Any Key to Go Back");
          sc.nextLine();
        }else{
          System.out.println(" Deletion Failed \n");
          System.err.println("Enter Any Key to Go Back");
          sc.nextLine();
        }
    } catch (Exception e) {
    }

  }
}


public class TODO{
    public static void main(String[] args) {
        
        int s;
        Scanner sc = new Scanner(System.in);
        (new File("tasks")).mkdir();
        TaskManager TM = new TaskManager();

        while(true){
          TM.cls();
          System.out.println("\n       -------To Do List-------");
          System.out.println("           1 - Add Task \n           2 - View All tasks \n           3 - Delete Task \n           4 - exit ");
          System.out.println("       ------------------------ \n");
          System.err.println("Enter Correct option:");
          s = sc.nextInt();
          switch(s){
            case 1 : TM.addTask();
            break;
            case 2 : TM.viewTask();
            break;
            case 3 : TM.DelTask();
            break;
            case 4 : sc.close();
                     System.exit(0);
            default : System.out.println("Enter correct option \n ");
        }
    }
  }
}


//MADE WITH ❤️ BY DEBANJAN MUKHERJEE :)