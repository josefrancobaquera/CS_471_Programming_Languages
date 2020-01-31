 # -------------------------------------------------------------------------------
 # Name: Jose Franco Baquera
 # Course: CS 471 - Programming Language Structures 1 at NMSU
 # Instructor: Shaun Cooper 
 # Assignment: File Conversion to Comma Separate Value
 # Program/File Name: FileConversionCSV.py
 # Date: October 27, 2019
 # 
 # Assumptions: We assume that records will NOT have empty newlines, such
 # as in the following examples:
 # %%%%%
 # 
 # This 
 # record 
 #
 # is not encouraged. 
 # 
 # $$$$$
 # %%%%%
 # 
 # 
 # $$$$$
 # 
 # If such records are inputted, the program will produce ,This,record,,is not allowed.,
 # for the first one while ,Empty Record for the second one. In other words, the program 
 # will NOT ignore empty newlines in records.
 #
 # The program will write commas for empty newlines. An empty record with newlines will be 
 # converted to include commas with an "Empty Record" ending. An empty record with no 
 # newlines will end with simply "Empty Record". 
 # 
 # Records that have ONLY non-empty newlines are encouraged, such as in the following 
 # examples:
 # %%%%%
 # Records that have all
 # lines populated with non-empty strings
 # are encouraged.
 # $$$$$
 # These lines will be ignored, but the next record is encouraged. If an empty record
 # with no newlines is found, then the program will simply write "Empty Record" to the 
 # CSVOutputPython.txt file. 
 # %%%%%
 # $$$$$
 #
 # We also assume that every start of a record (%%%%%) will also end eventually ($$$$$).
 # Records that do not start or end appropriately will result in errors.
 # 
 # Description / Explanation of Code: The program will read an inputted file line-by-line and 
 # will store each line on a list. The program will then iterate through the list and will 
 # call the handleRecord function if a start of a record is found, otherwise the
 # line will be ignored. The handleRecord function will then continue to read through the list 
 # until it sees 5 dollar signs, indicating that the record has finished. The function will 
 # convert the record using the following three rules: 
 #      1) If a line has a comma, then the entire line needs to be double quoted.
 #      2) If a line has a double quote, the double quote needs to be duplicated.
 #      3) It the line starts with white space, or ends in white space, then the 
 #         entire line needs to be quoted.
 # Each line in the list will then be written into the CSVOutputPython.txt file. The function will 
 # return the number of non-empty lines found in the record. The program checks this return 
 # value and will write "Empty Record" if the record was empty. The program will then 
 # continue to read through the entire list using a while loop. 
 # 
 # Purpose: The purpose of this assignment is to learn how to solve real-world CSV problems that 
 # might be assigned to us in our future jobs. That is, learning how to convert records to
 # comma separated values is extremely important in any field. The assignment also allows students 
 # to learn how to convert one algorithm implemented in one programming language into another
 # programming language (i.e. Java and Python).
 # 
 # Program Input / Precondition: A file with records. Each record must have a correct starting and
 # ending. Users can only run the program through the command line. And example on how to use 
 # the program (using the command line) is the following: 
 # python FileConversionCSV.py < Records_Examples.txt 
 # 
 # Program Output / Post-condition: A file named CSVOutputPython.txt with all the records 
 # adhering to the three previously mentioned rules. This file will be placed in the current
 # working directory where the program was run. 
 # ------------------------------------------------------------------------------- 

# Import the required libraries that are needed to complete this assignment. 
import sys

# Global variables that will allow us to determine when a record starts and ends.
startOfRecord = "%%%%%"
endOfRecord = "$$$$$"

# Create and open a file called "CSVOutputPython.txt" in the current working directory
# that will store the manipulated records.
fileWriter = open("CSVOutputPython.txt", "w")

# Get the file lines without the extraneous newline and store them in a list.
listOfLines = [line.rstrip('\n') for line in sys.stdin]

# Function that will handle the found record. 
# Pre-Condition: Lines of text inside a record that must be converted into CSV. These 
# lines must be stored in the original list. The last line MUST be $$$$$. Furthermore, 
# we assume that the first line of the record (e.g. %%%%%) has already been consumed. 
# Post-Condition: Record is written to the file CSVOutputPython.txt in the current 
# working directory and in the appropriate format. 
def handleRecord( indexLeftOff ):

   # Increment the index left off to read the next item on the list.
   indexLeftOff += 1
   tempLineRead = listOfLines[indexLeftOff]
   
   # Variables that will allow us to determine if we need to double quote 
   # the entire line of text and if the record is empty. 
   numberOfNotEmptyLines = 0
   quoteEntireLine = 0
   
   # String variable that will be written to the appropriate file. 
   finalStringPerLine = ""
   
   # While loop that will iterate through the list until the end of the 
   # record is found.
   while tempLineRead != endOfRecord:
          
      # Check if the entire line of text needs to be double quoted. 
      if tempLineRead != "" and ( ',' in tempLineRead or 
                                 tempLineRead[0] == " " or 
                                 tempLineRead[0] == "\t" or 
                                 tempLineRead[len(tempLineRead)-1] == " " or 
                                 tempLineRead[len(tempLineRead)-1] == "\t" ): 
         quoteEntireLine = 1
         
      # Check if there exists a double quote in the line of text. If it does,
      # double it. If it does not, continue normally. 
      if '\"' in tempLineRead:
    
         # For loop that will double all double quotation marks found in the 
         # line of text. 
         for k in tempLineRead:
             
            if k == '\"':
               finalStringPerLine = finalStringPerLine + k + '\"'    
            else:
               finalStringPerLine = finalStringPerLine + k
               
      else: 
         finalStringPerLine = tempLineRead
                 
      # Add the double quotes if entire line needs to be quoted. Reset Boolean variable.
      if quoteEntireLine == 1:
         finalStringPerLine = '\"' + finalStringPerLine + '\"'
         quoteEntireLine = 0 
         
      # Count the number of non-empty lines in the record.   
      if ( finalStringPerLine != "" ):
         fileWriter.write(finalStringPerLine)
         numberOfNotEmptyLines += 1
      
      # Increment the index left off to read the next item on the list.      
      indexLeftOff += 1
      tempLineRead = listOfLines[indexLeftOff]
      
      # If statement that will append the comma, if needed. 
      if tempLineRead != endOfRecord:
         fileWriter.write(",")
         
      # "Reset" String in order to process the next line in record. 
      finalStringPerLine = ""
      
   return numberOfNotEmptyLines
    
# Variable that will allow us to iterate through the list of text lines. 
i = 0

# While loop that will read through the entire list. 
while i < len(listOfLines):
    
   # Check if record has begun. If a start of record is found, call 
   # the corresponding function. If not, ignore the line of text. 
   if( listOfLines[i] == startOfRecord ):
      emptyRecorderChecker = handleRecord( i )
      
      # If record is empty, write "Empty Record" to the file. 
      if emptyRecorderChecker == 0:
         fileWriter.write("Empty Record")
      
      # Write a newline to the file since record has finished being manipulated. 
      fileWriter.write("\n")
      
   # Increment the index in order to process next text line on list. 
   i += 1

# Close the written file.
fileWriter.close()
