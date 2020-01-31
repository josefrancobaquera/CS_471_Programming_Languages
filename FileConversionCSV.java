/* -------------------------------------------------------------------------------
 * Name: Jose Franco Baquera
 * Course: CS 471 - Programming Language Structures 1 at NMSU
 * Instructor: Shaun Cooper 
 * Assignment: File Conversion to Comma Separate Value
 * Program/File Name: FileConversionCSV.java
 * Date: October 27, 2019
 * 
 * Assumptions: We assume that records will NOT have empty newlines, such
 * as in the following examples:
 * %%%%%
 * 
 * This 
 * record 
 *
 * is not encouraged. 
 * 
 * $$$$$
 * %%%%%
 * 
 * 
 * $$$$$
 * 
 * If such records are inputted, the program will produce ,This,record,,is not allowed.,
 * for the first one while ,Empty Record for the second one. In other words, the program 
 * will NOT ignore empty newlines in records.
 *
 * The program will write commas for empty newlines. An empty record with newlines will be 
 * converted to include commas with an "Empty Record" ending. An empty record with no 
 * newlines will end with simply "Empty Record". 
 * 
 * Records that have ONLY non-empty newlines are encouraged, such as in the following 
 * examples:
 * %%%%%
 * Records that have all
 * lines populated with non-empty strings
 * are encouraged.
 * $$$$$
 * These lines will be ignored, but the next record is encouraged. If an empty record
 * with no newlines is found, then the program will simply write "Empty Record" to the 
 * CSVOutputJava.txt file. 
 * %%%%%
 * $$$$$
 *
 * We also assume that every start of a record (%%%%%) will also end eventually ($$$$$).
 * Records that do not start or end appropriately will result in errors.
 * 
 * Description / Explanation of Code: The main function will read an inputted file line-by-line.
 * The program will call the handleRecord function if a start of a record is found, otherwise the
 * line will be ignored. The handleRecord function will then read each line of the record 
 * until it sees 5 dollar signs, indicating that the record has finished. The function will 
 * convert the record using the following three rules: 
 *      1) If a line has a comma, then the entire line needs to be double quoted.
 *      2) If a line has a double quote, the double quote needs to be duplicated.
 *      3) It the line starts with white space, or ends in white space, then the 
 *         entire line needs to be quoted.
 * Each line will then be written into the CSVOutputJava.txt file. The function will return the 
 * number of non-empty lines found in the record. The main function checks this return value
 * and will write "Empty Record" if the record was empty. The main function will then continue to 
 * read the entire file using a while loop. 
 * 
 * Purpose: The purpose of this assignment is to learn how to solve real-world CSV problems that 
 * might be assigned to us in our future jobs. That is, learning how to convert records to
 * comma separated values is extremely important in any field. The assignment also allows students 
 * to learn how to convert one algorithm implemented in one programming language into another
 * programming language (i.e. Java and Python).
 * 
 * Program Input / Precondition: A file with records. Each record must have a correct starting and
 * ending. Users MUST first compile the program (i.e. javac FileConversionCSV.java) and can only 
 * run the program through the command line. And example on how to use the program (using 
 * the command line) after it is compiled is the following: 
 * java FileConversionCSV < Records_Examples.txt
 * 
 * Program Output / Post-condition: A file named CSVOutputJava.txt with all the records 
 * adhering to the three previously mentioned rules. This file will be placed in the current
 * working directory where the program was run. 
------------------------------------------------------------------------------- */

/* Import the required libraries that are needed to complete this assignment. */
import java.util.Scanner;  
import java.io.*;

/* Declare class FileConversionCSV. */
public class FileConversionCSV {

   /* Final variables that will allow us to determine when a record starts and ends. */
   static final String startOfRecord = "%%%%%";
   static final String endOfRecord = "$$$$$";
   
   /* Function that will handle the found record. 
      Pre-Condition: Lines of text inside a record that must be converted into CSV. The last line 
      MUST be $$$$$. Furthermore, we assume that the first line of the record (e.g. %%%%%) has already 
      been consumed. 
      Post-Condition: Record is written to the file CSVOutputJava.txt in the current working directory
      and in the appropriate format. */
   public static int handleRecord( Scanner tempFileToConvert, BufferedWriter tempBufferWriter ) {
      
      /* String variables that will keep track of each line read from the file and that
         will be written to the appropriate file. */
      String tempLineRead;
      String finalStringPerLine = "";
      
      /* Variables that will allow us to determine if we need to double quote the entire line of text
         and if the record is empty. */
      boolean quoteEntireLine = false;
      int numberOfNotEmptyLines = 0;
      
      tempLineRead = tempFileToConvert.nextLine();
      
      /* While loop that will iterate until the end of the record is found. */
      while ( !tempLineRead.equals(endOfRecord) ) {
      
         /* Check if the entire line of text needs to be double quoted. */
         if( (!tempLineRead.equals("")) && 
             (tempLineRead.contains(",") || 
             (tempLineRead.charAt(0) == ' ') ||
             (tempLineRead.charAt(0) == '\t') || 
             (tempLineRead.charAt(tempLineRead.length() - 1) == ' ') ||
             (tempLineRead.charAt(tempLineRead.length() - 1) == '\t') )){
            
            quoteEntireLine = true;
            
         } /* end if */ 
         
         /* Check if there exists a double quote in the line of text. If it does,
            double it. If it does not, continue normally. */
         if( tempLineRead.contains("\"") ) {
         
            /* For loop that will double all double quotation marks found in the line of text. */
            for( int i = 0; i < tempLineRead.length(); i++ ) {
            
               if( tempLineRead.charAt(i) == '\"' )
                  finalStringPerLine = finalStringPerLine + tempLineRead.charAt(i) + "\"";
               else 
                  finalStringPerLine = finalStringPerLine + tempLineRead.charAt(i);
               
            } /* end for */
            
         } /* end if */
         
         else {
         
            finalStringPerLine = tempLineRead;
         
         } /* end else */
         
         /* Add the double quotes if entire line needs to be quoted. Reset Boolean variable.*/
         if( quoteEntireLine ) {
         
            finalStringPerLine = "\"" + finalStringPerLine + "\"";
            quoteEntireLine= false;
            
         } /* end if */
         
         /* Try and catch used to write to a file. */ 
         try {
         
            /* Count the number of non-empty lines in the record. */
            if ( !finalStringPerLine.equals("") ) {
            
               /* Write the modified line of the record to the file. */
               tempBufferWriter.write(finalStringPerLine);
               numberOfNotEmptyLines = numberOfNotEmptyLines + 1;
               
            } /* end if */               
         
            /* Read the next line of the found record. */
            tempLineRead = tempFileToConvert.nextLine();
         
            /* If statement that will append the comma, if needed. */
            if( !tempLineRead.equals(endOfRecord) ) {
         
               tempBufferWriter.write(","); 
               
            } /* end if */
         
         } /* end try */
         
         /* Catch error if cannot write to file. */
         catch (IOException ex) {
         
            System.out.println ("ERROR Writing to File");
            System.exit(1);
         
         } /* end catch */
         
         /* "Reset" String in order to process the next line in record. */  
         finalStringPerLine = "";
         
      } /* end while loop */
      
      return numberOfNotEmptyLines;
      
   } /* end handleRecord function */
   
   /* Main function. The program will start running from this particular function. */
   public static void main ( String args [ ] ) {
   
      /* Get the file that the user inputted through the command line. */
      Scanner fileToConvert = new Scanner ( System.in );
      
      /* Declare variables that will allow us to write into a file. */
      FileWriter fileWriter;
      BufferedWriter bufferedWriter;

      /* String variable that will keep track of each line read from the file. */
      String lineRead = "";
      
      /* Variable that will check if the record is empty. */
      int emptyRecorderChecker = 0; 

      /* Try and catch used to write to a file. */
      try {
      
         /* Create a file called CSVOutputJava.txt in the current working directory 
            that will store the manipulated records. */
         fileWriter = new FileWriter( System.getProperty("user.dir") + "/CSVOutputJava.txt", false );
         bufferedWriter = new BufferedWriter( fileWriter);
        
         /* While loop that will read through the entire text file. */
         while ( fileToConvert.hasNextLine()  ) {
      
            /* Read a line from the text file. */
            lineRead = fileToConvert.nextLine();
         
            /* Check if record has begun. If a start of record is found, call 
               the corresponding function. If not, ignore the line of text. */
            if( lineRead.equals(startOfRecord) ) {
            
               emptyRecorderChecker = handleRecord( fileToConvert, bufferedWriter );
               
               /* If record is empty, write "Empty Record" to the file. */
               if ( emptyRecorderChecker == 0 )
               
                  bufferedWriter.write("Empty Record");
               
               /* Write a newline to the file since record has finished being manipulated. */
               bufferedWriter.write("\n");
         
            } /* end if */
   
         } /* end while loop */
         
         /* Close used Scanner, BufferedWriter, and FileWriter. */
         bufferedWriter.close();
         fileToConvert.close();
         fileWriter.close();
      
      } /* end try */
      
      /* Catch error if cannot write to file. */
      catch (IOException ex) {
      
         System.out.println ("ERROR Writing to File");
         System.exit(1);
         
      } /* end catch */
   
   } /* end main */

} /* end class */
