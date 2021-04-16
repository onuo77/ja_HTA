package collection.list;

import java.util.ArrayList;
import java.util.Iterator;

public class ArrayListApp3 {

	public static void main(String[] args) {
		
		ArrayList<String> names = new ArrayList<String>();
		
		names.add("김유신");
		names.add("강감찬");
		names.add("이순신");
		names.add("유관순");
		names.add("안창호");
		names.add("유관순");	
		names.add("안중근");
		
		System.out.println("### 이름에 '안'이 포함된 사람을 삭제하기");
		//향상된 for문 내부에서 ArrayList에 저장된 객체를 순서대로 조회중일 때
		//조회중인 그 객체를 삭제할 수 없다.		
//		for(String name : names) {
//			if(name.startsWith("안")) {
//				names.remove(name); //오류 발생
//			}
//		}
		
		Iterator<String> iter = names.iterator();
		while(iter.hasNext()) { //다음에 꺼낼 데이터가 있는지 없는지 boolean값을 반환
			String name = iter.next();
			if(name.startsWith("안")) {
				 iter.remove();
			}
		}
		
		//iterator는 소모성(일회용)이기 때문에 위에서 한번 밖에 사용이 안됨
		iter = names.iterator();//재사용하고 싶을 경우 이렇게 해서 재사용 가능
		System.out.println("Iterator로 사람이름 출력하기");
		while(iter.hasNext()) {	
			System.out.println(iter.next());//Iterator는 일회용이기때문에 위에서 사용해서 출력 안됨
		}
		
		System.out.println("향상된 for문으로 사람이름 출력하기");
		for(String name : names) {
			System.out.println(name);
		}
	}
}
