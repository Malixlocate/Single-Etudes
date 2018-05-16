import java.util.regex.Pattern;
import java.util.Scanner;
import java.lang.System;

class dateValidator implements validator {


    private static Pattern pattern1 = Pattern.compile(
            //"((?!0)\\d|0(?!0)\\d|1\\d|3(?![2-9])\\d)"
            "
            ///?   (0(?!2|4|6|9)\\d|1(?!1|3|4|5|6|7|8|9)\\d)  /?  (19[5-9][0-9]|20[0-4][0-9])"
            );
    private static Pattern pattern2 = Pattern.compile("0");
    private static Pattern pattern3 = Pattern.compile("0");
    public int checkMonth(String date){

        String month = "";

        if(date.contains ("/")){
            month = date.substring(date.indexOf("/")+1);
            month = month.substring(0, month.indexOf("/"));

        }else if(date.contains("-")){

            month = date.substring(date.indexOf("-")+1);
            month = month.substring(0, month.indexOf("-"));
        }


        if     (month == "01" || month == "03" || month == "05" || month == "07" || month == "08" || month == "10" || month == "12" ||
                month == "1"  || month == "3"  || month == "5"  || month == "7"  ||  month == "8" ||

                month == "Jan" || month == "Mar" || month == "May" || month == "Jul" || month == "Aug" || month == "Oct" || month == "Dec")
        {return 1;}

        else if(month == "04" || month == "06" || month == "09" || month == "11" ||
                month == "4"  || month == "6"  || month == "9"  ||

                month == "Apr" || month == "Jun" || month == "Sep" || month == "Nov")
        {return 2;}

        else

        {return 3;}

    }


    @Override
    public boolean matches(String date) {

       // if(checkMonth(date) == 1){
            return pattern1.matcher(date).matches();

        //}else if(checkMonth(date) == 2){
          //  return pattern2.matcher(date).matches();

        //}else if(checkMonth(date) == 3){
        //    return pattern3.matcher(date).matches();

        //}else{
        //    System.out.println("Error");
       // }
        
    //return false;
    }
}
