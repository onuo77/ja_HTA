package day1;

public class ProductApp {
	
	public static void main(String[] args) {
		
		//상품정보를 표현하는 객체를 생성하고, 객체의 멤버변수에 적절한 상품정보를 저장한다.
		Product p1 = new Product();
		
		p1.no = 1; //앞에 00넣으면 8진수로 인식함
		p1.category = "IT/컴퓨터";
		p1.name = "자바의 정석";
		p1.maker = "도우출판";
		p1.price = 25000;
		p1.stock = 60;
		//p1.isSoldOut = false;
		
		//위에서 생성한 상품객체에 저장된 상품정보를 화면에 출력하는 메소드를 실행한다.
		p1.printInfo();
	}
}
