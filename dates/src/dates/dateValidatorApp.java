import java.lang.System;
import java.util.Scanner;

public class dateValidatorApp {




    public static void main(String[]args){

        Scanner input = new Scanner(System.in);
        String date = "";
        int trueCount = 0;
        int falseCount= 0;
        dateValidator validator = new dateValidator();

        while(true){

            date = input.nextLine();


            if(date.equals("exit")) {
                break;
            }

            if (validator.matches(date) == true){
                trueCount++;
            }

            if (validator.matches(date) == false){
                falseCount++;
                }
        }
        System.out.println(trueCount+" valid dates entered");
        System.out.println(falseCount+" invalid dates entered");
    }
}

