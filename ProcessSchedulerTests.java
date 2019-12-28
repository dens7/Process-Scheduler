/**
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

/**
 * This class is the testing class of the SJF Process Scheduler Program. All test methods are
 * defined and called here.
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class ProcessSchedulerTests {

  /**
   * This method is responsible for 'filling' or enqueue of CustomProcess objects of random burst
   * times into a CustomProcessQueue priority queue.
   * 
   * @param queue is the empty CustomProcessQueue that needs to be filled with CustomProcess objects
   */
  private static void queueFiller(CustomProcessQueue queue) {

    // Insertion of 11 CustomProcess objects in the min-heap array
    queue.enqueue(new CustomProcess(26));
    queue.enqueue(new CustomProcess(19));
    queue.enqueue(new CustomProcess(31));
    queue.enqueue(new CustomProcess(44));
    queue.enqueue(new CustomProcess(10));
    queue.enqueue(new CustomProcess(33));
    queue.enqueue(new CustomProcess(27));
    queue.enqueue(new CustomProcess(14));
    queue.enqueue(new CustomProcess(35));
    queue.enqueue(new CustomProcess(42));
    queue.enqueue(new CustomProcess(19)); // Duplicate element
  }

  /**
   * This method test the getProcessId method of the CustomProcess class by making sure that the
   * right ProcessIds are being returned
   * 
   * @return true if method shows correct functionality, false otherwise
   */
  public static boolean getProcessIdTest() {
    CustomProcessQueue queueTest1 = new CustomProcessQueue(); // Empty queue
    queueFiller(queueTest1); // queue enqueued with 11 elements
    /*
     * This array stores the Process IDs of the objects in the same order as they are stored in the
     * min-heap array. This array will be used to check the getProcessId method
     */
    int arrProcessID[] = {0, 5, 8, 7, 2, 11, 6, 3, 4, 9, 10, 1};
    // for-loop that checks the processIds returned by getProcessId method of every object stored in
    // the array
    for (int i = 1; i <= 11; i++) {
      if (arrProcessID[i] != queueTest1.getArrayQueue()[i].getProcessId())
        return false; // if an incorrect Process ID is returned, then the test is unsuccessful
    }
    return true; // if all Process IDs are correct then the test is successful
  }

  /**
   * This method checks the correctness of the enqueue operation implemented in the 
   * CustomProcessQueue class
   * 
   * @return true if method shows correct functionality, false otherwise
   */
  public static boolean testEnqueueCustomProcessQueue() {
    CustomProcessQueue queueTest2 = new CustomProcessQueue(); // An empty queue object is created
    queueFiller(queueTest2); // the queue is filled with Random objects
    
    /*
     * This array stores the burst times of the objects in the same order as the objects are stored 
     * in the min-heap array. This array will be used to check whether the enqueue method stores 
     * the objects without violating the min-heap properties
     */
    int arrBurstTime[] = {0, 10, 14, 27, 19, 19, 33, 31, 44, 35, 42, 26};
    // for-loop that checks the burst times returned by each element of the array against the 
    // correct burst times
    for (int i = 1; i <= 11; i++) {
      if (arrBurstTime[i] != queueTest2.getArrayQueue()[i].getBurstTime())
        return false; // if an incorrect burst time is returned, then the test is unsuccessful
    }
    return true; // if all Process IDs are correct then the test is successful
  }

  /**
   * This method checks the correctness of the dequeue operation implemented in the 
   * CustomProcessQueue class
   * 
   * @return true if method shows correct functionality, false otherwise
   */
  public static boolean testDequeueCustomProcessQueue() {
    CustomProcessQueue queueTest3 = new CustomProcessQueue();
    queueFiller(queueTest3);
    /*
     * This array stores the burst times of the objects in the same order as the objects are stored 
     * in the min-heap array. This array will be used to check whether the dequeue method returns
     * the object with the highest priority. 
     */
    int arrPriorityOrder[] = {0, 10, 14, 19, 19, 26, 27, 31, 33, 35, 42, 44};
    // for-loop that checks the burst times returned by each element of the array against the 
    // correct burst times
    for (int i = 1; i <= 11; i++) {
      if (arrPriorityOrder[i] != queueTest3.dequeue().getBurstTime())
        return false; // if an incorrect Object is dequeued, then the test is unsuccessful
    }
    return true; // if all Process IDs are correct then the test is successful
  }

  /**
   * This test method checks the run method of the ProcessScheduler class by making sure that the 
   * correct output is given to the user 
   * 
   * @return true if method shows correct functionality, false otherwise
   */
  public static boolean runTest() {
    // This string is expected when 2 customProcess objects are scheduled and subsequently run
    String test = new String("Starting 2 processes\n\nTime 0 : Process ID 34 Starting.\nTime 4 : "
        + "Process ID 34 Completed.\nTime 4 : Process ID 35 Starting.\nTime 71 : Process ID 35 "
        + "Completed.\n\nTime 71: All scheduled processes completed.\n");
    ProcessScheduler Scheduler = new ProcessScheduler(); // A ProcessScheduler is created
    // two new CustomProcess objects are added 
    Scheduler.scheduleProcess(new CustomProcess(4));  
    Scheduler.scheduleProcess(new CustomProcess(67));
    
    // checks whether the run method returns the correct string by checking against string 'test'
    return Scheduler.run().equals(test); // true is returned if the test is successful
  }

  /**
   * The main method is responsible for calling the test methods and displaying the results of the 
   * tests to the user
   * 
   * @param args for taking any string arguments
   */
  public static void main(String[] args) {
    //Tests are called and displayed
    
    System.out.println("Results: \ngetProcessIdTest(): " + getProcessIdTest());
    System.out.println("testEnqueueCustomProcessQueue(): " + testEnqueueCustomProcessQueue());
    System.out.println("testDequeueCustomProcessQueue(): " + testDequeueCustomProcessQueue());
    System.out.println("runTest(): " + runTest());
  }

}
