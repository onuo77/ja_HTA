package sample1;

import java.util.Scanner;

public class App {
	
	public static class Student {
		private String name;
		private int kor;
		private int eng;
		private int math;
		
		public Student(String name, int kor, int eng, int math) {
			this.name = name;
			this.kor = kor;
			this.eng = eng;
			this.math = math;
		}
		
		public String getName() {
			return name;
		}
		public int getKor() {
			return kor;
		}
		public int getEng() {
			return eng;
		}
		public int getMath() {
			return math;
		}
		
	}
	
	public static void main(String[] args) {
		// 학생들의 정보를 입력받아서 배열에 저장하기
		
		// 1. 5명의 학생정보를 저장할 수 있는 배열을 정의한다.
		// 2. 키보드 입력을 읽어오는 Scanner객체를 생성한다.
		// 3. 반복문(for문)을 사용해서 5회 반복한다.
		//		1. 이름, 국어, 영어, 수학 점수를 차례로 입력받는다.
		//		2. Student객체를 생성해서 입력받은 값을 저장한다.
		//		2. 배열에 순서대로 Student객체를 저장한다.
		// 4. 배열에 저장된 학생들의 이름을 반복문(향상된 for문)을 사용해서 출력한다.
		
		Student[] students = new Student[5];
		Scanner sc = new Scanner(System.in);
		
		for(int i=0; i<students.length; i++) {
			System.out.println("이름을 입력하세요");
			String name = sc.next();
			System.out.println("국어점수를 입력하세요");
			int kor = sc.nextInt();
			System.out.println("영어점수를 입력하세요");
			int eng = sc.nextInt();
			System.out.println("수학점수를 입력하세요");
			int math = sc.nextInt();
			Student studentInfo = new Student(name, kor, eng, math);
			
			students[i] = studentInfo;
		}
		
		for (Student student : students) {
			System.out.println("###학생 성적 출력###");
			System.out.println("이름 : " + student.getName());
			System.out.println("국어점수 : " + student.getKor());
			System.out.println("영어점수 : " + student.getEng());
			System.out.println("수학점수 : " + student.getMath());
		}
	}
}
