import java.io.*;
import java.text.*;
import java.util.*;

public class dateValidator{

	private List dates = new ArrayList();
	private String regex = "^[0-3]?[0-9]/[0-3]?[0-9]/(?:[0-9]{2})?[0-9]{2}$";


	public static void main(int[]args){
		dates.add("1/1/11");
		dates.add("01/01/01");
	}


	public boolean checkDate(String date) {
		Pattern pattern = Pattern.complie(this.regex);

		for (String date : this.dates){

			Matcher checkValid = pattern.checkValid(date);
			system.out.println(date +" : "+ matcher.checkValid());
		}
	}
}


