package day4;

public class ItemService2 {
	Item[] items = new Item[100];
	int position = 0;
		
		// 기본생성자
		// items 배열에 샘플아이템을 미리 등록한다.
		ItemService2(){
			//items 배열에 Item객체 5개 등록하기
			//items[position++]=new Item(100, "노트북","맥북", "애플", 1900000);
			items[position++] = new Item(101, "노트북", "맥북", "애플", 1900000);
			items[position++] = new Item(102, "노트북", "맥북에어", "애플", 1200000);
			items[position++] = new Item(103, "노트북", "맥북프로", "애플", 2500000);
			items[position++] = new Item(104, "노트북", "LG그램", "LG", 2000000);
			items[position++] = new Item(105, "노트북", "소니바이오", "소니", 1400000);
			items[position++] = new Item(106, "노트북", "씽크패드", "레노버", 2800000);
			items[position++] = new Item(107, "스마트폰", "iphone12", "애플", 1400000);
			items[position++] = new Item(108, "스마트폰", "iphoneSE", "애플", 600000);
			items[position++] = new Item(109, "스마트폰", "갤럭시S20", "삼성", 1300000);
			items[position++] = new Item(110, "워치", "애플워치", "애플", 500000);
			items[position++] = new Item(111, "워치", "갤럭시워치", "삼성", 450000);
			items[position++] = new Item(112, "워치", "가민945", "가민", 750000);
		}
		
		//items 배열에 저장된 모든 Item객체의 번호, 상품명, 가격을 출력한다.
		void printAllItem(){
			System.out.println("--- 전체 아이템 목록 -----------------");
			for(Item item : items) {
				if(item == null) {
					break;
				}
				System.out.println(item.no + "\t" + item.name + "\t" + item.price);
			}
			System.out.println("----------------------------------");
		}
		
		//items 배열에 저장된 Item객체 중에서 전달받은 Category에 속하는 Item객체의 번호, 상품명, 가격을 출력한다.
		void printItemByCategory(String category) {
			boolean isExist = false;
			
			System.out.println("---" + category + "아이템 목록 -----------------");
			
			for(Item item : items) {
				if(item == null) {
					break;
				}
				if(item.category.equals(category)) {
					isExist = true;
					System.out.println(item.no + "\t" + item.name + "\t" + item.price);
				}
			}
			if(!isExist) {
				System.out.println("[" + category + "]에 해당하는 아이템은 존재하지 않습니다.");
			}
			System.out.println("----------------------------------");
		}
		
		//items 배열에 저장된 Item객체 중에서 전달받은 상품명을 포함하고 있는 Item객체의 번호, 상품명, 가격을 출력한다.
		//.equals(name)은 상품명이 name과 일치하면 true
		//.contains(name)은 상품명에 name이 포함되어 있으면 true
	    void printItemByName(String name) {
			boolean isExist = false;
			
			System.out.println("----------------------------------");
			
			for(Item item : items) {
				if(item == null) {
					break;
				}
				
				if(item.name.contains(name)) {
					isExist = true;
					System.out.println(item.no + "\t" + item.name + "\t" + item.price);
				}
			}
			
			if(!isExist) {
				System.out.println("상품명에 ["+name+"]이 포함된 아이템은 존재하지 않습니다.");
			}
			
			System.out.println("----------------------------------");

		}
		
		//items 배열에 저장된 Item객체 중에서 전달받은 가격범위에 속하는 Item객체의 번호, 상품명, 가격을 출력한다.
		void printItemByPrice(int minPrice, int maxPrice) {
			boolean isExist = false;
			
			System.out.println("----------------------------------");
			
			for(Item item : items) {
				if(item == null) {
					break;
				}
			
				if(item.price >= minPrice && item.price <= maxPrice) {
					isExist = true;
					System.out.println(item.no + "\t" + item.name + "\t" + item.price);
				}
			}	
			
			if(!isExist) {
				System.out.println("상품명에 ["+minPrice+ "~"+ maxPrice +"]이 포함된 아이템은 존재하지 않습니다.");
			}
			
			System.out.println("----------------------------------");
				
		}
		
		//items 배열에 저장된 Item객체 중에서 전달받은 번호와 일치하는 Item객체의 모든 정보를 출력한다.
		//Item마다 고유한 아이템번호를 가지고 있다.
		//아이템 번호로 아이템을 조회하면 한 개만 조회되거나, 조회되지 않는다.
		void printItemDetail(int no) {
			Item foundItem = null;
			System.out.println("----------------------------------");

			for(Item item : items) {
				if(item == null) {
					break;
				}
				if(item.no == no) {
					foundItem = item;
					break;
				}
			}
			
			if(foundItem == null) {
				System.out.println("아이템번호 ["+no+"]에 해당하는 아이템은 존재하지 않습니다.");				
			} else {
				System.out.println("번호 : " + foundItem.no);
				System.out.println("카테고리 : " + foundItem.category);
				System.out.println("이름 : " + foundItem.name);
				System.out.println("제조사 : " + foundItem.maker);
				System.out.println("가격 : " + foundItem.price);
			}
			
			System.out.println("----------------------------------");
		}
		
		//items 배열에 전달받은 Item 객체를 등록한다.
		void insertItem(Item item) {
			items[position++] = item;
			System.out.println("### 신규 아이템이 등록되었습니다. ###");
		}
		
		// items 배열에서 전달받은 번호에 해당하는 Item객체를 제거한다.
		// 제거된 자리에는 맨마지막번째에 저장된 Item객체를 옮긴다.(조금 어려움)
		/*
		 * 1. 맨 마지막에 저장된 아이템을 삭제하기
		 * 	 - 해당 위치의 칸을 null로 만든다.
		 * 	 - position을 1감소시킨다.
		 * 
		 * 2. 중간에 위치한 아이템을 삭제하기
		 * 	 - 해당 위치의 칸을 null로 만든다.
		 *   - 맨 마지막에 위치한 아이템을 해당위치로 옮긴다.
		 *   - 맨 마지막 칸을 null로 만든다.
		 *   - position을 1감소시킨다.
		 */
		void deleteItem(int no) {
			//삭제할 아이템이 저장된 인덱스를 저장하는 변수
			int foundItemIndex = -1; //배열은 0번째부터라 나올 수 없는 값 설정
			
			//향상된 for문을 사용해서 배열에 저장된 Item객체를 순서대로 하나씩 조회하는 동안
			//지금 조회된 Item객체가 배열의 몇번째 칸에서 조회된 아이템인지를 저장하는 변수
			int index = 0; //배열에서 조회한 아이템의 현재 순번을 저장하는 변수
			
			//items배열에서 Items객체의 주소값을 순서대로 하나씩 가져옴
			for (Item item : items) {
				//가져온 주소값이 null이면 더이상 저장된 Item객체가 저장되어 있지 않은 것이기 때문에
				//for문을 탈출함
				if(item == null) {
					break;
				}
				
				//배열에서 가져온 Item객체의 no와 전달받은 번호(no)가 일치하면
				//foundItemIndex에 지금 조회된 Item객체의 저장위치를 foundItemIndex에 저장한다.
				if(item.no == no) {
					foundItemIndex = index;
					break;
				}
				index++;
			}
			
			//foundItemIndex값이 -1이면 아이템번호와 일치하는 아이템이 배열에 존재하지 않
			if(foundItemIndex == -1) {
				System.out.println("아이템번호 ["+no+"]에 해당하는 아이템은 존재하지 않습니다.");				
				return; //메소드의 실행을 즉시 종료하고,(이 메소드를 호출한 측으로 제어권이 넘어간다.)
			} 
			
			//foundItemIndex가 -1이 아닌 경우 아래의 수행문이 실행된다.
			//lastItemSaveIndex는 items 배열에서 Item객체가 저장된 맨 마지막 Index를 저장하는 변수다.
			int lastItemSaveIndex = position - 1;
			
			//foundItemIndex와 lastItemSaveIndex가 같으면 지금 삭제할 Item객체는
			//맨 마지막으로 저장된 Item객체다.
			if(foundItemIndex == lastItemSaveIndex) {
				
				//삭제할 아이템이 맨 마지막에 저장되어 있다.
				// -배열에서 foundItemIndex 칸을 null로 만든다.
				// -position값을 1감소시킨다.
				items[foundItemIndex] = null;
				position--;
				
			}else {
				//삭제할 아이템이 중간에 저장되어 있다.
				// -배열에서 foundItemIndex 칸을 null로 만든다.
				// -맨 마지막으로 저장된 Item객체의 주소값을 foundItemIndex칸에 대입한다.
				// -배열에서 맨 마지막으로 Item객체가 저장되었던 칸을 null로 만든다.
				// -position값을 1감소시킨다.
				items[foundItemIndex] = null; //찾은곳을 null로 만들고
				items[foundItemIndex] = items[lastItemSaveIndex]; //찾은 곳에 마지막position위치값을 넣어주고
				items[lastItemSaveIndex] = null; //마지막 position은 중복이기때문에 null로 삭제
				position--; //position을 하나 감소시켜 배열을 줄인다.
			}
			
			
		}

}
