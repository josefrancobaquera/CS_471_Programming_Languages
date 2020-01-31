# -------------------------------------------------------------------------------
# Name: Jose Franco Baquera
# Course: CS 471 - Programming Language Structures 1 at NMSU
# Instructor: Shaun Cooper 
# Assignment: Programming #3 - Comparing Interpreted and Compiled Codes
# Program/File Name: Assignment3Numpy.py
# Date: September 18, 2019
#
# Assumptions: We assume that the user will run this program using the 
# time UNIX command, such as in the following manner: time python Assignment3Numpy.py 250
# Because of this expectation, the program will have no output, unless to error. 
# Furthermore, the program does use the numpy module. We, therefore, expect that the 
# runtimes for this program will be shorter than the runtimes for the program 
# that does use not numpy.
#
# Description/Explanation of Code: 
# Argument Checker: The program will first check that the user passed only one
#   argument through the command line and that the number is greater than 0.
#   If true, continue, else error. 
# Declarations:
#   This particular program will first define a variable (i.e. matrixSize) that will 
#   store the square matrix size. The value assigned to it will be the argument passed.
#   This variable will be changed for each experiment (i.e. 250, 500, 
#   1000, 1500, and 2000). The program then declares and populates two matrices 
#   (i.e. firstMatrix and secondMatrix) with random floating-point 
#   numbers between 0 and 100.
# Matrix Multiplication:
#   We will use the "dot" method provided by nunmpy to do matrix multiplication. 
#   The result will be stored in resultMatrix
#
# Purpose: The purpose of the program is to allow students to learn about how 
# different matrix sizes affect runtimes in Python with using the numpy module. 
# It is worth noting that Python is an interpreted language. These runtimes 
# will be observed by using the UNIX time function. Furthermore, the more general 
# purpose of the assignment is to gather quantitative data in order to compare
# and contrast interpreted and compiled languages.  
#
# Program Input/Precondition: One integer argument through the command line. 
# (e.g. python Assignment3Numpy.py 250). Integer must be > 0.
#
# Program Output/Postcondition: After the program executes, the result of the 
# matrix multiplication between firstMatrix and secondMatrix will be stored in
# resultMatrix. However, the program will have no output, unless there is an error.  
# -------------------------------------------------------------------------------

# Import the "numpy" module that will allow us to produce random numbers and 
# perform matrix multiplication. Import the "sys" module that will allow us to get 
# the parameters passed by users.
import sys as SYSTEM
import numpy as NP

# Check that the user inputted ONE argument. If they did, assign this value to
# the variable keeping track of the matrix size. If they didn't, error and exit. */
if len(SYSTEM.argv) == 2 and int(SYSTEM.argv[1]) > 0:
   matrixSize= int(SYSTEM.argv[1])
else:
   SYSTEM.exit('Error: Must provide ONE argument greater than 0.')

# Populate firstMatrix and secondMatrix with random floating-point numbers
# between 0 and 100. 
firstMatrix = NP.random.rand(matrixSize, matrixSize) * 100
secondMatrix = NP.random.rand(matrixSize, matrixSize) * 100

# Use the "dot" method provided by numpy to do matrix multiplication and
# store the result matrix in resultMatrix. 
resultMatrix = firstMatrix.dot(secondMatrix)