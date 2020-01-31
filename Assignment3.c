/* -------------------------------------------------------------------------------
 * Name: Jose Franco Baquera
 * Course: CS 471 - Programming Language Structures 1 at NMSU
 * Instructor: Shaun Cooper 
 * Assignment: Programming #3 - Comparing Interpreted and Compiled Codes
 * Program/File Name: Assignment3.c
 * Date: September 18, 2019
 * 
 * Assumptions: We assume that the user will run this program using the 
 * time UNIX command, such as in the following manner: time ./a.out 250
 * Because of this expectation, the program will have no output, unless there is
 * an error. We expect that the runtimes for this program will be shorter than 
 * the runtimes for the Python program that does not use numpy. 
 * 
 * Description/Explanation of Code: 
 * Argument Checker: The program will first check that the user passed only one
 *  argument through the command line and that the number is greater than 0.
 *  If true, continue, else error. 
 * Declarations:
 *  This particular program will first define a variable (i.e. MATRIX_SIZE) that 
 *  will store the square matrix size. The value assigned to it will be the argument passed.
 *  This variable will be changed for each experiment (i.e. 250, 500, 
 *  1000, 1500, and 2000). The program then declares and allocates three pointers 
 *  (i.e. firstMatrix, secondMatrix, and resultMatrix) that will point to three float 
 *  arrays that are size MATRIX_SIZE^2. In essence, these pointers will "mimic" matrices. 
 *  We then declare three variables (i.e. row, column, and rowXcolumn) that will 
 *  allow us to use for loops. Lastly, the program will declare a temporary dot
 *  product variable (i.e. dotProd) that will allow us to keep track of the dot 
 *  product while we do matrix multiplication.
 * First For Nested Loop:
 *  The first for nested loop will populate two of the matrices (i.e. firstMatrix 
 *  and secondMatrix) with random floating-point numbers that are between 0 and 100.
 * Second For Nested Loop:
 *  The second for nested loop will actually do the matrix multiplication between 
 *  firstMatrix and secondMatrix. This will be done by calculating the dot 
 *  product of rows and columns in an iterative way. The result of the matrix
 *  multiplication will be stored in resultMatrix. 
 * 
 * Purpose: The purpose of the program is to allow students to learn about how 
 * different matrix sizes affect runtimes in the C programming language. 
 * It is worth noting that C is a compiled language. These runtimes 
 * will be observed by using the UNIX time function. Furthermore, the more general 
 * purpose of the assignment is to gather quantitative data in order to compare
 * and contrast interpreted and compiled languages.  
 * 
 * Program Input/Precondition: One integer argument through the command line. 
 * (e.g. ./a.out 250). Integer must be > 0.
 * 
 * Program Output/Postcondition: After the program executes, the result of the 
 * matrix multiplication between firstMatrix and secondMatrix will be stored in
 * resultMatrix. However, the program will have no output, unless there is an error.
------------------------------------------------------------------------------- */

/* Include all necessary libraries and for the program. */
/* The stdlib library allows the program to call the rand() and malloc() 
 * functions. The stdio library will allow us to print an error to the user. 
 */
#include <stdlib.h>
#include <stdio.h>

/* Main function. The program will start running from this particular function.  */
int main(int argc, char *argv[]) {


   /* Declare a variable that will keep track of the square matrix size. */
   int MATRIX_SIZE = 0;

   /* Check that the user inputted a ONE argument. If they did, assign this value to
    * the variable keeping track of the matrix size. If they didn't error and exit. */
   if ( argc == 2 && atoi(argv[1]) > 0 ) 
      MATRIX_SIZE= atoi(argv[1]);
   else {
      printf( "Error: Must provide ONE argument greater than 0.\n"); 
      exit(1);
    } /* end else */

   /* Declare three 2Dimensional matrices. These matrices will "simulate" regular 
    * matrices. Use malloc allocate memory in the heap segment. */
   float *firstMatrix = (float *)malloc(MATRIX_SIZE * MATRIX_SIZE * sizeof(float));
   float *secondMatrix = (float *)malloc(MATRIX_SIZE * MATRIX_SIZE * sizeof(float));
   float *resultMatrix = (float *)malloc(MATRIX_SIZE * MATRIX_SIZE * sizeof(float));           

   /* Declare three variables that will allow us to iterate through the 2Dimensional arrays. */
   int row = 0, column = 0, rowXcolumn = 0;

   /* Declare a temporary variable that will keep track of the dot product while doing the 
    * matrix multiplication. */
   float dotProd = 0;

   /* For loop that will populate the first two matrices with random floating 
    * point numbers between 0 and 100 (inclusive). The outer loop will allow us
    * to go row by row while the inner loop will allow us to go column by column. */
   for( row = 0; row < MATRIX_SIZE; row++ ) {
      for( column = 0; column < MATRIX_SIZE; column++ ) {

         /* Assign a random floating-point number between 0 and 100.00 (inclusive). */
         *(firstMatrix + row*MATRIX_SIZE + column) = ((float)rand()/(float)RAND_MAX) * 100.0;
         *(secondMatrix + row*MATRIX_SIZE + column) = ((float)rand()/(float)RAND_MAX) * 100.0;

      } /* end inner for */     
   } /* end outer for */

   /* For loop that will conduct matrix multiplication between firstMatrix and 
    * secondMatrix. The result will be stored in resultMatrix. */
   for( row = 0; row < MATRIX_SIZE; row++ ) {
      for( column = 0; column < MATRIX_SIZE; column++ ) {
         for( rowXcolumn = 0; rowXcolumn < MATRIX_SIZE; rowXcolumn++ ) {

            /* Compute the dot product between row and column. */
            dotProd = dotProd + *(firstMatrix + row*MATRIX_SIZE + rowXcolumn) * *(secondMatrix + rowXcolumn*MATRIX_SIZE + column);

         } /* end deepest for */

         /* Store the result of the dot product into the result matrix and reset the dotProd variable
          * back to 0. */ 
         *(resultMatrix + row * MATRIX_SIZE + column) = dotProd;
         dotProd = 0;

      } /* end second deepest for */     
   } /* end outer for */

   /* Deallocate the memory allocated with malloc. */

   free(firstMatrix);
   free(secondMatrix);
   free(resultMatrix);

} /* end main function */