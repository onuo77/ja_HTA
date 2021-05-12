package app;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Scanner;

import exception.BookStoreException;
import service.BookStoreService;
import vo.Book;
import vo.User;

public class BookApp {
	DecimalFormat df = new DecimalFormat("#,###");

	private BookStoreService service = new BookStoreService();
	private Scanner scanner = new Scanner(System.in);
	
	public void menu() {
		try {
			
			boolean isLogined = service.isLogined();
			
			if (!isLogined) {
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("1.도서목록  2.로그인  3.회원가입  0.종료");
				System.out.println("-----------------------------------------------------------------------");
				
				System.out.print("메뉴 선택 > ");
				int menuNo = scanner.nextInt();
				System.out.println();
				
				if (menuNo == 1) {
					displayAllBooks();
					
				} else if (menuNo == 2) {
					login();
					
				} else if (menuNo == 3) {
					register();
				
				} else if (menuNo == 0) {
					exit();
				}
				
			} else {
				System.out.println("-----------------------------------------------------------------------");
				System.out.println("1.도서목록  2.검색  3.주문  4.내정보  5.주문내역  6.로그아웃  0.종료");
				System.out.println("-----------------------------------------------------------------------");
				
				System.out.print("메뉴 선택 > ");
				int menuNo = scanner.nextInt();
				System.out.println();
				
				if (menuNo == 1) {
					displayAllBooks();
					
				} else if (menuNo == 2) {
					searchBook();
					
				} else if (menuNo == 3) {
					orderBook();
					
				} else if (menuNo == 4) {
					displayMyInfo();
					
				} else if (menuNo == 5) {
					displayMyOrderHistory();
					
				} else if (menuNo == 6) {
					logout();
					
				} else if (menuNo == 0) {
					exit();
				}
			}
		} catch (BookStoreException e) {
			System.out.println("[오류] " + e.getMessage());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println();
		System.out.println();
		System.out.println();
		
		menu();
	}
	
	/**
	 * 모든 책 정보 출력하기.
	 */
	private void displayAllBooks() {
		System.out.println("[도서 목록]");
		List<Book> bookList = service.getAllBookList();
		System.out.println("책번호\t가격\t할인가격\t재고\t제목");
		System.out.println("-----------------------------------------------");
		for (Book book : bookList) {
			System.out.print(book.getNo()+"\t");
			System.out.print(df.format(book.getPrice())+"\t");
			System.out.print(df.format(book.getDiscountPrice())+"\t");
			System.out.print(book.getStock()+"\t");
			System.out.println(book.getTitle());
		}
		
	}
	
	/**
	 * 로그인하기
	 */
	private void login() {
//		 아이디, 비밀번호를 입력받는다.
//         BookStoreService객체의 login(아이디, 비밀번호)를 실행해서 로그인인증을 수행한다.
//         * 로그인 인증이 통과되면 메뉴가 변경된다.
		System.out.println("[로그인]");
		System.out.print("아이디 > ");
		String id = scanner.next();
		System.out.print("비밀번호 > ");
		String pw = scanner.next();
		
		service.login(id, pw);
		
	}
	
	/**
	 * 회원가입하기
	 */
	private void register() {
		System.out.println("[회원가입]");
		System.out.print("아이디 > ");
		String id = scanner.next();
		System.out.print("이름 > ");
		String name = scanner.next();
		System.out.print("비밀번호 > ");
		String pw = scanner.next();
		
		User newUser = new User(id, name, pw, 0, "일반");
		service.addNewUser(newUser);
	}
	
	/**
	 * 도서 검색
	 */
	private void searchBook(){
		while(true) {
			System.out.println("-----------------------------------------------------------------------");
			System.out.println("1.제목검색  2.카테고리검색  3.가격검색  0.종료");
			System.out.println("-----------------------------------------------------------------------");

			System.out.print("검색 메뉴 선택 > ");
			int searchMenuNo = scanner.nextInt();
			System.out.println();
			
			if (searchMenuNo == 1) {
				System.out.print("검색할 책 제목 > ");
				String searchBookTitle = scanner.next();
				List<Book> searchedBooks = service.searchBooksByTitle(searchBookTitle);
				
				System.out.println("책번호\t가격\t할인가격\t재고\t제목");
				System.out.println("-----------------------------------------------");
				for(Book books : searchedBooks) {
					System.out.print(books.getNo()+"\t");
					System.out.print(df.format(books.getPrice())+"\t");
					System.out.print(df.format(books.getDiscountPrice())+"\t");
					System.out.print(books.getStock()+"\t");
					System.out.println(books.getTitle());
				}
				if(searchedBooks.isEmpty()) {
					System.out.println("[안내] \'"+searchBookTitle+"\' 검색결과가 없습니다.");
				}
				
			} else if (searchMenuNo == 2) {
				System.out.print("검색할 책 카테고리 > ");
				String searchBookCategory = scanner.next();
				List<Book> searchedBooks = service.searchBooksByCategory(searchBookCategory);
				
				System.out.println("책번호\t카테고리\t가격\t할인가격\t재고\t제목");
				System.out.println("-----------------------------------------------");
				for(Book books : searchedBooks) {
					System.out.print(books.getNo()+"\t");
					System.out.print(books.getCategory()+"\t");
					System.out.print(df.format(books.getPrice())+"\t");
					System.out.print(df.format(books.getDiscountPrice())+"\t");
					System.out.print(books.getStock()+"\t");
					System.out.println(books.getTitle());
				}
				if(searchedBooks.isEmpty()) {
					System.out.println("[안내] \'"+searchBookCategory+"\' 검색결과가 없습니다.");
				}								
				
			} else if (searchMenuNo == 3) {
				System.out.print("검색할 책의 최소가격 > ");
				int minPrice = scanner.nextInt();
				System.out.print("검색할 책의 최대가격 > ");
				int maxPrice = scanner.nextInt();
				
				List<Book> searchedBooks = service.searchBooksByPrice(minPrice, maxPrice);
				
				System.out.println("책번호\t가격\t할인가격\t재고\t제목");
				System.out.println("-----------------------------------------------");
				for(Book books : searchedBooks) {
					System.out.print(books.getNo()+"\t");
					System.out.print(df.format(books.getPrice())+"\t");
					System.out.print(df.format(books.getDiscountPrice())+"\t");
					System.out.print(books.getStock()+"\t");
					System.out.println(books.getTitle());
				}
				if(searchedBooks.isEmpty()) {
					System.out.println("[안내] 검색결과가 없습니다.");
				}		
				
			} else if (searchMenuNo == 0) {
				break;
			}
			System.out.println();
		}
	}
	
	/**
	 * 책 주문하기
	 */
	private void orderBook() {
		System.out.print("구매할 책 번호 > ");
		int orderBookNo = scanner.nextInt();
		System.out.print("구매수량 > ");
		int orderAmount = scanner.nextInt();
		service.orderBook(orderBookNo, orderAmount);
		System.out.println("[안내] 주문이 완료되었습니다.");
		
	}
	
	/**
	 * 내 정보 보기
	 */
	private void displayMyInfo() {
		User myInfo = service.getMyInfo();
		System.out.println("["+myInfo.getId()+"]님의 정보");
		System.out.println("사용자아이디 : " + myInfo.getId());
		System.out.println("이름 : " + myInfo.getName());
		System.out.println("고객등급 : " + myInfo.getGrade());
		System.out.println("포인트 : " + df.format(myInfo.getPoint()));
	}
	
	/**
	 * 내주문내역 보기
	 */
	private void displayMyOrderHistory() {
		List<Map<String,Object>> myOrderInfo = service.getMyOrderList();
		for(Map<String,Object> map : myOrderInfo) {
			for(Entry<String, Object> entry : map.entrySet()) {
				System.out.println(entry.getKey() + " : " + entry.getValue());
			}
			System.out.println();
		}
	}
	
	/**
	 * 로그아웃하기
	 */
	private void logout() {
		service.logout();
	}
	
	/**
	 * 종료하기
	 */
	private void exit() {
		System.out.println("[프로그램 종료]");
		service.restore();
		System.exit(0);
	}
	
	public static void main(String[] args) {
		new BookApp().menu();
	}
}
