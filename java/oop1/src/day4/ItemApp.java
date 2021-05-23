package day4;

import java.util.Scanner;

public class ItemApp {
	
	public static void main(String[] args) {
		final int SEARCH_ALL_PRODUCT = 1;
		final int SEARCH_CATEGORY = 2;
		final int SEARCH_NAME = 3;
		final int SEARCH_PRICE = 4;
		final int SEARCH_NUMBER = 5;
		final int INSERT_PRODUCT = 6;
		final int DELETE_PRODUCT = 7;
		final int EXIT = 0;
		
		Scanner sc = new Scanner(System.in);
		//Item객체에 대한 조회/검색/추가/삭제 기능을 제공하는 객체 생성
		ItemService service = new ItemService();
		
		while(true) {
			System.out.println("------------------------------------------------");
			System.out.println("1.전체 2.카테고리 3.이름 4.가격 5.번호 6.등록 7.삭제 0.종료");
			System.out.println("------------------------------------------------");
			
			System.out.print("메뉴번호를 선택하세요 : ");
			int menuNo = sc.nextInt();
			
			if(menuNo == SEARCH_ALL_PRODUCT) {
				service.printAllItem();
				
			} else if(menuNo == SEARCH_CATEGORY) {
				System.out.print("상품 카테고리를 입력하세요 : ");
				String categoryName = sc.next();
				
				service.printItemByCategory(categoryName);
				
			} else if(menuNo == SEARCH_NAME) {
				System.out.print("상품명을 입력하세요 : ");
				String productName = sc.next();
				
				service.printItemByName(productName);
				
			} else if(menuNo == SEARCH_PRICE) {
				System.out.print("상품의 최소가격을 입력하세요 : ");
				int pdMinPrice = sc.nextInt();
				
				System.out.print("상품의 최대가격을 입력하세요 : ");
				int pdMaxPrice = sc.nextInt();
				
				service.printItemByPrice(pdMinPrice, pdMaxPrice);
				
			} else if(menuNo == SEARCH_NUMBER) {
				System.out.print("상품번호를 입력하세요 : ");
				int productNo = sc.nextInt();
				
				service.printItemDetail(productNo);
				
			} else if(menuNo == INSERT_PRODUCT) {
				Item newItem = new Item();
				
				System.out.print("상품번호를 입력하세요 : ");
				newItem.no = sc.nextInt();
				
				System.out.print("카테고리를 입력하세요 : ");
				newItem.category = sc.next();
				
				System.out.print("상품이름을 입력하세요 : ");
				newItem.name = sc.next();
				
				System.out.print("제조사를 입력하세요 : ");
				newItem.maker = sc.next();
				
				System.out.print("상품가격을 입력하세요 : ");
				newItem.price = sc.nextInt();
				
				service.insertItem(newItem);
				
			} else if(menuNo == DELETE_PRODUCT) {
				System.out.print("삭제할 상품번호를 입력하세요 : ");
				int productNo = sc.nextInt();
				
				service.deleteItem(productNo);
				
			} else if(menuNo == EXIT) {
				System.out.println("--------------------- 종료 ----------------------");
				break;
			}else {
				System.out.println("잘못된 번호입니다.");
			}
		}
	}
}
