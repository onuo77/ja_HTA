package collection.set;

import java.util.HashSet;
import java.util.Random;

public class HashSetApp2 {

	public static void main(String[] args) {
		//Integer자리에는 int(기본자료형)는 올 수 없음 클래스 객체만 가능
		HashSet<Integer> lottoNumbers = new HashSet<Integer>();
		
		Random random = new Random();
		while(true) {
			int number = random.nextInt(45)+1; //10을 넣으면 0~9까지만 출력됨, 45도 0~44 그렇기때문에 1을 더해줘야 1~45까지나옴
			lottoNumbers.add(number); //lottoNumber.add(new Integer(number));
			System.out.print(number + ", ");
			
			if(lottoNumbers.size() == 6) {
				break;
			}
		}
		System.out.println();
		System.out.println(lottoNumbers);
	}
}
