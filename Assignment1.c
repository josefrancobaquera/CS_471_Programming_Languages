/*  
     Name: Jose Franco Baquera
     Course: CS 471 - Programming Language Structures 1 - NMSU
     Instructor: Shaun Cooper 
     Assignment: Programming #1 - Simple C Aliasing Problem
     Program/File Name: Assignment1.c
     Date: September 3, 2019
     
     Description: This particular program will use a C pointer to alias the same
     memory address of a particular variable. Even though the pointer will "point" 
     to the same memory address, it will interpret the binary string stored here 
     differently. The program will print memory addresses and their corresponding 
     contents in order to allow the user to understand this simple aliasing "problem." 
     
     Purpose:  The purpose of the program is to allow students to learn how aliasing 
     works in C. Furthermore, the program will prove that computers simply store
     binary strings (i.e. 1's and 0's) and that it is up to the programmer to tell it 
     how to interpret them.
     
     Program Output: Memory addresses, contents in those memory addresses,
     and sizes of specific data types (i.e. integer and float) in bytes. 
*/

/* Include all necessary libraries for the program. */

/* The stdio.h (standard input and output) library allows the program to call the "printf" 
    function, which is used to print characters onto the screen.  */
#include <stdio.h>

/* Main function. The program will start running from this particular function.  */
int main() {

   /* i is an integer; f is a float pointer. */
   int i; 
   float *f; 

   /* Commented out the next line. This was the original value that was assigned to i. */
   // i=1092616192; 
   
   i=1097859072; /* Explanation on how I got this number can be found 
                                in the homework assignment document. */

   f=(float *)&i; /* The '&' character is used to get the memory address of where variable 
                                i is stored. The '(float *)' portion is used to type cast. */

   /* Print the memory address of where variable i is stored and the actual value of f both in unsigned
        long format. These two numbers should be the same value. */
   printf("i variable's address: %lu --- f \"points\" to the address: %lu\n",&i,&f);
   
   /* Print the actual value of i (as a signed decimal) and the contents found in the memory address 
        that f points to (as a floating-point number). The * before f "dereferences" f. */
   printf("i has value %d --- f has value %f\n",i,*f);

   /* Print the size in bytes of both the integer and float data types in signed decimal format. */
   printf("int length in bytes: %d --- float length in bytes: %d\n",sizeof(int), sizeof(float));

} /* end main function */
