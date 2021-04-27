package utils;

import java.text.DecimalFormat;

public class CommonUtils {

	private static DecimalFormat decimalFormat = new DecimalFormat("#,###");
	
	public static String numberToCurrency(int number) {
		return decimalFormat.format(number);
	}
	
	public static String numberToCurrency(long number) {
		return decimalFormat.format(number);
	}
	
}
