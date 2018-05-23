import java.util.regex.Pattern;
import java.util.Scanner;
import java.util.Map;
import java.util.HashMap;
import java.lang.System;

class dateValidator{

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
        ("(17|18|19|2[0-9])[0-9]{2}|3000|(0\\d|\\d\\d)");

    private static Pattern leapYears = Pattern.compile
        ("(2000|2400|2800|3000|((18|19|2[0-9])(0[48]|[2468][048]|[13579][26])|(17([68][048]|(56|[79][26])))))"); 

    private static Pattern monthsFormat = Pattern.compile
        ("(JAN|FEB|MAR|APR|MAY|JUN|JUL|AUG|SEP|NOV|OCT|DEC|Jan|Feb|Mar|Apr|May|Jun|Jul|Aug|Sep|Nov|Oct|Dec|jan|feb|mar|may|jun|jul|aug|sep|nov|oct|dec)");

    public static String formatError = "Input entered is invalid: Please enter a date.";

    private static Map<String, String> dictionary = new HashMap<String, String>();

    public void createDictionary(){
        dictionary.put("01", "Jan"); dictionary.put("02", "Feb"); dictionary.put("03", "Mar");
        dictionary.put("04", "Apr"); dictionary.put("05", "May"); dictionary.put("06", "Jun");
        dictionary.put("07", "Jul"); dictionary.put("08", "Aug"); dictionary.put("09", "Sep");
        dictionary.put("10", "Nov"); dictionary.put("11", "Oct"); dictionary.put("12", "Dec");
    }

    public String getYear(String date){
        String year = "";

        if(date.contains ("/")){
            year = date.substring(date.indexOf("/")+1);
            year = year.substring(year.indexOf("/")+1);
            if(year.contains("/")){
                year = formatError;
            }

        }else if(date.contains("-")){
            year = date.substring(date.indexOf("-")+1);
            year = year.substring(year.indexOf("-")+1);
            if(year.contains("-")){
                year = formatError;
            }
        }else if(date.contains(" ")){
            year = date.substring(date.indexOf(" ")+1);
            year = year.substring(year.indexOf(" ")+1);
            if(year.contains(" ")){
                year = formatError;
            }
        }else{
            year = formatError;
        }
        return year;
    }


    public String getMonth(String date){
        String month = "";

        if(date.contains ("/")){
            month = date.substring(date.indexOf("/")+1);
            try{
                month = month.substring(0, month.indexOf("/"));
            }catch(StringIndexOutOfBoundsException e){
                month = formatError;
            }
        }else if(date.contains("-")){
            month = date.substring(date.indexOf("-")+1);
            try{
                month = month.substring(0, month.indexOf("-"));
            }catch(StringIndexOutOfBoundsException e){
                month = formatError;
            }
        }else if(date.contains(" ")){
            month = date.substring(date.indexOf(" ")+1);
            try{
                month = month.substring(0, month.indexOf(" "));
            }catch(StringIndexOutOfBoundsException e){
                month = formatError;
            }
        }else{
            month = formatError;
        }
        return month;
    }

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


        System.out.println("Output:");
        System.out.println(day +" "+ month +" "+ year);
    }


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


}
