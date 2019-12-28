/**
 * ////////////////////////////// ALL ASSIGNMENTS INCLUDE THIS SECTION//////////////////////////////
 * // Title: SJF Process Scheduler // Files: WaitingQueueADT.java, CustomProcess.java,
 * CustomProcessQueue.java, ProcessSchedulerTests.java, ProcessScheduler.java // Course: CS 300 Fall
 * term 2018 // Author: Vedaant Tambi // Email: tambi@wisc.edu // Lecturer's Name: MOUNA AYARI BEN
 * HADJ KACEM //////////////////////////////////////// CREDIT OUTSIDE HELP
 * //////////////////////////////////// // Piazza // //////////////////////////////////////// 100
 * COLUMNS WIDE ///////////////////////////////////////
 */

/**
 * This class represents a CustomProcess priority queue, where CustomProcess objects are group
 * together. Each queue is organized as a minHeap array of CustomProcess objects.
 * 
 * @author Vedaant Tambi
 * @version 1.0
 * @since 1.0
 */
public class CustomProcessQueue implements WaitingQueueADT<CustomProcess> {
  private static final int INITIAL_CAPACITY = 1; // the initial capacity of the heap
  private CustomProcess[] heap; // array-based min-heap storing the data. This is an over size array
  private int size; // number of CustomProcesses present in this CustomProcessQueue

  /**
   * Creates an CustomProcessqueue object and initializes the other data fields of the object
   * 
   */
  public CustomProcessQueue() {

    /*
     * the size of the array size is 1 more than the initial capacity because the 0 indexed element
     * is not supposed to be used for storage of a CustomProcess object
     */
    heap = new CustomProcess[INITIAL_CAPACITY + 1];
    heap[0] = null; // the first position will be null and storage will start from index 1
    size = 0; // no elements are present in the queue yet
  }

  /**
   * This is a helper method for swapping the position of 2 elements in an array
   * 
   * @param array is the array in which the swap is happena
   * @param idx - is the index of the first element
   * @param paridx - is the index (parent index) of the second index
   */
  public void swap(CustomProcess array[], int idx, int paridx) {
    CustomProcess temp; // a temporary CustomProcess object used for swapping
    temp = array[idx]; // the first element is stored temporarily
    array[idx] = array[paridx]; // the 2nd element is stored in the 1st element's position
    array[paridx] = temp; // the 2nd element's original position is occupied by the 1st element now
  }

  /**
   * This helper method for enqueue moves the specific element up in the min-heap based on the
   * element's priority in the heap
   * 
   * @param index - is the array index of the element that is to be percolated up the array min-heap
   */
  private void minHeapPercolateUp(int index) {
    if (index == 1) { // loop to check all indices up till the root of the min-heap array
      return;
    }

    int parentIndex = (index / 2); // frmula for calculating the parent index of a child in heap

    /*
     * This compares the index element's priority with it's parent's priority. If the compareTo
     * method returns a positive number, then the child has lower priority than its parent and
     * percolation stops
     */
    if (heap[index].compareTo(heap[parentIndex]) > 0)
      return; // percolation is completed and control returns to the caller method
    else { // if the child has higher priority than the parent then the child percolates up

      // percolation happens by swapping the parent's and child's position
      swap(heap, index, parentIndex); // swapping of positions using the swap() helper
      minHeapPercolateUp(parentIndex); // updates index for running next loop for further
                                       // percolation
    }

  }

  /**
   * This helper method for dequeue moves the specific element down in the min-heap based on the
   * elements priority in the heap
   * 
   * @param index - is the array index of the element that is percolated down the array min-heap
   */
  private void minHeapPercolateDown(int index) {
    int childIndex = 2 * index; // formula for calculating the left child's index of a given node
    CustomProcess value = heap[index]; // the index element is stored temporarily
    // to check the index against all indices till the deepest node is encountered
    if (index > this.size()) {
      return;
    }
    // For finding the max among the node and all the node's children
    CustomProcess minValue = value; // the minimum value is assumed to be the parent element
    int minIndex = -1;
    // this loop runs twice or till the deepest leaf is encountered in the min-heap
    for (int i = 0; i < 2 && i + childIndex <= this.size(); i++) {
      // priority is compared to find the object with higher priority
      if (minValue.compareTo(heap[i + childIndex]) > 0) {
        // the object with the highest priority, out of the parent and its children, is stored
        // in the variables for value and index
        minValue = heap[i + childIndex];
        minIndex = i + childIndex;
      }
    }

    // if the parent index has the higher priority then percolation stops
    if (minValue.compareTo(value) == 0) {
      return; // after percolation stops control is returned to the caller function
    } else { // if the parent has lower priority then it percolates down the min-heap array

      // call to swap the parent and the child with the higher priority
      swap(heap, index, minIndex);
      minHeapPercolateDown(minIndex);// the index is updated for the next iteration of the loop
      // the parent is updated for the next iteration of the loop
    }
  }



  /**
   * Getter for the heap[] data field
   * 
   * @return the heap[] array of the object
   */
  public CustomProcess[] getArrayQueue() {
    return this.heap;
  }

  /**
   * Inserts a CustomProcess object in the priority queue implemented as min-heap array
   * 
   * @param newObject - refers to the CustomProcess object that is to be added to the priority queue
   */
  public void enqueue(CustomProcess newObject) {
    // if-block is used to increase the size of the min-heap array when the array becomes full
    if (size() == heap.length - 1) { // checks whether the array is full or not
      CustomProcess temp[] = this.heap; // a temporary array to store the heap array's elements
      this.heap = new CustomProcess[2 * temp.length]; // heap array is recreated with doubled size
      for (int i = 0; i < temp.length; i++) // for loop to copy back the objects stored in the temp
        this.heap[i] = temp[i]; // array back into recreated heap[] array
    }
    this.size++; // the number of objects increases by one
    this.heap[this.size] = newObject; // the new process is inserted as the rightmost deepest node
    minHeapPercolateUp(this.size); // the newly inserted process is percolated to the right position
  }

  /**
   * Removes and returns the CustomProcess with the highest priority in the priority queue
   * implemented as a min-heap array
   * 
   * @return the CustomProcess with the highest priority in the priority queue
   */
  public CustomProcess dequeue() {
    if (isEmpty()) // checks whether any elements are present in the priority queue array
      return null; // execution is returned to caller function
    CustomProcess temp = heap[1]; // the process at index 1 is always the one with highest priority

    /*
     * The deepest right-most node (the element with the least priority in the queue) is placed at
     * the top of the min-heap. This node is chosen to replace the removed node because it is the
     * only replacement that does not violate the shape property of min-heaps
     */
    heap[1] = heap[size];
    heap[size] = null; // the position of the deepest right most node is vacant now
    this.size--; // since one element has been deleted, the size decreases by one
    minHeapPercolateDown(1); // The replacement node is percolated down to its appropriate position
    return temp; // the removed process is returned
  }

  /**
   * Returns without removing the process with the highest priority
   * 
   * @return the CustomProcess object with the highest priority in the priority queue
   */
  public CustomProcess peek() { // the process with the highest priority is always stored as the
    return this.heap[1]; // first object of the array
  }

  /**
   * Returns size of the priority queue implemented as a min-heap based array
   * 
   * @return the number of CustomProcess objects in the priority queue array
   */
  public int size() {
    return size;
  }

  /**
   * Checks if the priority queue is empty
   * 
   * @return true if the priority queue is empty, false otherwise
   */
  public boolean isEmpty() {
    return size() == 0; // checks whether the size is 0 and returns the result
  }

}
