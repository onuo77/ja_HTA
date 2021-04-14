package format;

import java.text.DecimalFormat;

public class DecimalFormatApp {

	public static void main(String[] args) {
		
		//숫자 3자리마다 ,찍기
		int number = 10000000;
		
//		DecimalFormat decimalFormat = new DecimalFormat("#,###");
//		String text = decimalFormat.format(number);
//		System.out.println("결과값 : " + text);	//결과값 : 10,000,000
	
//		DecimalFormat decimalFormat = new DecimalFormat("#,000");
//		String text = decimalFormat.format(number);
//		System.out.println("결과값 : " + text);	//결과값 : 10,000,000

		//실수 출력하기
		double number2 = 123.456;
		
//		DecimalFormat decimalFormat = new DecimalFormat("#.##");
//		String text = decimalFormat.format(number2);
//		System.out.println("결과값 : " + text);	//결과값 : 123.46 (3번째 자리에서 반올림되어 값출력됨)
		
//		DecimalFormat decimalFormat = new DecimalFormat("0.0000");
//		String text = decimalFormat.format(number2);
//		System.out.println("결과값 : " + text);	//결과값 : 123.4560

		double value = 1.0/3.0;
		System.out.println(value);	//0.3333333333333333
		DecimalFormat decimalFormat = new DecimalFormat("#.##");
		String text = decimalFormat.format(value);
		System.out.println("결과값 : " + text);	//결과값 : 0.33
	}
}
