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
 * This interface sets the defining characteristics of the priority queue Abstract Data Type.
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 * @param <T> refers to the generic data type of the objects that can be stored in the
 *        WaitngqueueADT. T can only be a data type that implements the Comparable interface
 */
public interface WaitingQueueADT<T extends Comparable<T>> {

  /**
   * Inserts a newObject in the priority queue
   * 
   * @param newObject refers to the new Object of type T that is to be added to the priority queue
   */
  public void enqueue(T newObject);

  /**
   * Removes and returns the item with the highest priority
   * 
   * @return the object with the highest priority in the priority queue
   */
  public T dequeue();

  /**
   * Returns without removing the item with the highest priority
   * 
   * @return the object with the highest priority in the priority queue
   */
  public T peek();

  /**
   * Returns size of the waiting queue
   * 
   * @return the number of elements in the priority queue
   */
  public int size();

  /**
   * Checks if the waiting queue is empty
   * 
   * @return true if the priority queue is empty, false otherwise
   */
  public boolean isEmpty();
}
