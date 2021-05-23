package step6;

public class Order {

	private String name; 		// 상품이름
	private String username;	// 주문자명
	private int price;			// 상품금액
	private int amount;			// 주문량
	
	public Order() {}

	public Order(String name, String username, int price, int amount) {
		super();
		this.name = name;
		this.username = username;
		this.price = price;
		this.amount = amount;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}
	
	
}
