package sample5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

public class App {
	
	public static void printStudentInfo(ArrayList<Student> students) {
		if(students.isEmpty()) {
			System.out.println("* 등록된 학생 정보가 존재하지 않습니다.");
		}else {
			for (Student student : students) {
				System.out.println(student.getName()+"\t"
								   +student.getKor()+"\t"
								   +student.getEng()+"\t"
								   +student.getMath()+"\t"
								   +student.getTotal()+"\t"
								   +student.getAverage());
			}
			
		}
		
	}
	
	public static void main(String[] args) {
		/*
		 * 학생성적 관리 프로그램
		 * 1. 학생 성적정보 입력기능
		 * 		이름, 국어, 영어, 수학점수를 입력받아서 ArrayList에 저장
		 * 2. 학생 성적정보 전체 조회 기능
		 * 		ArrayList에 저장된 학생들의 이름, 총점, 평균을 출력
		 * 3. 학생 성적정보 상세 조회 기능
		 * 		학생이름을 입력받아서 그 학생의 이름, 국어, 영어, 수학, 총점, 평균을 출력
		 * 4. 학생 성적정보 성적순 출력 기능 (고득점 -> 저득점 : 총점기준)
		 * 		Comparable<T> 혹은 Comparator<T>를 이용해서 총점이 높은 학생부터 낮은 학생순으로 출력
		 */
		
		ArrayList<Student> students = new ArrayList<Student>();
		Scanner sc = new Scanner(System.in);
		
		while(true) {
			System.out.println("--------------------------------------------------------------------");
			System.out.println("[학생성적 관리 프로그램]");
			System.out.println("1.학생성적 입력 2.학생성적 전체조회 3.학생성적 상세조회 4.학생 성적정보 성적순 조회 0.종료");
			System.out.println("--------------------------------------------------------------------");
			
			System.out.print("원하는 서비스를 선택하세요 : ");
			int menuNo = sc.nextInt();
			
			if(menuNo == 1) {
				System.out.println("[학생 성적정보 입력]");
				int total = 0;
				int avg = 0;
				
				System.out.print("* 학생 이름을 입력하세요 : ");
				String name = sc.next();
				
				System.out.print("* ["+ name +"]의 국어점수를 입력하세요 : ");
				int kor = sc.nextInt();
				
				System.out.print("* ["+ name +"]의 영어점수를 입력하세요 : ");
				int eng = sc.nextInt();
				
				System.out.print("* ["+ name +"]의 수학점수를 입력하세요 : ");
				int math = sc.nextInt();

				Student newStudent = new Student(name, kor, eng, math, total, avg);
				students.add(newStudent);
				
				System.out.println("* ["+ name +"]의 성적정보가 등록되었습니다.");
				
			} else if(menuNo == 2) {
				System.out.println("[학생성적 전체조회]");
				System.out.println("학생이름\t국어점수\t영어점수\t수학점수\t총점\t평균");

				App.printStudentInfo(students);
				
			} else if(menuNo == 3) {
				System.out.println("[학생성적 상세조회]");
				System.out.print("* 성적조회할 학생이름을 입력하세요 : ");
				String name = sc.next();
				boolean isExist = false;
				
				System.out.println("학생이름\t국어점수\t영어점수\t수학점수\t총점\t평균");
				for(Student student : students) {
					if(student.getName().equals(name)) {
						isExist = true;
						System.out.println(student.getName()+"\t"
										   +student.getKor()+"\t"
										   +student.getEng()+"\t"
										   +student.getMath()+"\t"
										   +student.getTotal()+"\t"
										   +student.getAverage());
					}
				}
				if(!isExist) {
					System.out.println("* [" + name + "] 학생이 존재하지 않습니다.");
				}
				
			} else if(menuNo == 4) {
				System.out.println("[학생 성적정보 성적순 조회]");
				System.out.println("학생이름\t국어점수\t영어점수\t수학점수\t총점\t평균");

//				Comparator<Student> studentTotalScore = new Comparator<Student>() {
//					@Override
//					public int compare(Student o1, Student o2) {
//						return o1.getTotal()-o2.getTotal();
//					}
//				};
//				Comparator<Student> studentTotalScore = (o1, o2) -> o1.getTotal()-o2.getTotal();
				
				Collections.sort(students, (o1, o2) -> o1.getTotal()-o2.getTotal());
				Collections.reverse(students);
				
				if(students.isEmpty()) {
					System.out.println("* 성적 등록된 학생이 존재하지 않습니다.");
				}else {
					for (Student student : students) {
						System.out.println(student.getName()+"\t"
											+student.getKor()+"\t"
											+student.getEng()+"\t"
											+student.getMath()+"\t"
											+student.getTotal()+"\t"
											+student.getAverage());
					}
				}
				
			} else if(menuNo == 0) {
				System.out.println("[학생성적 관리 프로그램을 종료합니다.]");
				break;
			}
			
		}
	}
}
