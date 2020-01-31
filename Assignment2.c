/*  

     Name: Jose Franco Baquera
     Course: CS 471 - Programming Language Structures 1 - NMSU
     Instructor: Shaun Cooper 
     Assignment: Programming #2 - Short Circuit Evaluation
     Program/File Name: Assignment2.c
     Date: September 10, 2019

     Assumptions: No assumptions about the program are made. We expect, 
     however, that C will most likely conduct short circuit evaluation. 

     Description/Explanation of Code: This particular program will check if
     the implementation of the C language automatically does short circuit 
     evaluation in the AND and OR Boolean constructs. Short circuit evaluation 
     occurs when the language evaluates the first portion of a Boolean expression
     and skips the evaluation of the second expression by using the result of the 
     first evaluation. For example, A & B is false if A is false, meaning 
     that there is no need to evaluate B. Similarly, A || B is true if A is true, 
     meaning that there is no need to evaluate B. The function "evaluation" will
     therefore, only be called if the programming language at hand DOES NOT
     conduct short circuit evaluation. Furthermore, a dummy variable "i" will be 
     used, in conjunction with a logical statement, to construct the first Boolean 
     expression. The second Boolean expression is therefore the output of the
     "evaluation" function (i.e. in this case always true or 1). 

     Purpose:  The purpose of the program is to allow students to learn 
     if C conducts short circuit evaluation. 

     Program Output: The final evaluation of the Boolean construct (i.e. true or
     false) and whether or not the second Boolean expression is evaluated. 

*/

/* Include all necessary libraries for the program. */

/* The stdio.h (standard input and output) library allows the program to call the "printf" 
    function, which is used to print characters onto the screen.  */
#include <stdio.h>

/* Declaration of function "evaluation." */
int evaluation( );

/* 
   Implementation of function "evaluation."
   Input: None/Void
   Output: Print statement; Returns 1 (i.e. true)
*/
int evaluation( ) {

   /* Print a meaningful message and return 1 (i.e. true). */
   printf( "Yikes - I Have Been Evaluated; Not Very Efficient\n" );
   return( 1 );

} /* end evaluation function */

/* Main function. The program will start running from this particular function.  */
int main( ) {

   /* Declare and assign a value to variable i. */ 
   int i = 1;

   /* Check if short circuit evaluation occurs in an AND statement. */
   if ( i == 0 && evaluation( ) )
      printf("&& - Boolean construct is TRUE\n");
   else 
      printf("&& - Boolean construct is FALSE\n");
   
   /* Check if short circuit evaluation occurs in an OR statement. */
   if ( i == 1 || evaluation( ) )
      printf("|| - Boolean construct is TRUE\n");
   else 
      printf("|| - Boolean construct is FALSE\n");

} /* end main function */

