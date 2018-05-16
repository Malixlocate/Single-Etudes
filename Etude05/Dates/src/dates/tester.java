
import java.lang.System;
import java.util.Scanner;
import java.lang.Integer;



public class tester{

	public static void main(String[]args){

		int dayNum;
		int monthNum;
		int yearNum;

		String[] months = {
			"Jan", "Feb", "Mar", "Apr", "May", "Jun", "Jul", "Aug", "Sep", "Oct", "Nov", "Dec"
		};

		String day = "1";
		String month = "1";
		String year = "1965";

		//format printed:	01/01/1990
		for(yearNum = 1965; yearNum <=1966; yearNum++){

			year = Integer.toString(yearNum);

			for(monthNum = 1; monthNum <=12; monthNum++){

				month = Integer.toString(monthNum);


				for (dayNum = 1; dayNum <= 31; dayNum++){

					day = Integer.toString(dayNum);

					if (dayNum <= 9 && monthNum > 9){
						System.out.println("0"+day+"/"+month+"/"+year);
					}else if (dayNum <= 9 && monthNum <= 9){
						System.out.println("0"+day+"/"+"0"+month+"/"+year);
					}else if (dayNum > 9 && monthNum <= 9){
						System.out.println(day+"/"+"0"+month+"/"+year);
					}else if (dayNum > 9 && monthNum > 9){
						System.out.println(day+"/"+month+"/"+year);
					}
				}
			}

		}
		//format printed:	01/Jan/1990
		for(yearNum = 1965; yearNum <=1966; yearNum++){

			year = Integer.toString(yearNum);

			for(int i = 0; i < months.length; i++){

				month = months[i];
				for (dayNum = 1; dayNum <= 31; dayNum++){

					day = Integer.toString(dayNum);

					if (dayNum <= 9){
						System.out.println("0"+day+"/"+month+"/"+year);
					}else{
						System.out.println(day+"/"+month+"/"+year);
					}
				}
			}
		}
	


	//format printed:	1/jan/1990
	for(yearNum = 1965; yearNum <=1966; yearNum++){

		year = Integer.toString(yearNum);

		for(int i = 0; i < months.length; i++){

			month = months[i];


			for (dayNum = 1; dayNum <= 12; dayNum++){

				day = Integer.toString(dayNum);

				System.out.println(day+"/"+month+"/"+year);
			}
		}
	}



	System.out.println("exit");
}
}



