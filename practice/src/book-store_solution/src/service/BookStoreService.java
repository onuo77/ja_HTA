package service;

import java.util.ArrayList;
import java.util.HashMap;
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

	private BookRepository bookRepository = new BookRepository();
	private UserRepository userRepository = new UserRepository();
	private OrderRepository orderRepository = new OrderRepository();
	private User loginedUser = null;
	
	/**
	 * 모든 책정보를 반환한다.
	 * @return 도서 목록
	 */
	public List<Book> getAllBookList() {
		return bookRepository.getAllBooks();
	}
	
	/**
	 * 지정된 카테고리에 해당하는 책 정보들을 반환한다.
	 * @param category 조회할 도서 카테고리
	 * @return 도서 목록
	 */
	public List<Book> searchBooksByCategory(String category) {
		List<Book> result = new ArrayList<>();
		List<Book> books = bookRepository.getAllBooks();
		for (Book  book : books) {
			if (book.getCategory().equals(category)) {
				result.add(book);
			}
		}
		return result;
	}
	
	/**
	 * 지정된 책 제목을 포함하고 잇는 책 정보들을 반환한다.
	 * @param title 조회할 도서 제목
	 * @return 도서 목록
	 */
	public List<Book> searchBooksByTitle(String title) {
		List<Book> result = new ArrayList<>();
		List<Book> books = bookRepository.getAllBooks();
		for (Book  book : books) {
			if (book.getTitle().contains(title)) {
				result.add(book);
			}
		}
		return result;
	}
	
	/**
	 * 지정된 가격범위에 속하는 책 정보들을 반환한다.
	 * @param minPrice 최소 가격
	 * @param maxPrice 최대 가격
	 * @return 도서 목록
	 */
	public List<Book> searchBooksByPrice(int minPrice, int maxPrice) {
		List<Book> result = new ArrayList<>();
		List<Book> books = bookRepository.getAllBooks();
		for (Book  book : books) {
			if (book.getPrice() >= minPrice && book.getPrice() <= maxPrice) {
				result.add(book);
			}
		}
		return result;
	}
	
	/**
	 * 지정된 책번호에 해당하는 책정보를 반환한다.
	 * @param no 조회할 책 번호
	 * @return 도서
	 */
	public Book findBook(int no) {
		Book book = bookRepository.getBookByNo(no);
		if (book == null) {
			throw new BookException("책번호에 해당하는 책정보가 존재하지 않습니다.");
		}
		return book;
	}
	
	
	/**
	 * 지정된 사용자정보를 등록한다.
	 * @param user 새 사용자
	 */
	public void addNewUser(User user) {
		User savedUser = userRepository.getUserById(user.getId());
		if (savedUser != null) {
			throw new UserException("이미 사용중인 아이디입니다.");
		}
		
		userRepository.insertUser(user);
	}
	
	/**
	 * 지정된 아이디와 비밀번호로 사용자정보를 인증한다.
	 * @param id 아이디
	 * @param password 비밀번호
	 */
	public void login(String id, String password) {
		User user = userRepository.getUserById(id);
		if (user == null) {
			throw new UserException("아이디 혹은 비밀번호가 올바르지 않습니다");
		}
		if (!user.getPassword().equals(password)) {
			throw new UserException("아이디 혹은 비밀번호가 올바르지 않습니다.");
		}
		this.loginedUser = user;
	}
	
	/**
	 * 로그인된 사용자 정보를 삭제한다.
	 */
	public void logout() {
		this.loginedUser = null;
	}
	
	/**
	 * 로그인여부를 반환한다.
	 * @return 로그인된 사용자정보가 존재하면 true를 반환한다.
	 */
	public boolean isLogined() {
		return this.loginedUser == null ? false : true;
	}
	
	/**
	 * 치정된 책번호의 책을 수량만큼 주문한다.
	 * @param bookNo 책번호
	 * @param amount 주문수량
	 */
	public void orderBook(int bookNo, int amount) {
		if (loginedUser == null) {
			throw new UserException("주문작업은 로그인 후 이용가능한 서비스입니다");
		}
		Book book = bookRepository.getBookByNo(bookNo);
		if (book == null) {
			throw new BookException("책번호가 올바르지 않습니다.");
		}
		if (book.getStock() < amount) {
			throw new BookException("책 재고가 부족합니다.");
		}
		Order order = new Order(loginedUser.getId(), bookNo, amount);
		orderRepository.insertOrder(order);
		
		book.setStock(book.getStock() - amount);
		loginedUser.setPoint(loginedUser.getPoint() + (int) (book.getDiscountPrice()*amount*0.01));
		
		if (loginedUser.getPoint() >= 5000000) {
			loginedUser.setGrade("플래티넘");
		} else if (loginedUser.getPoint() >= 1000000) {
			loginedUser.setGrade("골드");
		} else if (loginedUser.getPoint() >= 100000) {
			loginedUser.setGrade("로얄");
		} else {
			loginedUser.setGrade("일반");
		}
		
	}
	
	/**
	 * 로그인한 사용자의 주문정보를 반환한다.
	 * @return 주문 목록
	 */
	public List<Map<String, Object>> getMyOrderList() {
		if (loginedUser == null) {
			throw new UserException("주문내역 조회는 로그인 후 이용가능한 서비스입니다.");
		}
		List<Map<String, Object>> myOrderHistory = new ArrayList<Map<String,Object>>();
		List<Order> orders = orderRepository.getAllOrders();
		for (Order order : orders) {
			if (order.getUserId().equals(loginedUser.getId())) {
				Book book = bookRepository.getBookByNo(order.getBookNo());
				Map<String, Object> item = new HashMap<>();
				item.put("bookNo", book.getNo());
				item.put("bookTitle", book.getTitle());
				item.put("bookPrice", book.getPrice());
				item.put("bookDiscountPrice", book.getDiscountPrice());
				item.put("orderPrice", book.getDiscountPrice()*order.getAmount());
				item.put("savedPoint", (int) (book.getDiscountPrice()*order.getAmount()*0.01));
				
				myOrderHistory.add(item);
			}
		}
		return myOrderHistory;
	}
	
	/**
	 * 로그인한 사용자의 상세정보를 반환한다.
	 * @return
	 */
	public User getMyInfo() {
		if (loginedUser == null) {
			throw new UserException("주문내역 조회는 로그인 후 이용가능한 서비스입니다.");
		}
		return loginedUser;
	}
	
	/**
	 * 모든 정보를 저장한다.
	 */
	public void restore() {
		bookRepository.saveData();
		userRepository.saveData();
		orderRepository.saveData();
	}
}

