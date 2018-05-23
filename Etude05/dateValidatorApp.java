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
        String dayOutOfRange = "Invalid day entered: Day out of range.";
        String monthOutOfRange = "Invalid month entered: Month out of range.";
        String yearOutOfRange = "Invalid year entered: Year out of range.";
        String dayIncorrectFormat = "Invalid day entered: Day format incorrect.";
        String monthIncorrectFormat = "Invalid month entered: Month format incorrect.";
        String yearIncorrectFormat = "Invalid year entered: Year format incorrect.";
        int formatErrors;
        validator.createDictionary();
        while(true){
            System.out.println("-----------------------------------------------------------");
            System.out.println("-Please enter a date, or enter 'quit' to exit the program.-");
            System.out.println("-----------------------------------------------------------");
            System.out.println("Input:");
            date = input.nextLine();
            System.out.println();

            date = date.trim();

            if(date.equals("quit")) {
                break;
            }

            year = validator.getYear(date);
            month = validator.getMonth(date);
            day = validator.getDay(date);

            formatErrors = 0;

            if(year == validator.formatError){
                //System.out.println(year);
                formatErrors++;
            }
            if(month == validator.formatError){
                //System.out.println(month);
                formatErrors++;
            }
            if(day == validator.formatError){
                // System.out.println(day);
                formatErrors++;
            }


            if(formatErrors == 0){

                if(year.length() < 2 || year.length() == 3 || year.length() > 4){
                    System.out.println(yearIncorrectFormat);
                    formatErrors++;
                }

                if(month.length() == 0 || month.length() > 3){
                    System.out.println(monthIncorrectFormat);
                    formatErrors++;
                }else if(month.length() > 2 && validator.matchMonthsFormat(month) != true){
                    System.out.println(monthIncorrectFormat);
                    formatErrors++;
                }
                


                if(day.length() == 0 || day.length() > 2){
                    System.out.println(dayIncorrectFormat);
                    formatErrors++;
                }
                }
                if(formatErrors == 0){
                    if(validator.matchLeapYear(year) == true){
                        System.out.println("vaild leapyear");
                        month = month.toUpperCase();
                        if(validator.matchFeb(month) == true){
                            // System.out.println("valid feb");

                            if(validator.matchDaysFebLY(day) == true){
                                //  System.out.println("valid FebLY days");
                                validator.printDate(date);
                            }else{
                                System.out.println(dayOutOfRange);
                            }


                        }else if(validator.matchMonths30(month) == true){
                            //System.out.println("valid 30day month");

                            if(validator.matchDays30(day) == true){
                                //System.out.println("valid 30day");
                                validator.printDate(date);
                            }else{
                                System.out.println(dayOutOfRange);
                            }



                        }else if(validator.matchMonths31(month) == true){
                            // System.out.println("Valid 31day month");

                            if(validator.matchDays31(day) == true){
                                // System.out.println("Valid 31day");
                                validator.printDate(date);
                            }else{
                                System.out.println(dayOutOfRange);
                            }

                        }
                    }else if(validator.matchYear(year) == true){
                        // System.out.println("valid non-leapyear");
                        month = month.toUpperCase();
                        if(validator.matchFeb(month) == true){
                            // System.out.println("valid feb");

                            if(validator.matchDaysFebNLY(day) == true){
                                // System.out.println("valid FebNLY days");
                                validator.printDate(date);
                            }else{
                                System.out.println(dayOutOfRange);
                            }


                        }else if(validator.matchMonths30(month) == true){
                            // System.out.println("valid 30day month");

                            if(validator.matchDays30(day) == true){
                                //     System.out.println("valid 30day");
                                validator.printDate(date);
                            }else{
                                System.out.println(dayOutOfRange);
                            }


                        }else if(validator.matchMonths31(month) == true){
                            //System.out.println("Valid 31day month");

                            if(validator.matchDays31(day) == true){
                                //  System.out.println("Valid 31day");
                                validator.printDate(date);
                            }else{
                                System.out.println(dayOutOfRange);
                            }
                        }else{
                            System.out.println(monthOutOfRange);
                        }
                    }else{
                        System.out.println(yearOutOfRange);
                    }
                }else{
                    System.out.println(validator.formatError);
                }

            }
        }

    }




