/* -------------------------------------------------------------------------------
 * Name: Jose Franco Baquera
 * Course: CS 471 - Programming Language Structures 1 at NMSU
 * Instructor: Shaun Cooper 
 * Assignment: Chapter 5 Programming Assignment
 * Program/File Name: Assignment5.c
 * Date: October 4, 2019
 * 
 * Assumptions: There are no assumptions for this program. We do, however, expect that 
 * global variables are stored in the data segment while local variables are stored 
 * in the run-time stack segment.
 * 
 * Description/Explanation of Code: The program first declares a global (static) variable x. 
 * The value of the global variable is then printed in the main function before the first
 * assignment statement. This global variable is then assigned a value of 21 and printed again. 
 * After the second print statement, the program declares a new local variable with the same
 * "name" as the global variable (i.e. x). The value of this local variable is then printed.
 * Lastly, the program will assign a value of 42 to the local variable and print it. 
 * 
 * Purpose: The purpose of the program is to allow students to learn how static scoping works 
 * using global and local variables. The program's output will demonstrate a "search process" 
 * that occurs when trying to use a variable. That is, the program will first search declarations 
 * locally, then in increasingly larger enclosing scopes until one is found for the given name. 
 * This search process continues until the variable name is found and errors otherwise (i.e. the 
 * program will search up to global variables). Lastly, the programming assignment will also show 
 * that you can have multiple variables named "x" declared in different scopes. That is, the L-value 
 * of the variable "x" will change depending on which scope you are trying to access it. 
 * 
 * Program Input/Precondition: None.
 * 
 * Program Output/Post-condition: Prints the value of "x" at different locations within the program.
------------------------------------------------------------------------------- */

/* Include all necessary libraries for the program. */

/* The stdio library will allow the program to print. */
#include <stdio.h>

/* Declare a global variable x. */
int x;

/* Main function. The program will start running from this particular function. */
int main( int argc, char *argv[ ] ) {
    
   /* Print the value of global variable x before we assign it a different value. */
   printf( "x is %d before the first assignment statement inside main - Language C\n", x );

   /* Assign the value 21 to the global variable and print the value of it. */
   x = 21;
   printf( "x is %d after the first assignment statement inside main - Language C\n", x );

   /* Declare a new local variable x and print the value of it. */ 
   int x;
   printf( "x is %d after declaring a new variable x inside main - Language C\n", x );
   
   /* Assign the value 42 to the local variable and print the value of it. */
   x = 42;
   printf( "x is %d after the second assignment statement inside main - Language C\n", x );

} /* end main function */
