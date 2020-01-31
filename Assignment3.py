# -------------------------------------------------------------------------------
# Name: Jose Franco Baquera
# Course: CS 471 - Programming Language Structures 1 at NMSU
# Instructor: Shaun Cooper 
# Assignment: Programming #3 - Comparing Interpreted and Compiled Codes
# Program/File Name: Assignment3.py
# Date: September 18, 2019
#
# Assumptions: We assume that the user will run this program using the 
# time UNIX command, such as in the following manner: time python Assignment3.py 250
# Because of this expectation, the program will have no output, unless to error. 
# Furthermore, the program does not use the numpy module. We, therefore, expect that the 
# runtimes for this program will be longer than the runtimes for the program 
# that does use numpy, and even longer than the runtimes for the compiled C 
# program. 
#     
# Description/Explanation of Code: 
# Argument Checker: The program will first check that the user passed only one
#   argument through the command line and that the number is greater than 0.
#   If true, continue, else error. 
# Declarations:
#   This particular program will first define a variable (i.e. matrixSize) that will 
#   store the square matrix size. The value assigned to it will be the argument passed.
#   This variable will be changed for each experiment (i.e. 250, 500, 
#   1000, 1500, and 2000). The program then declares a temporary variable 
#   (i.e. dotProd) to 0. This variable will allow us to store the dot product of 
#   a row and column while we do matrix multiplication. Lastly, the program 
#   will declare three 2D dimensional arrays (i.e. firstMatrix, secondMatrix, 
#   and resultMatrix) that will "mimic" square matrices. These matrices will
#   be populated with zeros. 
# First For Nested Loop:
#   The first for nested loop will populate two of the matrices (i.e. firstMatrix
#   and secondMatrix) with random floating-point numbers that are between 0 and 100.
# Second For Nested Loop:
#   The second for nested loop will actually do the matrix multiplication between 
#   firstMatrix and secondMatrix. This will be done by calculating the dot 
#   product of rows and columns in an iterative way. The result of the matrix
#   multiplication will be stored in the 2D array resultMatrix. 
#
# Purpose: The purpose of the program is to allow students to learn about how 
# different matrix sizes affect runtimes in Python without using numpy module. 
# It is worth noting that Python is an interpreted language. These runtimes 
# will be observed by using the UNIX time function. Furthermore, the more general 
# purpose of the assignment is to gather quantitative data in order to compare
# and contrast interpreted and compiled languages.  
#
# Program Input/Precondition: One integer argument through the command line. 
# (e.g. python Assignment3.py 250). Integer must be > 0.
#
# Program Output/Postcondition: After the program executes, the result of the 
# matrix multiplication between firstMatrix and secondMatrix will be stored in
# resultMatrix. However, the program will have no output, unless there is an error.  
# -------------------------------------------------------------------------------

# Import the "random" module that will allow us to produce random numbers.
# Import the "sys" module that will allow us to get the parameters passed 
# by users.
import random as RAND 
import sys as SYSTEM

# Check that the user inputted ONE argument. If they did, assign this value to
# the variable keeping track of the matrix size. If they didn't, error and exit. */
if len(SYSTEM.argv) == 2 and int(SYSTEM.argv[1]) > 0:
   matrixSize= int(SYSTEM.argv[1])
else:
   SYSTEM.exit('Error: Must provide ONE argument greater than 0.')

# Declare a variable that will allow us to do the dot product between a row and column. 
dotProd = 0

# Declare three 2D arrays using the matrixSize variable. The "[0]*matrixSize" 
# section declares an array of size matrixSize. The "for i in range(matrixSize)" 
# section will create a total number of rows of size matrixSize. We note that
# these three matrices will "mimic" square matrices and that they will be
# populated with all zeros. 
firstMatrix = [[0]*matrixSize for i in range(matrixSize) ]
secondMatrix = [[0]*matrixSize for i in range(matrixSize)]
resultMatrix = [[0]*matrixSize for i in range(matrixSize)]

# For loop that will populate the first two matrices with random floating 
# point numbers between 0 and 100 (inclusive). The outer loop will allow us
# to go row by row while the inner loop will allow us to go column by column.
for row in range(matrixSize):
   for column in range(matrixSize):

         # Assign a random floating-point number between 0 and 100.00 (inclusive). 
         firstMatrix[row][column] = RAND.uniform(0, 100) 
         secondMatrix[row][column] = RAND.uniform(0, 100) 

# For loop that will conduct matrix multiplication between firstMatrix and 
# secondMatrix. The result will be stored in resultMatrix.
for row in range(matrixSize):
   for column in range(matrixSize):
      for rowXcolumn in range(matrixSize):

         # Compute the dot product between row and column. 
         dotProd = dotProd + firstMatrix[row][rowXcolumn]*secondMatrix[rowXcolumn][column]

      # Store the result of the dot product into the result matrix and reset the dotProd variable
      # back to 0. 
      resultMatrix[row][column] = dotProd
      dotProd = 0