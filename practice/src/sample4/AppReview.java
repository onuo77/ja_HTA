package sample4;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;


public class App {
	
	private static DecimalFormat numberFormat = new DecimalFormat("#,###");
	private static String numberToCurrency(int number) {
		return App.numberFormat.format(number);
	}
	
	private static void printProduct(Product[] products) {
		for(Product item : products) {
			System.out.println("-----------------------------------");
			System.out.println("상품번호 : " + item.getNo());
			System.out.println("상품이름 : " + item.getName());
			System.out.println("상품가격 : " + App.numberToCurrency(item.getPrice()));
			System.out.println("적립포인트 : " + item.getPoint());
		}

	}
	
	private static void printBuyProductInfo(Order[] orders) {
		for(Order item : orders) {
			if(item != null) {
				System.out.println("-----------------------------------");
				System.out.println("상품번호 : " + item.getItem().getNo());
				System.out.println("상품이름 : " + item.getItem().getName());
				System.out.println("상품가격 : " + item.getItem().getPrice());
				System.out.println("적립포인트 : " + item.getItem().getPoint());
				System.out.println("구매수량 : "+ item.getAmount());
			}else {
				break;
			}
		}
	}
	
	private static void buyProductByNo(Product[] products, Order[] orders,boolean isExist, int buyProductAmount,int buyProductNo, int position) {
		for (Product item : products) {
			if(buyProductNo == item.getNo()) {
				isExist = true;
				Order order = new Order();
				order.setItem(item);
				order.setAmount(buyProductAmount);
				orders[position++] = order;
				System.out.println("상품이 구매되었습니다.");
			}
		}
		if(!isExist) {
			System.out.println("해당번호가 존재하지 않습니다.");
		}
	}
	
	public static void main(String[] args) {
		//상품정보를 조회할 수 있다.
		//상품을 구매할 수 있다.
		//상품구매내역을 출력할 수 있다.
		//총 구매금액, 총 적립포인트를 출력할 수 있다.
		
//		1. 상품조회
//			- products 배열에 저장된 모든 상품을 출력한다.
//		2. 상품구매
//			- 상품번호와, 수량을 입력받는다.
//			- 상품번호에 해당하는 상품정보를 조회한다.
//			- 상품번호에 해당하는 상품이 존재하면, 구매정보객체에 상품정보와 구매수량을 저장한다.
//			- 구매정보 배열에 구매정보객체를 저장한다.
//		3. 구매내역조회
//			- 구매정보 배열에 저장된 모든 구매정보를 출력한다.
//		4. 총계조회
//			- 구매정보 배열에 저장된 구매정보를 사용해서, 총 주문금액, 총 적립포인트를 출력한다.
//			- 총주문금액은 상품가격*구매수량의 총합이다.
//			- 총적립포인트는 상품포인트*구매수량의 총합이다.

	
		//상품정보가 저장된 배열
		Product[] products = new Product[10];
		products[0] = new Product(10, "맥북프로", 2_600_000, 1000);
		products[1] = new Product(20, "애플워치", 600_000, 600);
		products[2] = new Product(30, "갤럭시워치", 450_000, 340);
		products[3] = new Product(40, "아이패드", 800_000, 700);
		products[4] = new Product(50, "TV", 400_000, 400);
		products[5] = new Product(60, "LG그램", 1_300_000, 500);
		products[6] = new Product(70, "스피커", 50_000, 100);
		products[7] = new Product(80, "키보드", 30_000, 10);
		products[8] = new Product(90, "마우스", 5000, 50);
		products[9] = new Product(100, "이어폰", 120_000, 120);
		
		//구매정보가 저장된 배열
		Order[] orders = new Order[100];
		int position = 0;
		
		Scanner sc = new Scanner(System.in);
		while(true) {
			System.out.println("---------------------------------------");
			System.out.println("1.상품조회 2.구매 3.구매내역조회 4.총계조회 0.종료");
			System.out.println("---------------------------------------");
			
			System.out.print("원하는 서비스를 선택하세요 : ");
			int menuNo = sc.nextInt();
			
			if(menuNo == 1) {
				System.out.println("[모든 상품 정보]");
				App.printProduct(products);
				
			} else if(menuNo == 2) {
				System.out.println("[상품 번호로 구매하기]");
				System.out.print("구매하려는 상품번호를 입력하세요 : ");
				int buyProductNo = sc.nextInt();
				System.out.print("구매하려는 수량을 입력하세요 : ");
				int buyProductAmount = sc.nextInt();
				
				boolean isExist = false;
				
				App.buyProductByNo(products, orders, isExist, buyProductAmount, buyProductNo, position);
				
//				for (Product item : products) {
//					if(buyProductNo == item.getNo()) {
//						isExist = true;
//						Order order = new Order();
//						order.setItem(item);
//						order.setAmount(buyProductAmount);
//						orders[position++] = order;
//						System.out.println("상품이 구매되었습니다.");
//					}
//				}
//				if(!isExist) {
//					System.out.println("해당번호가 존재하지 않습니다.");
//				}
				
			} else if(menuNo == 3) {
				System.out.println("[구매 내역 조회]");
				App.printBuyProductInfo(orders);

			} else if(menuNo == 4) {
				System.out.println("[총계조회]");
				int totalPrice = 0;
				int savedPoint = 0;
				boolean isExist = false;

				for(Order orderPoint : orders) {
					if(orderPoint != null) {
						isExist = true;
						totalPrice += orderPoint.getItem().getPrice()*orderPoint.getAmount();
						savedPoint += orderPoint.getItem().getPoint()*orderPoint.getAmount();
						System.out.println("총주문금액 : " + totalPrice);
						System.out.println("총적립포인트 : " + savedPoint);
					}
				}
				if(!isExist) {
					System.out.println("상품이 존재하지 않습니다.");
				}
				
			} else if(menuNo == 0) {
				System.out.println("[서비스를 종료합니다.]");
				break;
			}
			
		}
	}
}
