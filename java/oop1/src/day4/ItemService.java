package day4;

public class ItemService {
	
	Item[] items = new Item[100];
	int position = 0;
	
	ItemService(){
		//items 배열에 Item객체 5개 등록하기
		//items[position++]=new Item(100, "노트북","맥북", "애플", 1900000);
		items[position++] = new Item(100, "노트북", "맥북", "애플", 1900000);
		items[position++] = new Item(200, "노트북", "갤럭시북", "삼성", 2000000);
		items[position++] = new Item(300, "TV", "파브", "LG", 2200000);
		items[position++] = new Item(400, "이어폰", "에어팟", "애플", 250000);
		items[position++] = new Item(500, "이어폰", "갤럭시버즈", "삼성", 100000);
		
	}
	
	//items 배열에 저장된 모든 Item객체의 번호, 상품명, 가격을 출력한다.
	void printAllItem(){
		System.out.println("###### 상품정보 출력 ######");
		for (Item item : items) {
			if(item == null) {
				break;
			}
			
			System.out.println("상품번호 : " + item.no);
			System.out.println("상 품 명 : " + item.name);
			System.out.println("가   격 : " + item.price);
			System.out.println();
		}
		System.out.println("=======================================");
	}
	
	//items 배열에 저장된 Item객체 중에서 전달받은 Category에 속하는 Item객체의 번호, 상품명, 가격을 출력한다.
	void printItemByCategory(String category) {
		System.out.println("###### 상품카테고리 검색결과 ######");
		String foundCategory = null;
		
		for (Item item : items) {
			if(item == null) {
				break;
			}
			if(category.equals(item.category)) {
				foundCategory = category;
				System.out.println("[" + category + "]의 상품정보");
				System.out.println("상품번호 : " + item.no);
				System.out.println("상 품 명 : " + item.name);
				System.out.println("가   격 : " + item.price);
				System.out.println();
			} 
		}
		
		if(foundCategory == null){
			System.out.println("[" + category + "] 카테고리가 존재하지 않습니다.");
		}
		
		System.out.println("=======================================");

	}
	
	//items 배열에 저장된 Item객체 중에서 전달받은 상품명을 포함하고 있는 Item객체의 번호, 상품명, 가격을 출력한다.
	//.equals(name)은 상품명이 name과 일치하면 true
	//.contains(name)은 상품명에 name이 포함되어 있으면 true
	void printItemByName(String name) {
		System.out.println("###### 상품명 검색결과 ######");
		String foundName = null;
		
		for (Item item : items) {
			if(item == null) {
				break;
			}
			if(item.name.contains(name)) {
				foundName = name;
				System.out.println("[" + name + "]의 상품정보");
				System.out.println("상품번호 : " + item.no);
				System.out.println("상 품 명 : " + item.name);
				System.out.println("가   격 : " + item.price);
				System.out.println();
			} 
		}
		
		if(foundName == null){
			System.out.println("[" + name + "] 상품이 존재하지 않습니다.");
		}
		
		System.out.println("=======================================");

	}
	
	//items 배열에 저장된 Item객체 중에서 전달받은 가격범위에 속하는 Item객체의 번호, 상품명, 가격을 출력한다.
	void printItemByPrice(int minPrice, int maxPrice) {
		System.out.println("###### 상품가격 검색결과 ######");
		int foundPrice = 0;
		
		for (Item item : items) {
			if(item == null) {
				break;
			}
			if(item.price >= minPrice && item.price <= maxPrice) {
				foundPrice = minPrice;
				System.out.println("[" + minPrice + " ~ " + maxPrice + "] 가격의 상품정보");
				System.out.println("상품번호 : " + item.no);
				System.out.println("상 품 명 : " + item.name);
				System.out.println("가   격 : " + item.price);
				System.out.println();
			} 
		}
		
		if(foundPrice == 0){
			System.out.println("[" + minPrice + " ~ " + maxPrice + "] 가격의 상품이 존재하지 않습니다.");
		}
		
		System.out.println("=======================================");
	}
	
	//items 배열에 저장된 Item객체 중에서 전달받은 번호와 일치하는 Item객체의 모든 정보를 출력한다.
	void printItemDetail(int no) {
		System.out.println("###### 상품번호 조회결과 ######");
		int foundNo = 0;
		
		for (Item item : items) {
			if(item == null) {
				break;
		}
			if(no == item.no) {
				foundNo = no;
				System.out.println("[" + no + "] 번호의 상품정보");
				System.out.println("상품번호 : " + item.no);
				System.out.println("카테고리 : " + item.category);
				System.out.println("상 품 명 : " + item.name);
				System.out.println("제 조 사 : " + item.maker);
				System.out.println("가   격 : " + item.price);
				System.out.println();
			} 
		}
		
		if(foundNo == 0){
			System.out.println("[" + no + "] 번호의 상품이 존재하지 않습니다.");
		}
		
		System.out.println("=======================================");
	}
	
	//items 배열에 전달받은 Item 객체를 등록한다.
	void insertItem(Item item) {
		items[position++] = item;			
		System.out.println("새상품등록이 완료 되었습니다.");
	}
	
	// items 배열에서 전달받은 번호에 해당하는 Item객체를 제거한다.
	// 제거된 자리에는 맨마지막번째에 저장된 Item객체를 옮긴다.(조금 어려움)
	void deleteItem(int no) {
		System.out.println("###### 상품번호에 따른 삭제결과 ######");
		int deleteNo = 0;
		int indexNum = 0;
		
		for(int i=0; i<items.length; i++) {
			if(items[i] == null) {
				break;
			}
			if(no == items[i].no) {
				indexNum = i;
				deleteNo = items[i].no;
			}
		}
		
		if(deleteNo == no) {
			for(int i=indexNum; i<items.length-1; i++) {				
				items[i] = items[i+1];
				position = position-1;
			}			
			System.out.println("[" + no + "] 상품이 삭제 되었습니다.");
		}else {
			System.out.println("[" + no + "] 번호가 존재하지 않아 삭제에 실패했습니다.");
		}		
		
	}
	
}
