package repository;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import exception.OrderException;
import vo.Order;

public class OrderRepository {

	private List<Order> db = new ArrayList<>();
	
	public OrderRepository() {
		loadData();
	}
	
	/**
	 * 지정된 주문정보를 저장한다.
	 * @param order 주문정보
	 */
	public void insertOrder(Order order) {
		db.add(order);
	}
	
	/**
	 * 모든 주문내역을 반환한다.
	 * @return 주문내역
	 */
	public List<Order> getAllOrders() {
		return db;
	}
	
	
	private void loadData() {
		try (FileReader fileReader = new FileReader("src/orders.csv");
				BufferedReader reader = new BufferedReader(fileReader)) {
			String text = null;
			while ((text=reader.readLine()) != null) {
				String[] items = text.split(",");
				String userId = items[0];
				int bookNo = Integer.parseInt(items[1]);
				int amount = Integer.parseInt(items[2]);
				Order order = new Order(userId, bookNo, amount);
				
				db.add(order);
			}
		} catch (IOException e) {
			throw new OrderException(e.getMessage());
		}
	}
	
	public void saveData() {
		try (PrintWriter writer = new PrintWriter("src/orders.csv")) {
			for (Order order : db) {
				StringBuilder sb = new StringBuilder();
				sb.append(order.getUserId())
				.append(",")
				.append(order.getBookNo())
				.append(",")
				.append(order.getAmount());
				
				writer.println(sb.toString());
			}
		} catch (IOException e) {
			throw new OrderException(e.getMessage());
		}
	}
}
