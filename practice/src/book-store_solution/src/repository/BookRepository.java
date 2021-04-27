
package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import exception.BookException;
import vo.Book;

public class BookRepository {

	private List<Book> db = new ArrayList<>();
	
	public BookRepository() {
		loadData();
	}
	
	/**
	 * 모든 책정보를 반환한다.
	 * @return 도서 목록
	 */
	public List<Book> getAllBooks() {
		return db;
	}
	
	/**
	 * 지정된 책번호의 책을 반환한다.
	 * @param no 책번호
	 * @return 도서
	 */
	public Book getBookByNo(int no) {
		for (Book book : db) {
			if (book.getNo() == no) {
				return book;
			}
		}
		return null;
	}
	
	private void loadData() {
		try (FileReader fileReader = new FileReader("src/books.csv");
				BufferedReader reader = new BufferedReader(fileReader)) {
			String text = null;
			while ((text=reader.readLine()) != null) {
				String[] items = text.split(",");
				int no = Integer.parseInt(items[0]);
				String category = items[1];
				String title = items[2];
				String writer = items[3];
				String publisher = items[4];
				int price = Integer.parseInt(items[5]);
				int discountPrice = Integer.parseInt(items[6]);
				int stock = Integer.parseInt(items[7]);
				
				Book book = new Book(no, category, title, writer, publisher, price, discountPrice, stock);
				db.add(book);
			}
		} catch (IOException e) {
			throw new BookException(e.getMessage());
			
		}
	}
	
	public void saveData() {
		try (PrintWriter writer = new PrintWriter("src/books.csv")) {
			for (Book book : db) {
				StringBuilder sb = new StringBuilder();
				sb.append(book.getNo())
				.append(",")
				.append(book.getCategory())
				.append(",")
				.append(book.getTitle())
				.append(",")
				.append(book.getWriter())
				.append(",")
				.append(book.getPublisher())
				.append(",")
				.append(book.getPrice())
				.append(",")
				.append(book.getDiscountPrice())
				.append(",")
				.append(book.getStock());
				
				writer.println(sb.toString());
			}
		} catch (IOException e) {
			throw new BookException(e.getMessage());
		}
	}
}
