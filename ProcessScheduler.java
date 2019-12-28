/*
 * ////////////////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION////////////////////////////// 
 * // Title: SJF Process Scheduler
 * // Files: WaitingQueueADT.java, CustomProcess.java, CustomProcessQueue.java, 
 *           ProcessSchedulerTests.java, ProcessScheduler.java
 * // Course: CS 300 Fall term 2018 
 * // Author: Vedaant Tambi 
 * // Email: tambi@wisc.edu 
 * // Lecturer's Name: MOUNA AYARI BEN HADJ KACEM 
 * //////////////////////////////////////// CREDIT OUTSIDE HELP ////////////////////////////////////
 * //                                            Piazza                                           //
 * //////////////////////////////////////// 100 COLUMNS WIDE ///////////////////////////////////////
 */

import java.util.Scanner;

/**
 * This class represents the processor that will schedule and run all processes enter by the user.
 * This class will schedule processes using Shortest Job First scheduling
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class ProcessScheduler {

  private int currentTime; // stores the current time after the last run
  private int numProcessesRun; // stores the number of processes run so far
  private CustomProcessQueue queue; // this processing unit's custom process queue

  /**
   * Creates a ProcessScheduler object and initializes the data fields
   * 
   */
  public ProcessScheduler() {
    this.currentTime = 0; // the time before anything is run by the system
    this.numProcessesRun = 0; // no processes have been run yet
    this.queue = new CustomProcessQueue(); // a queue for running the processes is initialized
  }

  /**
   * Retrieves the number of processes run by this scheduler
   * 
   * @return the numProcessesRun data field
   */
  public int getNumProcessesRun() {
    return this.numProcessesRun;
  }

  /**
   * Retrieves the time that has elapsed after certain processes have run
   * 
   * @return the currentTime data field
   */
  public int getCurrentTime() {
    return this.currentTime;
  }

  /**
   * Schedules a process by adding it to the CustomProcessQueue
   * 
   * @param process - is the process that is added to the queue for scheduling
   */
  public void scheduleProcess(CustomProcess process) {
    this.queue.enqueue(process); // the process is added to the queue using enqueue()
  }

  /**
   * This method executes all the processes that have been scheduled in the queue. Each processes is
   * dequeued from the queue based on the burst time of the process. The Custom Process with the
   * least burst time is executed first.
   * 
   * @return a string which has a list of the processes that were executed and the current time at
   *         each stage
   */
  public String run() {
    String s = new String(); // an empty string
    if (queue.size() == 1) // checks if there is only one element in the queue
      // deals with exceptional wording for 1 process
      s += "Starting " + queue.size() + " process\n\n";
    else // all other cases are worded the normal way
      s += "Starting " + queue.size() + " processes\n\n";
    while (!queue.isEmpty()) { // loop runs as long as there are processes to executed
      s += "Time " + this.currentTime + " : Process ID " + this.queue.peek().getProcessId()
          + " Starting.\n"; // string for the start of execution

      this.currentTime += queue.peek().getBurstTime(); // current time is updated

      s += "Time " + this.currentTime + " : Process ID " + this.queue.peek().getProcessId()
          + " Completed.\n"; // string for showing the completion of a process

      this.queue.dequeue(); // the process is removed from the queue for running
      this.numProcessesRun++; // the number of processes run is incremented by one
    }

    // string to signal the completion of all processes
    s += "\nTime " + this.currentTime + ": All scheduled processes completed.\n";
    return s; // the string is returned
  }

  /**
   * This is the driver function of the whole program. This method is responsible for setting up a
   * user interface, calling the methods of the class and displaying the output based on the input
   * given by the user
   * 
   */
  public static void main(String[] args) {
    CustomProcess scheduleProcess; // a Custom Process that will be added to the queue
    Scanner input = new Scanner(System.in); // Scanner object to read user input from the keyboard
    // Process scheduler object for scheduling and running processes
    ProcessScheduler Sch = new ProcessScheduler();
    // Welcome message
    System.out.println("==========   Welcome to the SJF Process Scheduler App   ========\r\n");

    // Set the menu, prompt the user command, error messages, and goodbye strings
    final String menu =
        "\n Enter command: \r\n" + "   [schedule <burstTime>] or [s <burstTime>]\r\n"
            + "   [run] or [r]\r\n" + "   [quit] or [q]\r\n";
    final String errorMsg = "WARNING: Please enter a valid command!\\n";
    final String formatErrMsg1 = "WARNING: burst time MUST be an integer!\\n";
    final String formatErrMsg2 = "WARNING: burst time MUST be greater than 0!\n";

    String chc = new String(); // user choice is stored in a string
    boolean quit = false; // variable to determine if the program is to be quit

    int time = 0; // variable to hold the burst time of the process that will be scheduled
    // do-while loop to run the program as long as the user wants. (Will run at least once)
    do {

      // Display menu and prompts the user
      System.out.println(menu);
      chc = input.next(); // user choice is stored

      // if the user does not enter a valid string command, then an error message is printed
      if (!chc.equals("s") && !chc.equals("schedule") && !chc.equals("q") && !chc.equals("r")
          && !chc.equals("run") && !chc.equals("quit"))
        System.out.println(errorMsg);

      // switch-case block to execute the user's choice
      switch (chc) {

        // for scheduling a process
        case "s":

        case "schedule":

          if (chc.equals("s") || chc.equals("schedule")) {
            if (!input.hasNextInt()) { // checks if the user entered an integer for the burst time
              // if the burst time is not entered as an integer then an error message is thrown
              System.out.println(formatErrMsg1);
              break; // execution moves out of the switch-case after a formatting error
            } else {
              time = input.nextInt(); // user entered-time is stored in a variable
              if (time <= 0) { // if the time is non-positive then a format error message is showed
                System.out.println(formatErrMsg2);
                break; // the control skips rest of the code in the switch block
              }
            }
          }

          scheduleProcess = new CustomProcess(time); // the CustomProcess object is created

          // message showing the burst time of the scheduled process
          System.out.println("Process ID " + scheduleProcess.getProcessId() + " scheduled. Burst "
              + "Time = " + scheduleProcess.getBurstTime() + "\n");
          Sch.scheduleProcess(scheduleProcess); // call to the schedule process method()
          break; // break statement to prevent fall-through

        // for running or executing the scheduled processes
        case "r":
        case "run":
          System.out.println(Sch.run()); // the string returned by the run() method is returned
          break; // break statement to prevent fall-through

        // for quitting the program
        case "q":
        case "quit":

          quit = true; // the value of quit is changed
          // display goodbye message
          System.out.println(Sch.getNumProcessesRun() + " processes run in " + Sch.getCurrentTime()
              + " units of time!\n" + "Thank you for using our scheduler!\n" + "Goodbye!\n");
          break; // break statement to prevent fall-through
      }
    } while (!quit); // program runs until the user wishes to quit

    input.close(); // free the Scanner resource
  }
}


