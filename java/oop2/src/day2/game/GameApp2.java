package day2.game;

import java.util.Scanner;

public class GameApp2 {
	
	public static void main(String[] args) {
	
		Scanner sc = new Scanner(System.in);
		
		Marine unit1 = new Marine();
		Firebat unit2 = new Firebat();
		Ghost unit3 = new Ghost();
		BattleCruiser unit4 = new BattleCruiser();
		
		unit1.setName("마린");
		unit2.setName("파이어뱃");
		unit3.setName("고스트");
		unit4.setName("배틀크루저");
		
		//선택된 유닛객체(Marine, Firebat, Ghost, BattleCruiser 객체)을 저장하는 변수
		Unit selectedUnit = null; 

		while(true) {
			System.out.println("-----------------------");
			System.out.println("1.공격 2.이동 3.선택 0.종료");
			System.out.println("-----------------------");
			
			System.out.print("메뉴번호를 입력하세요 : ");
			int menuNo = sc.nextInt();
			
			if(menuNo == 1) {
				if(selectedUnit == null) {
					System.out.println("[경고] 선택된 유닛이 없습니다.");
					continue;
				}
				System.out.println("선택된 유닛은 ["+selectedUnit.getName()+"] 입니다.");
				selectedUnit.attack();
				
			} else if(menuNo == 2) {
				if(selectedUnit == null) {
					System.out.println("[경고] 선택된 유닛이 없습니다.");
					continue;
				}
				System.out.println("선택된 유닛은 ["+selectedUnit.getName()+"] 입니다.");
				selectedUnit.move();
				
			} else if(menuNo == 3) {
				System.out.println("유닛을 선택하세요:(1-마린, 2-파이어뱃, 3-고스트, 4-배틀크루저)");
				int unitNumber = sc.nextInt();
				if(unitNumber == 1) {
					selectedUnit = unit1;
				} else if(unitNumber == 2) {
					selectedUnit = unit2;
				} else if(unitNumber == 3) {
					selectedUnit = unit3;
				} else if(unitNumber == 4) {
					selectedUnit = unit4;
				}
				
			} else if(menuNo == 0) {
				System.out.println("[종료] 게임을 종료합니다.");
				break;
			} 
			
			System.out.println("");
			System.out.println("");
			System.out.println("");
			System.out.println("");
			
		}
		
	}
}
