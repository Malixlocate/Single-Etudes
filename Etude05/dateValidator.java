import java.util.regex.Pattern;
import java.util.Scanner;
import java.lang.System;

class dateValidator{

    private static Pattern days31 = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2\\d|30|31)");

    private static Pattern months31 = Pattern.compile
        ("((?!0|2|4|6|9)\\d|0(?!0|2|4|6|9)\\d|10|12|Jan|Mar|May|Jul|Aug|Oct|Dec)");

    private static Pattern days30 = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2\\d|30)");

    private static Pattern months30 = Pattern.compile
        ("((4|6|9|)|0(4|6|9)|11|Apr|Jun|Sep|Nov)");

    private static Pattern daysFebNLY = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2(?!9)\\d)");

    private static Pattern daysFebLY = Pattern.compile
        ("((?!0)\\d|0(?!0)\\d|1\\d|2\\d)");

    private static Pattern feb = Pattern.compile
        ("(2|02|FEB)");

    private static Pattern years = Pattern.compile 
        ("(17|18|19|2[0-9])[0-9]{2}|3000|(0\\d|\\d\\d)");

    private static Pattern leapYears = Pattern.compile
        ("(2000|2400|2800|3000|((18|19|2[0-9])(0[48]|[2468][048]|[13579][26])|(17([68][048]|(56|[79][26])))))"); 

    public static String seperatorError = "seperator error, seperator format invalid";


   public String[] splitDate(String date){
   
   String[] splitDate = new String[3];
   String year = "";
   String month = "";
   String day = "";
    
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
year = seperatorError;

splitDate[2] = year;

    if(date.contains ("/")){
            month = date.substring(date.indexOf("/")+1);
            month = month.substring(0, month.indexOf("/"));

        }else if(date.contains("-")){
            month = date.substring(date.indexOf("-")+1);
            month = month.substring(0, month.indexOf("-"));

        }else if(date.contains(" ")){
            month = date.substring(date.indexOf(" ")+1);
            month = month.substring(0, month.indexOf(" "));
        }

        month = month.toUpperCase();
        splitDate[1] = month;

        if(date.contains("/")){
            day = date.substring(0, date.indexOf("/"));
            day = day.substring(0,date.indexOf("/"));

        }else if(date.contains("/")){
            day = date.substring(0, date.indexOf("-"));
            day = day.substring(0,date.indexOf("-"));

        }else if(date.contains("/")){
            day = date.substring(0, date.indexOf("-"));
            day = day.substring(0,date.indexOf("-"));
}

        splitDate[0] = day;
        }
        return splitDate;
    }




    

    public String getYear(String date){
        String year = "";

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
            year = seperatorError;

            }
            return year;
    }

    public String getMonth(String date){
        String month = "";

        if(date.contains ("/")){
            month = date.substring(date.indexOf("/")+1);
            month = month.substring(0, month.indexOf("/"));

        }else if(date.contains("-")){
            month = date.substring(date.indexOf("-")+1);
            month = month.substring(0, month.indexOf("-"));

        }else if(date.contains(" ")){
            month = date.substring(date.indexOf(" ")+1);
            month = month.substring(0, month.indexOf(" "));
        }

        month = month.toUpperCase();
        return month;
    }

    public String getDay(String date){
        String day = "";

        if(date.contains("/")){
            day = date.substring(0, date.indexOf("/"));
            day = day.substring(0,date.indexOf("/"));

        }else if(date.contains("/")){
            day = date.substring(0, date.indexOf("-"));
            day = day.substring(0,date.indexOf("-"));

        }else if(date.contains("/")){
            day = date.substring(0, date.indexOf("-"));
            day = day.substring(0,date.indexOf("-"));
}
        return day;
    }

/*
    public int checkYear(String year){

        if (Integer.parseInt(year) % 4 == 0 && Integer.parseInt(year) % 100 !=0)
        {return 1;}

        else if (Integer.parseInt(year) % 400 == 0)
        {return 1;}

        else
        {return 0;}

    }

*/

    /*
       public int checkMonth(String date){

       if(     month.equals("01") || month.equals("03") || month.equals("05") || 
       month.equals("07") || month.equals("08") || month.equals("10") || 
       month.equals("12") || month.equals("1")  || month.equals("3")  || 
       month.equals("5")  || month.equals("7")  || month.equals("8")  ||
       month.equals("JAN")|| month.equals("MAR")|| month.equals("MAY")|| 
       month.equals("JUL")|| month.equals("AUG")|| month.equals("OCT")|| 
       month.equals("DEC"))

       {return 1;}

       else if(month.equals("04") || month.equals("06") || month.equals("09") || 
       month.equals("11") || month.equals("4")  || month.equals("6")  || 
       month.equals("9")  || month.equals("APR")|| month.equals("JUN")|| 
       month.equals("SEP")|| month.equals("NOV"))

       {return 2;}

       else

       {return 3;}

       }

*/

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


}
