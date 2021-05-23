package day3;

public class StaticFieldSample {

	//멤버변수, 인스턴스변수
	int x;
	
	//정적변수, 클래스변수
	static int y;
	
	//멤버메소드
	void increase() {
		x++;
		y++; //같은 클래스 내에 있을 때는 클래스명을 안붙여도됨 / 하지만 붙이는 쪽이 더 좋긴함(StaticFieldSample.y++;) 
	}
	
	//멤버메소드
	void print() {
		System.out.println("멤버변수의 x의 값 : " + x);
		System.out.println("정적변수의 y의 값 : " + y);
	}
}
