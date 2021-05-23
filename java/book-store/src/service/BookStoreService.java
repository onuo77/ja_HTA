package service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import exception.BookException;
import exception.UserException;
import repository.BookRepository;
import repository.OrderRepository;
import repository.UserRepository;
import vo.Book;
import vo.Order;
import vo.User;

public class BookStoreService {

	DecimalFormat df = new DecimalFormat("#,###");
	
	private BookRepository bookRepository = new BookRepository();
	private UserRepository userRepository = new UserRepository();
	private OrderRepository orderRepository = new OrderRepository();
	
	//로그인 인증을 마친 사용자정보가 저장된다.
	private User loginedUser = null;
	
	/**
	 * 모든 책정보를 반환한다.
	 * 모든 책정보를 조회하는 기능
	 * @return 도서 목록
	 */
	public List<Book> getAllBookList() {
		return bookRepository.getAllBooks();
	}
	
	/**
	 * 지정된 카테고리에 해당하는 책 정보들을 반환한다.
	 * 지정된 카테고리에 해당하는 책 정보들을 조회하는 기능
	 * @param category 조회할 도서 카테고리
	 * @return 도서 목록
	 */
	public List<Book> searchBooksByCategory(String category) {
		ArrayList<Book> bookByCategory = new ArrayList<>();
		List<Book> books = bookRepository.getAllBooks();
		for(Book b : books) {
			if(category.equals(b.getCategory())) {
				bookByCategory.add(b);
			}
		}
		return bookByCategory;
	}
	
	/**
	 * 지정된 책 제목을 포함하고 잇는 책 정보들을 반환한다.
	 * 지정된 책 제목을 포함하고 있는 책 정보들을 조회하는 기능
	 * @param title 조회할 도서 제목
	 * @return 도서 목록
	 */
	public List<Book> searchBooksByTitle(String title) {
		ArrayList<Book> bookByTitle = new ArrayList<>();
		List<Book> books = bookRepository.getAllBooks();
		for(Book b : books) {
			if(b.getTitle().contains(title)) {
				bookByTitle.add(b);
			}
		}
		return bookByTitle;
	}
	
	/**
	 * 지정된 가격범위에 속하는 책 정보들을 반환한다.
	 * 지
	 * @param minPrice 최소 가격
	 * @param maxPrice 최대 가격
	 * @return 도서 목록
	 */
	public List<Book> searchBooksByPrice(int minPrice, int maxPrice) {
		ArrayList<Book> bookByPriceRange = new ArrayList<>();
		List<Book> books = bookRepository.getAllBooks();
		for(Book b : books) {
			if(b.getPrice() >= minPrice && b.getPrice() <= maxPrice) {
				bookByPriceRange.add(b);
			}
		}
		return bookByPriceRange;
	}
	
	/**
	 * 지정된 책번호에 해당하는 책정보를 반환한다.
	 * @param no 조회할 책 번호
	 * @return 도서
	 */
	public Book findBook(int no) {
		return null;
	}
	
	/**
	 * 지정된 책번호의 재고를 변경한다.
	 * @param no 책번호
	 * @param stock 변경된 재고량
	 */
	public void updateBookStock(int no, int stock) {
		
	}
	
	/**
	 * 새 사용자정보를 등록하는 기능
	 * @param user 새 사용자
	 */
	public void addNewUser(User user) {
		User duplicatedUser = userRepository.getUserById(user.getId());
		if(duplicatedUser != null) {
			throw new UserException("[안내] 이미 등록된 아이디입니다.");
		}
		userRepository.insertUser(user);
		System.out.println("[안내] 회원가입이 완료되었습니다.");
	}
	
	/**
	 * 지정된 아이디와 비밀번호로 사용자를 인증하는 기능
	 * @param id 아이디
	 * @param password 비밀번호
	 */
	public void login(String id, String password){

		User savedLogin = userRepository.getUserById(id);
		if(savedLogin == null) {
			throw new UserException("[안내] 아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		if(!savedLogin.getPassword().equals(password)) {
			throw new UserException("[안내] 아이디 혹은 비밀번호가 일치하지 않습니다.");
		}
		System.out.println("[안내] 로그인 되었습니다.");
		loginedUser = savedLogin;
	}
	
	/**
	 * 로그인된 사용자 정보를 초기화하는 기능
	 */
	public void logout() {
		loginedUser = null;
	}
	
	/**
	 * 로그인여부를 반환하는 기능
	 * @return 로그인된 사용자정보가 존재하면 true를 반환한다.
	 */
	public boolean isLogined() {
		boolean isExist = false;
		if(loginedUser != null) {
			isExist = true;
		}
		return isExist;
	}
	
	/**
	 * 지정된 책번호의 책을 수량만큼 주문하는 기능
	 * @param bookNo 책번호
	 * @param amount 주문수량
	 */
	public void orderBook(int bookNo, int amount) {
		if(loginedUser == null) {
			throw new UserException("[안내] 로그인이 필요한 서비스입니다.");
		}

		Book searchBooks = bookRepository.getBookByNo(bookNo);
		
		if(searchBooks == null) {
			throw new BookException("[안내] 책번호가 올바르지 않습니다.");
		}
		if(searchBooks.getStock() < amount) {
			throw new BookException("[안내] 재고량이 부족합니다.");
		}
		
		Order orderBooks = new Order(loginedUser.getId(), bookNo, amount);
		orderRepository.insertOrder(orderBooks);
		
		int stock = searchBooks.getStock() - amount;		//-= 안되는이유
		searchBooks.setStock(stock);
		int point = (int) ((searchBooks.getPrice()*amount) * 0.01);
		loginedUser.setPoint(point + loginedUser.getPoint());
		
		int userPoint = loginedUser.getPoint();
		// 일반 :                 ~    99,999
		// 로얄 :      100,000    ~   999,999
		// 골드 :    1,000,000    ~ 4,999,999
		// 플레티넘 : 5,000,000
		if(userPoint >= 100_000 && userPoint <= 999_999) {
			loginedUser.setGrade("로얄");
		} else if (userPoint >= 1_000_000 && userPoint <= 4_999_999){
			loginedUser.setGrade("골드");
		} else if (userPoint >= 5_000_000) {
			loginedUser.setGrade("플래티넘");
		}
		
	}
	
	/**
	 * 로그인한 사용자의 주문내역을 조회하는 기능
	 * @return 주문 목록
	 */
	public List<Map<String,Object>> getMyOrderList() {
		if(loginedUser == null) {
			throw new UserException("[안내] 로그인이 필요한 서비스입니다.");
		}

		ArrayList<Map<String,Object>> savedOrderDetailList = new ArrayList<Map<String,Object>>();
		List<Order> allOrderList = orderRepository.getAllOrders();
		
		int savedBookNo = 0;
		for (Order order : allOrderList) {
			if(loginedUser.getId().equals(order.getUserId())) {
				savedBookNo = order.getBookNo();
				
				Book bookInfoByNo = bookRepository.getBookByNo(savedBookNo);
				
				LinkedHashMap<String, Object> orderDetail = new LinkedHashMap<String, Object>();
				
				int totalPrice = bookInfoByNo.getPrice() * order.getAmount();
				int savedPoint = (int) (totalPrice * 0.01);
				
				orderDetail.put("책번호", order.getBookNo());
				orderDetail.put("책제목", bookInfoByNo.getTitle());
				orderDetail.put("구매수량", order.getAmount());
				orderDetail.put("총 구매가격", df.format(totalPrice));
				orderDetail.put("적립되는 포인트", df.format(savedPoint));
				
				savedOrderDetailList.add(orderDetail);
			}
		}
		
		return savedOrderDetailList;
	}
	
	/**
	 * 로그인한 사용자의 상세정보를 조회하는 기능
	 * @return
	 */
	public User getMyInfo() {
		if(loginedUser == null) {
			throw new UserException("[안내] 로그인이 필요한 서비스입니다.");
		}
		return loginedUser;
	}
	
	/**
	 * 모든 정보를 저장하는 기능
	 */
	public void restore() {
		userRepository.saveData();
		bookRepository.saveData();
		orderRepository.saveData();
	}
}

