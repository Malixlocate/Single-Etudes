import org.omg.CORBA.DynAnyPackage.Invalid;
import java.util.Scanner;

public class Dates {
    private static Scanner sc;
    private static String month, lineIn;
    private static String[] splitLine, result;
    private static final String[] MONTHS = {
        "Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
    };

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        result = new String[3];
        while (sc.hasNextLine()) {
            lineIn = sc.nextLine();
            splitLine = split();
            if (splitLine == null) {
                invalidLine(0);
            } else if (!handleMonth() || !handleYear() || !handleDay()) {
                //DO Something
            } else printValid();
        }
    }

    public static String[] split() {
        String[] temp;

        temp = lineIn.split(" ");

        if (temp.length != 3) {
            temp = lineIn.split("/");
        } else {
            return temp;
        }

        if (temp.length != 3) {
            temp = lineIn.split("-");
        } else {
            return temp;
        }

        return temp.length == 3 ? temp : null;
    }

    public static Boolean handleMonth() {
        String month = splitLine[1].toLowerCase();
        month = month.substring(0, 1).toUpperCase() + month.substring(1);
        Boolean isWord = checkIfWord(month);
        //System.out.println("Month is " + month + " and isWord is " + isWord);
        if (isWord && contains(month) != -1) {
            result[1] = month;
            return true;
        } else if (month.length() <= 2 && month.length() >= 1 && isNumeric(month)) {
            try { 
                int monthNum = Integer.parseInt(month);
                if (monthNum > 12 || monthNum < 1) {
                    invalidLine(6);
                    return false;
                }
                result[1] = MONTHS[monthNum - 1];
                return true;
            } catch (NumberFormatException e) {
                e.printStackTrace();
                return false;
            }
        } else {
            invalidLine(1);
            return false;
        }
    }

    public static Boolean handleDay() {
        int dayAsInt;
        String day = splitLine[0];
        int month = contains(result[1]);
        result[0] = day;
        if (!(day.length() >= 1 && day.length() <= 2) || !isNumeric(day)) {
            invalidLine(4);
            return false;
        }
        dayAsInt = Integer.parseInt(day);

        if (month == 1) {
            if ((isLeapYear(Integer.parseInt(result[2])) && dayAsInt <= 29) || dayAsInt <= 28) {
                return true;
            } else {
                invalidLine(5);
                return false;
            }
        } else if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (dayAsInt > 0 && dayAsInt <= 31) {
                return true;
            } else {
                invalidLine(5);
                return false;
            }
        } else {
            if (dayAsInt > 0 && dayAsInt <= 30) {
                return true;
            } else {
                invalidLine(5);
                return false;
            }
        }
    }

    public static int contains(String in) {
        for (int i = 0; i < MONTHS.length; i++) {
            if (MONTHS[i].equals(in))  {
                //System.out.println(in + " found in array");
                return i;
            }
        }
        return -1;
    }

    public static Boolean handleYear() {
        String year = splitLine[2];
        int yearAsInt;
        if (year.length() != 2 && year.length() != 4 || !isNumeric(year)) {
            //System.out.println("Year length: " + year.length());
            invalidLine(2);
            return false;
        }
        yearAsInt = Integer.parseInt(year);
        if (year.length() == 2) {
            if (yearAsInt >= 50 && yearAsInt <= 99) {
                year = "19" + year;
            } else if (yearAsInt >= 0 && yearAsInt <= 49) {
                year = "20" + year;
            } else {
                invalidLine(3);
                return false;
            }
            yearAsInt = Integer.parseInt(year);
            //System.out.println("Year is " + year);
        }
        
        if (yearAsInt > 3000 || yearAsInt < 1753) {
            invalidLine(3);
            return false;
        }
        result[2] = year;
        return true;
    }

    public static Boolean isLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                if (year % 400 == 0) {
                    return true;
                }
                return false;
            }
            return true;
        }
        return false;
    }

    public static Boolean isNumeric(String numStr) {
        for (int i = 0; i < numStr.length(); i++) {
            if (!Character.isDigit(numStr.charAt(i))) return false;
        }
        return true;
    }

    public static Boolean checkIfWord(String in) {
        for (int i = 0; i < in.length(); i++) {
            if (Character.isLetter(in.charAt(i))) return true;
        }
        return false;
    }

    public static void invalidLine(int errorCode) {
        //0: Separator Error, 1: Invalid Month Format, 2: Invalid Year Format. 3: Year out of range
        //4: Day format error, 5: Day out of range
        String errorLine = lineIn + " : INVALID - ";
        switch (errorCode) {
            case 0: errorLine += "Separator Error";
            break;
            case 1: errorLine += "Incorrect Month Format";
            break;
            case 2: errorLine += "Incorrect Year Format";
            break;
            case 3: errorLine += "Year Out Of Range";
            break;
            case 4: errorLine += "Incorrect Day Format";
            break;
            case 5: errorLine += "Day Out Of Range";
            break;
            case 6: errorLine += "Month Out Of Range";
            break;
        }
        System.out.println(errorLine);
    }

    public static void printValid() {
        if (result[0].length() == 1) result[0] = "0" + result[0];
        System.out.println(result[0] + " " + result[1] + " " + result[2]);
    }
}
