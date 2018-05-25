import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.lang.System;

class dateValidator{

    //mmmmmmmmmmmmmmm badly written regex
    private static Pattern days31 = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2\\d|30|31)");

    private static Pattern months31 = Pattern.compile
        ("((?!0|2|4|6|9)\\d|0(?!0|2|4|6|9)\\d|10|12|JAN|MAR|MAY|JUL|AUG|OCT|DEC)");

    private static Pattern days30 = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2\\d|30)");

    private static Pattern months30 = Pattern.compile
        ("((4|6|9|)|0(4|6|9)|11|APR|JUN|SEP|NOV)");

    private static Pattern daysFebNLY = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2(?!9)\\d)");

    private static Pattern daysFebLY = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2\\d)");

    private static Pattern feb = Pattern.compile
        ("(2|02|FEB)");

    private static Pattern years = Pattern.compile 
        ("(((175[3-9])|(17[6-9][0-9])|(18|19|2[0-9])[0-9]{2})|3000)|(0\\d|\\d\\d)");

    private static Pattern leapYears = Pattern.compile
        ("(2000|2400|2800|3000|((18|19|2[0-9])(0[48]|[2468][048]|[13579][26])|(17([68][048]|(56|[79][26])))))"); 

    private static Pattern monthsFormat = Pattern.compile
        ("(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|NOV|OCT|DEC|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Nov|Oct|Dec|jan|feb|mar|may|jun|jul|aug|sep|nov|oct|dec)");

    private static Pattern stringNumeric = Pattern.compile("-?\\d+(\\.\\d+)?");

    //Error messages!
    private static String inputError = "Input entered is invalid: Please enter a date.";
    private static String formatError = "Seperator error.";

    private static String dayOutOfRange = "Invalid day entered: Day out of range.";
    private static String monthOutOfRange = "Invalid month entered: Month out of range.";
    private static String yearOutOfRange = "Invalid year entered: Year out of range.";

    private static String dayIncorrectFormat = "Invalid day entered: Day format incorrect.";
    private static String monthIncorrectFormat = "Invalid month entered: Month format incorrect.";
    private static String yearIncorrectFormat = "Invalid year entered: Year format incorrect.";

    //Leap year flag
    private int leapYear = 0;

    private static Map<String, String> dictionary = new HashMap<String, String>();
    /**
     * Constructs a dictionary of all the dates
     */
    public void createDictionary(){
        dictionary.put("01", "Jan"); dictionary.put("02", "Feb"); dictionary.put("03", "Mar");
        dictionary.put("04", "Apr"); dictionary.put("05", "May"); dictionary.put("06", "Jun");
        dictionary.put("07", "Jul"); dictionary.put("08", "Aug"); dictionary.put("09", "Sep");
        dictionary.put("10", "Nov"); dictionary.put("11", "Oct"); dictionary.put("12", "Dec");
    }
    
    //I had a good reason for doing these next three methods seperatly, but I left it on the bus.

    /**
     *Seperates the year from the date.
     *@param date
     *@return year
     */
    public String getYear(String date){
        String year = "";
        int count = 0;

        if(date.contains ("/")){
            year = date.substring(date.indexOf("/")+1);
            year = year.substring(year.indexOf("/")+1);
        }else if(date.contains("-")){
            year = date.substring(date.indexOf("-")+1);
            year = year.substring(year.indexOf("-")+1);
        }else if(date.contains(" ")){
            year = date.substring(date.indexOf(" ")+1);
            year = year.substring(year.indexOf(" ")+1);
        }else{
            year = inputError;
        }
        return year;
    }

    /**
     * Sperates the month from the date.
     * @param date
     * @return month
     */
    public String getMonth(String date){
        String month = "";
        
        if(date.contains ("/")){
            month = date.substring(date.indexOf("/")+1);
            try{
                month = month.substring(0, month.indexOf("/"));
            }catch(StringIndexOutOfBoundsException e){
                month = inputError;
            }
        }else if(date.contains("-")){
            month = date.substring(date.indexOf("-")+1);
            try{
                month = month.substring(0, month.indexOf("-"));
            }catch(StringIndexOutOfBoundsException e){
                month = inputError;
            }
        }else if(date.contains(" ")){
            month = date.substring(date.indexOf(" ")+1);
            try{
                month = month.substring(0, month.indexOf(" "));
            }catch(StringIndexOutOfBoundsException e){
                month = inputError;
            }
        }else{
            month = formatError;
        }
        return month;
    }
    /**
     *Seperates the day from the month.
     *@param date
     *@return day
     */
    public String getDay(String date){
        String day = "";

        if(date.contains("/")){
            day = date.substring(0, date.indexOf("/"));
            day = day.substring(0,date.indexOf("/"));

        }else if(date.contains("-")){
            day = date.substring(0, date.indexOf("-"));
            day = day.substring(0,date.indexOf("-"));

        }else if(date.contains(" ")){
            day = date.substring(0, date.indexOf(" "));
            day = day.substring(0,date.indexOf(" "));
        }else{
            day = formatError;
        }
        return day;
    }
    
    /**
     * Prints the date formatted to etude specification.
     * @param String date
     */
    public void printDate(String date){
        String year = getYear(date);
        String month = getMonth(date);
        String day = getDay(date);
        
        

        if(year.length() < 4){
            if(Integer.parseInt(year) < 50){
                year = "20" + year;
            }else if(Integer.parseInt(year) > 49){
                year = "19" + year;
            }
        }

        if(month.length() < 3){
            if(month.length() < 2){
                month = "0" + month;
            }
            month = dictionary.get(month);
        }else if(month.length() == 3){
            month = month.toLowerCase();
            month = month.substring(0,1).toUpperCase() + month.substring(1);
        }

        if(day.length() < 2){
            day = "0" + day;
        }

        System.out.println(day +" "+ month +" "+ year);
    }

    /**
     * Check to see if the days in 31 day months are valid.
     * @param date
     */

    public void validateDays31(String date){
        String day = getDay(date);
        if(matchDays31(day) == true){
            printDate(date);
        }else{
            System.out.println(dayOutOfRange);
        }
    }
    
    /**
     * Check to see if the days in 30 day months are valid.
     * @param date
     */
    public void validateDays30(String date){
        String day = getDay(date);
        if(matchDays30(day) == true){
            printDate(date);
        }else{
            System.out.println(dayOutOfRange);
        }
    }
    /**
     * Check to see if the days in non-leapYear february are valid.
     * @param date
     */
    public void validateDaysFebNLY(String date){
        String day = getDay(date);
        if(matchDaysFebNLY(day) == true){
            printDate(date);
        }else{
            System.out.println(dayOutOfRange);
        }
    }
    /**
     * I'm not sure what this is doing, as I don't think I make a call to it,
     * However, when I tried to remove it things broke.
     * @param date
     */
    public void valdiateDaysFebLY(String date){
        String day = getDay(date);
        if(matchDaysFebLY(day) == true){
            printDate(date);
        }else{
            System.out.println(dayOutOfRange);
        }
    }

    
     /**
     * Check to see if the days in leapYear february are valid.
     * @param date
     */ 
    public void validateFebDaysLY(String date){
        String day = getDay(date);
        if(matchDaysFebLY(day) == true){
            printDate(date);
        }else{
            System.out.println(dayOutOfRange);
        }
    }

    /**
     * Checks to see if the month is in valid range.
     * @param date
     */
    public void validateMonths(String date){
        String month = getMonth(date);
        month = month.toUpperCase();
        if(matchMonths31(month) == true){
            validateDays31(date);
        }else if(matchMonths30(month) == true){
            validateDays30(date);
        }else if(matchFeb(month) == true && leapYear == 0){
            validateDaysFebNLY(date);
        }else if(matchFeb(month) == true && leapYear == 1){
            validateFebDaysLY(date);
        }else{
            System.out.println(monthOutOfRange);
        }
    }
    
   /**
    * Check to see if years are in valid range.
    * @param date
    */
    public void validateYear(String date){
        String year = getYear(date);

        if(matchLeapYear(year) == true){
            leapYear = 1;
            validateMonths(date);
        }else if(matchYear(year) == true){
            validateMonths(date);
        }else{
            System.out.println(yearOutOfRange);
        }
    }
    /**
    * Check the date for format errors, and seperator errors.
    * @param date
    */
    public void validateDate(String date){
        String year = getYear(date);
        String month = getMonth(date);
        String day = getDay(date);

        
        //Checks day, month, year lengths to see if input is valid 
        if(day.length() == 0 && month.length() == 1 && year.length() == 0){
            System.out.println(inputError);
        }else if(day.length() == 0 && month.length() == 0 && year.length() == 0){
            System.out.println(inputError);
            
        //Checks to see if the get calls have thrown a seperator error
         }else if(year.equals(inputError) || month.equals(inputError)){
            System.out.println(inputError); 
        }else if(year.equals(formatError) || month.equals(formatError) || day.equals(formatError)){
            System.out.println(formatError);
         
        //Checks the length of the year, month, day to make sure it's a valid format
        }else if(year.length() < 2 || year.length() == 3 || year.length() > 4){
            System.out.println(yearIncorrectFormat);
        }else if(month.length() == 0 || month.length() > 3){
            System.out.println(monthIncorrectFormat);
        }else if(month.length() > 2 && matchMonthsFormat(month) != true){
            System.out.println(monthIncorrectFormat);
        }else if(day.length() == 0 || day.length() > 2){
            System.out.println(dayIncorrectFormat);

                
        //Checks to see if the year, month, and day are numeric when supposed to be
        }else if(matchStringNumeric(year) == false){
            System.out.println(yearIncorrectFormat);
        }else if(month.length() != 3 && matchStringNumeric(month) == false){
            System.out.println(monthIncorrectFormat);
        }else if(matchStringNumeric(day) == false){
            System.out.println(dayIncorrectFormat);

        }else{
            validateYear(date);
        }
      }



    /** These methods all access the regular expressions 
     * at the top of the file to check that the dates match the specified ranges */

    public boolean matchLeapYear(String year){
        return leapYears.matcher(year).matches();
    }

    public boolean matchYear(String year){
        return years.matcher(year).matches();
    }

    public boolean matchFeb(String month){
        return feb.matcher(month).matches();
    }

    public boolean matchMonths30(String month){
        return months30.matcher(month).matches();
    }

    public boolean matchMonths31(String month){
        return months31.matcher(month).matches();
    }

    public boolean matchDaysFebLY(String day){
        return daysFebLY.matcher(day).matches();
    }

    public boolean matchDaysFebNLY(String day){
        return daysFebNLY.matcher(day).matches();
    }

    public boolean matchDays30(String day){
        return days30.matcher(day).matches();
    }
    public boolean matchDays31(String day){
        return days31.matcher(day).matches();
    }

    public boolean matchMonthsFormat(String month){
        return monthsFormat.matcher(month).matches();
    }

    public boolean matchStringNumeric(String input){
        return stringNumeric.matcher(input).matches();
        }

}
