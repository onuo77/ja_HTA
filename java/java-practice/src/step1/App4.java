package step1;

import java.util.Scanner;

public class App4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		/*
		 * Scanner객체의 nextInt()메소드를 실행해서 정수를 2개 입력받고 각각의 변수에 대입하세요.
		 * 변수에 대입된 값을 화면에 출력하시오.
		 */
		
		System.out.println("정수1을 입력하세요");
		int value1 = scanner.nextInt();
		System.out.println("정수2를 입력하세요");
		int value2 = scanner.nextInt();
		
		System.out.println("정수1 : " + value1);
		System.out.println("정수2 : " + value2);
	}
}
