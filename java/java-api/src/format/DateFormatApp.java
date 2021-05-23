package format;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class DateFormatApp {

	public static void main(String[] args) {
		
		Date today = new Date();
		System.out.println("날짜 시간정보 : " + today);
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//2021
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//2021-04
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//2021년 4월
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//2021-04-14
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm:ss");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//12:22:18
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("a h시 m분 s초");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//오후 12시 23분 33초
		
//		SimpleDateFormat dateFormat = new SimpleDateFormat("a hh:mm:ss",Locale.US);
//		String text = dateFormat.format(today);
//		System.out.println(text);	//PM 12:28:19

//		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일 EEEE");
//		String text = dateFormat.format(today);
//		System.out.println(text);	//2021년 4월 14일 수요일
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy년 M월 d일 EEEE a h시 m분 s초");
		String text = dateFormat.format(today);
		System.out.println(text);	//2021년 4월 14일 수요일 오후 12시 30분 7초

	}
}
