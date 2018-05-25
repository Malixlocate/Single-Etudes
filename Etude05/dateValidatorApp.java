import java.lang.System;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class dateValidatorApp {



    public static void main(String[]args){

        String date = "";
        dateValidator validator = new dateValidator();
        validator.createDictionary();
        Scanner input = new Scanner(System.in);
        int count = 0;
            while(input.hasNextLine()){
              //  System.out.println("-----------------------------------------------------------");
              //  System.out.println("-Please enter a date, or enter 'quit' to exit the program.-");
              //  System.out.println("-----------------------------------------------------------");
                date = input.nextLine();
            //    System.out.println("test "+count);
            //    System.out.println("Input:");
            //    System.out.println(date);
             //   System.out.println();

                date = date.trim();

                if(date.equals("quit")) {
                    break;
                }
                validator.validateDate(date);
                count++;
                }

        }
    }




