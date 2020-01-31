 # -------------------------------------------------------------------------------
 # Name: Jose Franco Baquera
 # Course: CS 471 - Programming Language Structures 1 at NMSU
 # Instructor: Shaun Cooper 
 # Assignment: Exception Handling
 # Program/File Name: ExceptionHandling.rb
 # Date: November 3, 2019
 # 
 # Assumptions: No clear assumptions about users and input are made. The program will
 # handle non-integer input by continuing/not exiting and will warn users if they 
 # entered a grade outside the boundaries. We do assume, however, that the user will 
 # exit the program by entering a negative integer only. 
 # 
 # Description / Explanation of Code: The program will declare an array of size 10. 
 # This array will be used to keep track of grade frequencies entered by a user. 
 # Boundaries for frequencies are 0-9, 10-19, 20-29, 30-39, 40-49, 50-59, 
 # 60-69, 70-79, 80-89, and 90-100. The program will use a while loop that will 
 # iterate until a user inputs a negative integer. Only negative integers 
 # will cause the program to exit correctly. A negative floating point 
 # number will, for example, be ignored by the program. Once user input is read, 
 # the program will check if it's an empty String. If it is, it will use a "next" 
 # to get another input. If it's not, it will then check if it is an integer. If the 
 # input is not an integer, a ArgumentError exception is thrown. The program will then print 
 # an error message but continue to get another input. If it is an integer, the program will 
 # then check if the input is negative. If the input is negative, the program 
 # throws a RangeError exception and exits the while loop. If it is not negative, 
 # the program throws an IndexError exception and handles user input. When handling 
 # the IndexError exception, the program will use math to assign the entered grade to 
 # the correct array slot. If the grade is greater than 100, a meaningful message 
 # will be displayed but the program will not quit. The program will print the 
 # frequencies of each grade inputted only when the user inputs a negative integer. 
 # 
 # Purpose: The purpose of this assignment is to learn how to implement an 
 # interrupt-driven program. These types of programs are important since, 
 # in the real world, we do not want our programs to end abruptly when an 
 # exception is thrown. Therefore, we should always handle exceptions in order 
 # to make our software more reliable and safer.
 # 
 # Program Input / Precondition: A user will input integer grade values between 0 and 
 # 100 (inclusive) and will enter a negative integer value to exist. All other
 # inputs such as floats, strings, and integer values grater than 100 will cause 
 # errors that will be handled appropriately. 
 # 
 # Program Output / Post-condition: Frequencies of each grade inputted by the user. 
 # ------------------------------------------------------------------------------- 

# Cooper Rocks!

# Declare an array of length 10 that will store the frequencies of grades inputted and 
# a variable that will store the values inputted by a user. 
freq = Array.new(10, 0)
new_grade = 0

# Integer variables that will allow us to 1) Print in the correct format 2) Determine
# which index an inputted value falls in. 
index = 0
limit_1 = 0
limit_2 = 0

# While loop that will iterate until the user enters a negative integer value.
while 1 
    
   # Get user input.
   print "Enter a Grade Between 0 and 100 (Enter a Negative Integer to Exit): "
   new_grade = gets.strip()
   
   # If user enters an empty String, continue to loop.
   if new_grade == "" 
      next
   end
   
   # Begin a "try and catch". 
   begin

      # Check that user inputted an integer.
      new_grade = Integer(new_grade) 
       
      # If user inputted a negative number, invoke an RangeError 
      # exception. 
      if new_grade < 0
        raise RangeError
      end
      
      # Invoke an IndexError exception. 
      raise IndexError
    
   # If a user inputs anything that is not an integer, print an error message 
   # and continue while loop. 
   rescue ArgumentError
      puts "Error Handler: Oops! You entered a non-integer. Please try again."
      next
      
   # If a user inputs a negative number, break from while loop.     
   rescue RangeError
      puts "Error Handler: Inputted negative number. Exiting while loop."
      break
      
   # Handle user input appropriately.
   rescue IndexError
       
      puts "Error Handler: IndexError Exception - Handling user input."
      
      # Get the corresponding index of the grade inputted. We note that indexes 
      # start at 0 in Ruby.
      index = new_grade/10
       
      # This if statement will execute only if a user inputted a number 
      # greater than 100. Print error message but continue while loop. 
      if new_grade > 100
         puts "Error Handler: New grade " + new_grade.to_s + " is out of range."

      # Special case that will take care of grade 100.
      elsif new_grade == 100
         freq[9] = freq[9] + 1
         
      # If score is less than 100, index is correct.
      else
         freq[index] = freq[index] + 1
         
      end

   end
    
end

# Print header. 
puts "-- Limits --       -- Frequency --"

# Print the frequencies of grades inputted using a for loop.
for index in Range.new(0, freq.size - 1)

   # Use math to get lower and upper bounds. 
   limit_1 = 10 * index
   limit_2 = limit_1 + 9
   
   # Special case when upper limit is 100 and not 99. 
   if index == 9
      limit_2 = 100
   end 
   
   # Print in columns. 
   printf "%-9s %-16s %-9s\n", limit_1.to_s, limit_2.to_s, freq[index].to_s
   
end
   
