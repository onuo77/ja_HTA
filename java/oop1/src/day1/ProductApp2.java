package day1;

public class ProductApp2 {
	public static void main(String[] args) {

		//상품정보를 3개 저장하는 배열객체를 생성하기
		Product[] products = new Product[3];
		System.out.println("### 배열 생성 직후 배열의 각 칸에 저장된 값 출력하기");
		System.out.println("0번째 칸 : " + products[0]);
		System.out.println("1번째 칸 : " + products[1]);
		System.out.println("2번째 칸 : " + products[2]);
		
		System.out.println();
		
		//상품정보를 담을 수 있는 Product객체 3개 생성하기
		Product p1 = new Product();
		p1.no = 100;
		p1.category = "스마트폰";
		p1.name = "아이폰12";
		p1.maker = "애플";
		p1.price = 1500000;
		p1.stock = 20;
		p1.isSoldOut = false;
		
		Product p2 = new Product();
		p2.no = 110;
		p2.category = "노트븍";
		p2.name = "LG그램";
		p2.maker = "LG";
		p2.price = 2500000;
		p2.stock = 10;
		p2.isSoldOut = false;
		
		Product p3 = new Product();
		p3.no = 130;
		p3.category = "스마트워치";
		p3.name = "애플워치5";
		p3.maker = "애플";
		p3.price = 600000;
		p3.stock = 30;
		p3.isSoldOut = false;
		
		System.out.println("### 참조변수가 참조하는 객체의 정보 출력하기");
		System.out.println("p1 참조하는 객체 : " + p1);
		System.out.println("p2 참조하는 객체 : " + p2);
		System.out.println("p3 참조하는 객체 : " + p3);
		
		System.out.println();
		
		//배열의 0번째 칸부터 2번째 칸에 생성된 Product객체를 저장하기
		products[0] = p1; //p1참조변수가 참조하는 객체의 주소값을 배열의 0번째 칸에 대입함
		products[1] = p2; //p2참조변수가 참조하는 객체의 주소값을 배열의 1번째 칸에 대입함
		products[2] = p3; //p3참조변수가 참조하는 객체의 주소값을 배열의 2번째 칸에 대입함
		
		System.out.println("### 배열의 각 칸에 Product객체를 저장한 후 각 칸에 저장된 값 출력하기");
		System.out.println("0번째 칸 : " + products[0]);
		System.out.println("1번째 칸 : " + products[1]);
		System.out.println("2번째 칸 : " + products[2]);
		
		System.out.println();
		
		//모든상품의 이름을 화면에 출력하기
		Product px = products[0]; //products배열의 0번째 칸에 저장된 Product객체의 주소값을 px에 대입
		Product py = products[1]; //products배열의 1번째 칸에 저장된 Product객체의 주소값을 px에 대입
		Product pz = products[2]; //products배열의 2번째 칸에 저장된 Product객체의 주소값을 px에 대입
		
		System.out.println("### 배열에 각 칸에 저장된 Product객체의 상품이름을 출력하기");
		System.out.println("0번째 칸에 저장된 상품객체의 이름 : " + px.name);
		System.out.println("1번째 칸에 저장된 상품객체의 이름 : " + py.name);
		System.out.println("2번째 칸에 저장된 상품객체의 이름 : " + pz.name);
		System.out.println("0번째 칸에 저장된 상품객체의 이름 : " + products[0].name);
		System.out.println("1번째 칸에 저장된 상품객체의 이름 : " + products[1].name);
		System.out.println("2번째 칸에 저장된 상품객체의 이름 : " + products[2].name);
		
		System.out.println();
		
		//모든 상품의 이름을 향상된 for문을 사용해서 출력하기
		/*
		 * for(Product x : products)  //products -> [#222, #333, #444]
		 * {System.out.println(x.name);} //x에는 #222, #222로 참조되는 객체의 name값이 출력된다. 
		 * {System.out.println(x.name);} //x에는 #333, #333로 참조되는 객체의 name값이 출력된다. 
		 * {System.out.println(x.name);} //x에는 #444, #444로 참조되는 객체의 name값이 출력된다. 
		 */
		System.out.println("### 향상된 for문을 사용해서 배열에 저장된 모든 상품의 이름을 출력하기 ###");
		
		for(Product x : products) { //products -> [#222, #333, #444]
			String productName = x.name;
			int productPrice = x.price;
			int productStock = x.stock;
			System.out.println(productName + "," + productPrice + "," + productStock);
			System.out.println("상품명 : " + x.name); //x에는 #222, #222로 참조되는 객체의 name값이 출력된다.
		}
		
		System.out.println();
		
		System.out.println("### products 배열에 저장된 모든 상품정보 출력하기 ###");
		for(Product y : products) {
			y.printInfo();
			System.out.println(y);
		}
		
		System.out.println();
		
		System.out.println("### products 배열에 저장된 상품중에서 재고수량이 25개 이상인 상품의 정보만 출력하기");
		for(Product z : products) {
			if(z.stock >= 25) {
				z.printInfo();
			}
		}
		
		System.out.println();
		
		System.out.println("### products 배열에 저장된 상품중에서 제품의 가격이 100만원 이상인 상품의 정보만 출력하기");
		for(Product z : products) {
			if(z.price >= 1000000) {
				z.printInfo();
			}
		}
		
		System.out.println();
		
		System.out.println("### products 배열에 저장된 상품중에서 애플에서 제조한 상품정보만 출력하기");
		/*
		 * for(Product z : products) { if(z.maker.equals("애플")) { z.printInfo(); } }
		 */
		//위에 방법보다 아래 방법으로 하는게 좋음!!
		for(Product item : products) {
			if("애플".equals(item.maker)) {
				item.printInfo();
			}
		}
	}
}
