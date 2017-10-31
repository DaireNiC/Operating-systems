package ie.gmit;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.IntStream;

public class Runner
{
  public Runner() {}
  
  public static void main(String[] args)
  {
    ArrayList<Process> list = new ArrayList();

    enterProcesses(list);
    
    System.out.println("You have entered the following processes");
    display(list);
    
    menu(list);
  }
  
  public static void enterProcesses(ArrayList<Process> list)
  {
    Scanner console = new Scanner(System.in);
    

    System.out.println("Please enter the number of processes you wish to schedule: ");
    int num_process = console.nextInt();
    
    for (int i = 0; i < num_process; i++)
    {
      Process p = new Process();
      

      System.out.println("Enter the process name: ");
      int p_id = console.nextInt();
      p.setP_id(p_id);
      
      System.out.println("Enter the burst time for the Process: ");
      int burst = console.nextInt();
      p.setP_burst(burst);
      
      list.add(p);
    }
  }
  
  public static void display(ArrayList<Process> list)
  {
    System.out.println(list.toString());
  }
  
  public static void menu(ArrayList<Process> list)
  {
    Scanner console = new Scanner(System.in);

    System.out.println("\nPlease enter the number the scheduling algorithm you wish to use: ");
    System.out.println("\nPress 1 for Round Robin \nPress 2 for FCFS \nPress 3 for SJF\nPress 4 to Exit");
    
    int choice = console.nextInt();
    while(choice != 4){
	    switch (choice) {
			case 1: 
				RoundRobin(list);
				break;
			case 2: 
				FCFS(list);
				break;
			case 3: 
				list.sort(null);
				FCFS(list);
				break;
			case 4: 
				System.exit(0);
				break;
	    }
	    System.out.println("\nPlease enter the number the scheduling algorithm you wish to use: ");
	    System.out.println("\nPress 1 for Round Robin \nPress 2 for FCFS \nPress 3 for SJF\nPress 4 to Exit");
	    choice = console.nextInt();
    }//while
   // console.close();
  }
  
  private static void RoundRobin(ArrayList<Process> list) {
	  
	  Scanner console = new Scanner(System.in);
	  
	  //print list
	  System.out.println("=== Round Robin ====");
	  //get user to enter quantum
	  System.out.println("Please enter the quantum: ");
	  int quantum = console.nextInt();
	  
	  // 3 arrays
	  int remaining [] = new int[list.size()];
	  int wait_time [] = new int[list.size()]; 
	  int quantums [] = new int[list.size()];
	  
	  int remainder =0, current_time =0, wait =0, i=0; 
	  Process p =  new Process();
	  //populate the remaining array
	  for( i = 0; i < list.size(); i ++){
		 p = list.get(i); //get the process
		 remaining[i] = p.getP_burst() ; //set remainder values = burst time
	  }
	   
	  
	  //while there are still values to processes in the remaining array
	  while (IntStream.of(remaining).sum() > 0){
		  //for each process in the list
		  if (i < list.size()){
			  
			 if (remaining[i] > 0){ //while still stuff to process
				
				 wait_time[i] = current_time;
				 
				 int temp = remaining[i];
				 
				 remaining[i] = remaining[i] - quantum; //temp var for comparison
				 quantums[i]++; //Increment number of quantum
				 
				 if(remaining[i] < 0){
					 remaining[i] = 0;
				 }
				 
				 //increment current_time by (Q or remainder)
				 if (temp < quantum ){
					 current_time+= temp;
				 }
				 else{
					current_time+= quantum;
				 }
				 
				 //print 
				 System.out.println("\n==== Process " + i + "======");
				 System.out.println("current time" + current_time);
				 System.out.println("remaining array : " + remaining[i]);
				 System.out.println("wait_time array : " + wait_time[i]);
				 System.out.println("num of quantums array : " + quantums[i]);	 
			 }
			 //increment counter
			  i++;
		  }
		  else{
			  //if greater than size of list reset counter
			  i = 0;
		  }
		  
	  }//while  

	//calculate wait time for each process
	  for( i = 0; i <list.size(); i ++){
		  p = list.get(i);
		  wait = wait_time[i] -  (quantums[i] - 1) * quantum;
		  p.setP_wait_time(wait);
		  System.out.println(p.toString());
		}			  
  }//Round Robin
  

  private static void SJF(ArrayList<Process> list)
  {
    list.sort(null);
    display(list);
    FCFS(list);
  }
  

  public static void FCFS(ArrayList<Process> list)
  {
    int wait_time = 0;
    int burst = 0;
    int avg_wait = 0;
    
    for (int i = 0; i < list.size(); i++) {
      wait_time += burst;
      
      ((Process)list.get(i)).setP_wait_time(burst);
      burst = ((Process)list.get(i)).getP_burst();
      System.out.println("wait " + wait_time);
    }
    
    avg_wait = wait_time / list.size();
    System.out.println("Average Wait time: " + wait_time);
  }
}