package step2;

public class App3 {

	public static void main(String[] args) {
		/*
		 * for문을 이용해서 1~100까지 정수의 합계를 계산하고 그 결과를 출력하시오
		 */
		int sum = 0;
		for(int i=1; i<=100; i++) {
			sum += i;
		}
		System.out.println(sum);
	}
}
