import java.util.regex.Pattern;
import java.util.Scanner;
import java.lang.System;

class dateValidator implements validator {

	
	private static Pattern pattern = Pattern.compile(
          "(0[1-9]|1[0-2])/?(0[0-9]|1[0-2])/?(19[5-9][0-9]|20[0-4][0-9])"
       // "\\d{2}-?\\d{2}-?\\d{2}"
    );
      /**
      "^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$"
      + "|^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$"
      + "|^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$"
      + "|^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$");
	**/

	//matching Febuary 29th:
	//^((2000|2400|2800|(19|2[0-9](0[48]|[2468][048]|[13579][26])))-02-29)$
	
	//Matchhin General days of febuaury:
	//^(((19|2[0-9])[0-9]{2})-02-(0[1-9]|1[0-9]|2[0-8]))$
	
	//Matching 31-day months
	//^(((19|2[0-9])[0-9]{2})-(0[13578]|10|12)-(0[1-9]|[12][0-9]|3[01]))$
	
	//Matching 30-day months
	//^(((19|2[0-9])[0-9]{2})-(0[469]|11)-(0[1-9]|[12][0-9]|30))$
	

    //	
	
	
	
	
	@Override
	public boolean matches(String date) {
		return pattern.matcher(date).matches();
	}
}	
