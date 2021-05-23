package step6;

import java.util.ArrayList;
import java.util.Scanner;

public class App3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(System.in);
		ArrayList<Order> orders = new ArrayList<>();
		
		while (true) {
			System.out.println("---------------------------------------------------------------------");
			System.out.println("1.조회  2.추가  3.검색  0.종료");
			System.out.println("---------------------------------------------------------------------");
			
			System.out.print("메뉴를 선택하세요 > ");
			int menuNo = scanner.nextInt();
			System.out.println();
			
			if (menuNo == 1) {
				System.out.println("[전체 조회]");
				// 위에서 생성한 ArrayList에 저장된 주문정보를 화면에 출력하시오
				
			} else if (menuNo == 2) {
				System.out.println("[주문 추가]");
				// 상품명, 주문자, 주문가격, 주문량을 입력받아서 Order객체에 저장하시오.
				// Order객체를 위에서 생성한 ArrayList객체에 추가하시오
				
			} else if (menuNo == 3) {
				System.out.println("[주문 상품 검색]");
				// 상품명을 입력받아서 ArrayList객체에서 상품명에 해당하는 주문정보를 찾아서 화면에 출력하시오
				
			} else if (menuNo == 0) {
				System.out.println("[종료]");
				break;
			}
		}
		
	}
}
