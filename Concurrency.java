/* -------------------------------------------------------------------------------
 * Name: Jose Franco Baquera
 * Course: CS 471 - Programming Language Structures 1 at NMSU
 * Instructor: Shaun Cooper 
 * Assignment: Concurrency
 * Program/File Name: Concurrency.java
 * Date: November 15, 2019
 * 
 * Assumptions - We assume that the user will only input an integer between 1 and 31 (inclusive). 
 * The program will output an error and exit if a user inputs a non-integer or an integer outside 
 * this range. More specifically, the input cannot be less than 1 since we cannot have an 
 * NxN two-dimensional matrix were N < 1. Furthermore, the input cannot be greater than 31 since
 * the upper and lower bounds of random numbers would be 2 ^ (negative exponent), which include 
 * floating-point numbers. For example, if we allowed the user to input 32, then the upper limit 
 * would be 2^(31 - 32) = 1/2. This makes little sense since we are populating the random matrix 
 * with integers only and not floating-point numbers. 
 * 
 * Description / Explanation of Code - Two static ArrayList instances will be declared and initialized. 
 * This is where the newly created Threads and ThreadRow instances will be added. Furthermore, two static 
 * variables will be created in order to help us connect to a common block in memory. The main function 
 * will begin by initializing a set number of variables that will allow us to accomplish our goal. The main 
 * function will then check user input. If the user input is within bounds and appropriate, then the matrix 
 * will be initialized using this input. This two-dimensional matrix will then be populated with random 
 * integers within the appropriate range. A total of N threads will be created, started, and added to 
 * their corresponding ArrayList instance. Each thread that is started will find the minimum, maximum, and 
 * "average" of its corresponding row. We note that this "average" is not the typical calculated average but 
 * rather part of the overall calculation. For example, suppose that we have the following list of 
 * numbers: 1 2 3 4. The average of this list would be (1+2+3+4)/4. However, mathematically speaking, this is
 * equal to (1+2)/4 + (3+4)/4. Such mathematical implication is used to calculate the overall average value 
 * of the matrix. The main function will loop until all Thread instances finish executing and will find 
 * the minimum in all the minimums and the maximum in all the maximums. It will also add all the averages 
 * calculated by all threads. The main function will end after printing out to the screen the minimum, maximum, 
 * and average values in the entire matrix. The time it took to complete these calculations will also be 
 * printed in nanoseconds.
 * 
 * Purpose - The purpose of this assignment is to learn how to write a simple concurrent program in JAVA. 
 * Knowing how to write concurrent programs is becoming a very important skill in the real-world due to the 
 * explosion of multi-threaded, multi-core processors. 
 * 
 * Program Input / Precondition - An integer between 1 and 31. Any integer outside this range and any 
 * non-integer will produce an error. Furthermore, zero arguments and more than one argument passed will 
 * also produce an error. 
 *
 * Program Output / Post-condition - The minimum, maximum, and average values of the matrix, as well as
 * the time (in nanoseconds) it took the threads and the main function to find these values. 
 *
 * References - The code was influenced by the following websites:
 * http://www.letmeknows.com/2017/04/24/wait-for-threads-to-finish-java/
 * https://www.techiedelight.com/measure-elapsed-time-execution-time-java/
------------------------------------------------------------------------------- */
 
// Import the required libraries to run the program.
import java.util.ArrayList;
import java.util.Random;

// Concurrency class implementation.
public class Concurrency {
    
   // Use two static ArrayList objects that will store all created Threads
   // and ThreadRow instances. These objects will allow us to access ThreadRow
   // members after they finish executing. 
   private static ArrayList<Thread> arrThreads = new ArrayList<Thread>();
   private static ArrayList<ThreadRow> arrThreadsRow = new ArrayList<ThreadRow>();

   // Static variables that will help us connect threads to a common block.
   public static int N = 0;
   public static int [][] randomMatrix;
      
   // Main function. The program will start running from here.  
   public static void main(String[] args) {
   
      // Variables that will allow us to generate random integers on a given 
      // interval.
      int upperBound = 0;
      int lowerBound = 0;
      Random rand = new Random();
      
      // Variables that will allow us to find the matrix's minimum, maximum, 
      // and average.
      int maximum = Integer.MIN_VALUE; 
      int minimum = Integer.MAX_VALUE;
      float average = 0;
      
      // Variables that will allow us to find the time it took to find the 
      // minimum, maximum, and average values.
      // NOTE: Cooper allowed us to use the LONG primitive type to calculate 
      // elapsed time. 
      long startTimer = 0, endTimer = 0, totalTime = 0; 
        
      try {
        
         // Check user input. Error if no arguments are passed or if more 
         // than one argument is passed.  
         if ( args.length == 0 )
            throw new IllegalArgumentException("ERROR: No arguments. Please pass only ONE argument.");
      
         else if ( args.length > 1 )
            throw new IllegalArgumentException("ERROR: More than one argument passed. Please pass only ONE argument.");
            
         // Parse user input into an integer.  
         N = Integer.parseInt(args[0]);

         // Check that input is inside the bounds. We note that we cannot have a matrix
         // size less than 1 and that 32 was not chosen as the upper limit since 2^(31 - 32)
         // would yield 1/2. However, this number would never be produced since we 
         // are populating the matrix with integers only. More information about this check
         // can be found in the assumptions section of the header.
         if( N < 1 || N > 31)
           throw new IllegalArgumentException("ERROR: Integer out of bounds. Please enter an integer between 1 and 31 (inclusive) as an argument.");
            
         // Initialize the NxN, two dimensional matrix.
         randomMatrix = new int[N][N];
         
         // Find the bounds for the random numbers.
         upperBound = (int) Math.pow(2, 32 - N);
         lowerBound = (int) Math.pow(2, 31 - N);
         
         // Populate the matrix with random integers given the bounds.
         populateMatrix(lowerBound, upperBound, rand);
               
         // Start recording the time before doing any calculations.
         startTimer = System.nanoTime();
         
         // For loop that will create N threads. Each thread will 
         // calculate a specific row's min, max, and "average". That is,
         // each thread will work on a different row of the matrix.
         for (int row = 0; row < N; row++) {
         
            // Create new ThreadRow and Thread instances for each row and start 
            // running the threads as they are created. The new ThreadRow instance 
            // will take in a single row as its parameter.
            ThreadRow tempThreadRow = new ThreadRow(row);
            Thread newThread = new Thread(tempThreadRow);
            newThread.start();
            
            // Add all instances to their corresponding ArrayList objects in order 
            // to access them later. 
            arrThreadsRow.add(tempThreadRow);
            arrThreads.add(newThread);
                
         } // end for loop 
         
         // This for loop will not stop executing any of the threads and will only 
         // come out when all threads have finished executing.  
         for (int row = 0; row < N; row++) {
         
            // Wait until the specified thread finishes execution.
            arrThreads.get(row).join();
            
         } // end for 
         
         // This for loop will calculate the minimum, maximum, and average of the random matrix.   
         for (int row = 0; row < N; row++) {
            
            // Begin finding the the minimum, maximum, and average of the entire matrix.
            // That is, check if a new minimum or maximum is found for every executed thread. 
            // We note that we use the arrThreadsRow variable and not the arrThreads variable 
            // since this is where all the created instances of the ThreadRow class are being stored. 
            minimum = Math.min(minimum, arrThreadsRow.get(row).getRowMin());
            maximum = Math.max(maximum, arrThreadsRow.get(row).getRowMax());
            average = average + arrThreadsRow.get(row).getAverage();
            
         } // end for 
         
         // Record the end time after calculations are finished. Calculate the elapsed time.
         endTimer = System.nanoTime();
         totalTime = endTimer - startTimer;
         
         // Print out the minimum, maximum, and average values of the matrix.
         System.out.println("The maximum value in the random matrix is: " + maximum);
         System.out.println("The minimum value in the random matrix is: " + minimum);
         System.out.printf("The average of all of the values in the random matrix is: %.9f", average);
         System.out.println();
         System.out.println("The program took a total of " + totalTime + " nanoseconds to execute.");
         System.out.println("Exiting main thread.");
             
      } // end try 
        
      // Catch error if user inputs a non-integer.
      catch (NumberFormatException e) {
      
         System.out.print("ERROR: ");
         System.out.println(e.getMessage());
         System.out.println("Only integers are allowed. Try again!");
         
      } // end catch
        
      // Catch error if no arguments are passed or if more than one argument is passed.
      catch ( IllegalArgumentException e ) {
        
         System.out.println(e.getMessage());

      } // end catch
        
      // Catch more generic errors. 
      catch ( InterruptedException e ) {
      
         System.out.println(e.getMessage());

      } // end catch
        
      catch ( Exception e ) {
        
         System.out.println(e.getMessage());
        
      } // end catch
        
   } // end main
   
   // Method that will populated the matrix with random integer numbers given bounds.
   // Precondition: Appropriate lower and upper bounds, as well as a Random object. 
   // Postcondition: Matrix populated with random integers given the bounds. 
   private static void populateMatrix ( int tempLowerBound, int tempUpperBound, Random tempRand ) {
   
      // Fill the matrix with random integers between 2^(32 - N) and 2^(31 - N) (inclusive). 
      for (int row = 0; row < N; row++) { 
         for (int column = 0; column < N; column++) {
         
            randomMatrix[row][column] = tempRand.nextInt((tempUpperBound - tempLowerBound) + 1) + tempLowerBound;
               
         } // end inner for loop

      } // end outer for loop
      
   } // end populateMatrix function
    
} // end Concurrency class

// ThreadRow class implementation.
class ThreadRow implements Runnable {

   // Variables that will keep track of which row a Thread instance 
   // is working on and the maximum, minimum, and "average" of that specific
   // row. 
   private int row;
   private int rowMin;
   private int rowMax;
   private float average;
   
   // Constructor of ThreadRow.
   public ThreadRow(int tempRow) {
   
      // Initialize the members of the object.
      row = tempRow;
      rowMin = Integer.MAX_VALUE;
      rowMax = Integer.MIN_VALUE;
      average = 0;
      
   } // end constructor
   
   // This function will be run when the start() function is called.
   public void run() {
   
      // Temporary variable that keeps track of the sum of all elements in
      // the row.
      int tempSum = 0;
   
      try {
      
         // For loop that will find the minimum, maximum, and sum of all 
         // elements in the specified row.
         for (int column = 0; column < Concurrency.N; column++) {
      
            rowMin = Math.min(rowMin, Concurrency.randomMatrix[row][column]);
            rowMax = Math.max(rowMax, Concurrency.randomMatrix[row][column]);
            tempSum = tempSum + Concurrency.randomMatrix[row][column];

         } // end for
            
         // Calculate the "average" of the given row.
         average = ((float)tempSum)/(Concurrency.N * Concurrency.N);
 
      } // end try
      
      // Print error message if exception is caught.
      catch (Exception e) {
            
         System.out.println(e.getMessage());
 
      } // end catch   

   } // end run method
   
   public int getRowMin( ) {
   
      return rowMin;   
      
   } // end accessor for rowMin.
   
   public int getRowMax( ) {
   
      return rowMax;
      
   } // end accessor for rowMax.
   
   public float getAverage( ) {
   
      return average;
      
   } // end accessor for average.

} // end ThreadRow class.
