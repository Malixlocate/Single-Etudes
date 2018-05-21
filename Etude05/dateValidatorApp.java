import java.lang.System;
import java.util.Scanner;

public class dateValidatorApp {




    public static void main(String[]args){

        Scanner input = new Scanner(System.in);
        String date = "";
        String year = "";
        String month = "";
        String day = "";
        dateValidator validator = new dateValidator();
        String dayOutOfRange = "Invalid day entered, day out of range";
        String monthOutOfRange = "Invalid month entered, month out of range";
        String yearOutOfRange = "Invalid year entered, year out of range";
        String dayInvalid = "Invalid day entered, day format is incorrect";

        while(true){

            date = input.nextLine();

            year = validator.getYear(date);
            month = validator.getMonth(date);
            day = validator.getDay(date);

            if(validator.matchLeapYear(year) == true){
                System.out.println("vaild leapyear");



                if(validator.matchFeb(month) == true){
                    System.out.println("valid feb");

                    if(validator.matchDaysFebLY(day) == true){
                        System.out.println("valid FebLY days");
                    }else{
                        System.out.println(dayOutOfRange);
                    }


                }else if(validator.matchMonths30(month) == true){
                    System.out.println("valid 30day month");

                    if(validator.matchDays30(day) == true){
                        System.out.println("valid 30day");
                    }else{
                        if(day > 2){
                         System.out.println(dayInvalid){
                         else{
                        System.out.println(dayOutOfRange);
                        }
                    }


                }else if(validator.matchMonths31(month) == true){
                    System.out.println("Valid 31day month");

                    if(validator.matchDays31(day) == true){
                        System.out.println("Valid 31day");
                    }else{
                        System.out.println(dayOutOfRange);
                    }

                }else{
                    System.out.println(monthOutOfRange);
                }



            }else if(validator.matchYear(year) == true){
                System.out.println("valid non-leapyear");

                if(validator.matchFeb(month) == true){
                    System.out.println("valid feb");

                    if(validator.matchDaysFebNLY(day) == true){
                        System.out.println("valid FebNLY days");
                    }else{
                        System.out.println(dayOutOfRange);
                    }


                }else if(validator.matchMonths30(month) == true){
                    System.out.println("valid 30day month");

                    if(validator.matchDays30(day) == true){
                        System.out.println("valid 30day");
                    }else{
                        System.out.println(dayOutOfRange);
                    }


                }else if(validator.matchMonths31(month) == true){
                    System.out.println("Valid 31day month");

                    if(validator.matchDays31(day) == true){
                        System.out.println("Valid 31day");
                    }else{
                        System.out.println(dayOutOfRange);
                    }


                }else{
                    System.out.println(monthOutOfRange);
                }

                
            }else if(year.equals(validator.seperatorError)){
                System.out.println(validator.seperatorError);
            }

            if(date.equals("quit") || date.equals("q")) {
                break;
            }

            /*

               if (validator.matches(date) == true){
               trueCount++;
               System.out.println("Correct");
               }

               if (validator.matches(date) == false){
               falseCount++;
               System.out.println("Wrong");
               }
               */
        }
        //System.out.println(trueCount+" valid dates entered");
        //System.out.println(falseCount+" invalid dates entered");
    }


}

