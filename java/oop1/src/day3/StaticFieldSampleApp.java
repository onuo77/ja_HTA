package day3;

public class StaticFieldSampleApp {
	
	public static void main(String[] args) {
		StaticFieldSample sp1 = new StaticFieldSample();
		StaticFieldSample sp2 = new StaticFieldSample();
		StaticFieldSample sp3 = new StaticFieldSample();
		
		sp1.increase();
		sp1.increase();
		sp1.increase();
		
		sp2.increase();
		sp2.increase();
		
		sp3.increase();

		//인스턴스변수 x는 객체마다 따로 생성되기 때문에 객체의 상태에 따라서 다른 값을 가질 수 있다.
		//클래스 변수 y는 오직 한 개만 생성되기 때문에 동일한 값이 출력된다.
		sp1.print(); //x=3 y=6
		sp2.print(); //x=2 t=6
		sp3.print(); //x=1 y=6

		
	}
}
