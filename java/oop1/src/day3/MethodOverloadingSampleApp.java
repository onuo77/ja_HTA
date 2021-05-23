package day3;

public class MethodOverloadingSampleApp {
	
	public static void main(String[] args) {
		
		MethodOverloadingSample sp = new MethodOverloadingSample();
		
		sp.plus(100.10, 200.0);
		sp.plus(110, 20);
		sp.plus(10, 20, 30);
		sp.plus(1010, 20.0);
		sp.plus(10.10, 2);
		
	}
}
