package collection.list;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ArrayListApp6 {

	public static void main(String[] args) {
		
		ArrayList<Product> products = new ArrayList<Product>();
		products.add(new Product(10,"노트북",1200000,120));
		products.add(new Product(100,"이어폰",400000,45));
		products.add(new Product(160,"스피커",600000,60));
		products.add(new Product(30,"태블릿",1200000,120));
		products.add(new Product(70,"갤럭시",1300000,130));
		products.add(new Product(20,"아이폰",1600000,150));
		
		System.out.println("### 상품번호순으로 정렬");
		Collections.sort(products);
		
		for(Product item : products) {
			System.out.println(item.getNo() + "\t" + item.getName() + "\t" + item.getPrice());
		}
		System.out.println();
		
		
		System.out.println("### 이름순으로 정렬");
		//Comparator<E> 인터페이스 int compare(T t1, T t2) 메소드를 익명객체로 구현하기
		Comparator<Product> nameComparator = new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getName().compareTo(o2.getName());
			}
		};
		Collections.sort(products, nameComparator);
		for(Product item : products) {
			System.out.println(item.getNo() + "\t" + item.getName() + "\t" + item.getPrice());
		}
		System.out.println();
		
		
		System.out.println("### 가격순으로 정렬");
		Comparator<Product> priceComparator = new Comparator<Product>() {
			@Override
			public int compare(Product o1, Product o2) {
				return o1.getPrice() - o2.getPrice();
			}
		};
		Collections.sort(products, priceComparator);
		for(Product item : products) {
			System.out.println(item.getNo() + "\t" + item.getName() + "\t" + item.getPrice());
		}
		System.out.println();
		
		
		System.out.println("### 적립포인트순으로 정렬하기");
		//Comparator<Product> pointComparator = (o1, o2) -> o1.getPoint() - o2.getPoint(); //interface에 추상메소드가 단 1개만 있는 것만 람다식으로 표현가능
		//Collections.sort(products, pointComparator);
		
		//pointComparator대신에 (o1, o2) -> o1.getPoint() - o2.getPoint() 적기 가능
		Collections.sort(products, (o1, o2) -> o1.getPoint() - o2.getPoint());
		//Collections.sort(products, Comparator.comparing(Product::getPoint));
		for(Product item : products) {
			System.out.println(item.getNo() + "\t" + item.getName() + "\t" + item.getPoint());
		}
		System.out.println();
		
	}
}
