import java.util.regex.Pattern;
import java.util.Scanner;
import java.lang.System;

class dateValidator implements validator {


    private static Pattern pattern1 = Pattern.compile(
            //pattern 1
            
          // "(((?!0)\\d|0(?!0)\\d|1\\d|2\\d|30|31)(/|-)((?!0|2|4|6|9)\\d|0(?!0|2|4|6|9)\\d|10|12|Jan|Mar|May|Jul|Aug|Oct|Dec)(/|-)((17|18|19|2[0-9])[0-9]{2}|3000|(0\\d|\\d\\d)))"
            //pattern 2
            "|(((?!0)\\d|0(?!0)\\d|1\\d|2\\d|30)(/|-)((4|6|9|)|0(4|6|9)|11|Apr|Jun|Sep|Nov)(/|-)((17|18|19|2[0-9])[0-9]{2}|3000)|(0\\d|\\d\\d)))"

            //pattern 3
           + "|(((?!0)\\d|0(?!0)\\d|1\\d|2(?!9)\\d)(/|-)(2|02|FEB)(/|-)(((17|18|19|2[0-9])[0-9]{2}|3000)|(0\\d|\\d\\d)))"
            
            //months for pattern 1
            //"((?!0|2|4|6|9)\\d|0(?!0|2|4|6|9)\\d|10|12|Jan|Mar|May|Jul|Aug|Oct|Dec)"

            //months for pattern 2
            //"((4|6|9|)|0(4|6|9)|11|Apr|Jun|Sep|Nov)"

            //months for pattern 3
            //"((2|02|FEB)"
            
          //  "((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26]))))" 

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


             if(month == "01" || month == "03" || month == "05" || 
                month == "07" || month == "08" || month == "10" || 
                month == "12" || month == "1"  || month == "3"  || 
                month == "5"  || month == "7"  || month == "8"  ||
                month == "Jan"|| month == "Mar"|| month == "May"|| 
                month == "Jul"|| month == "Aug"|| month == "Oct"|| 
                month == "Dec")

        {return 1;}

        else if(month == "04" || month == "06" || month == "09" || 
                month == "11" || month == "4"  || month == "6"  || 
                month == "9"  || month == "Apr"|| month == "Jun"|| 
                month == "Sep"|| month == "Nov")

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
