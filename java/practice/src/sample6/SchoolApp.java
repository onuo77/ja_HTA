package sample6;

import java.util.Scanner;

public class SchoolApp {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		
		SchoolRepo repo = new SchoolRepo();
		
		while(true) {
			System.out.println("--------------------------------------------------------------");
			System.out.println("1.과정조회 2.수강신청 3.수강철회 4.수강신청내역조회 5.학생등록 6.과정개설 0.종료");
			System.out.println("--------------------------------------------------------------");
			
			System.out.print("메뉴를 선택하세요 : ");
			int menuNo = sc.nextInt();
			
			if(menuNo == 1) {
				System.out.println("[전체 개설 과정 조회]");
				repo.displayAllCourses();
				
			} else if(menuNo == 2) {
				System.out.println("[수강 신청]");
				System.out.print("* 신청하는 학생의 번호를 입력하세요 : ");
				int studentNo = sc.nextInt();
				System.out.print("* 신청할 과정 번호를 입력하세요 : ");
				int courseNo = sc.nextInt();
				
				repo.addRegistration(studentNo, courseNo);
				
			} else if(menuNo == 3) {
				System.out.println("[신청 철회]");
				System.out.print("* 신청을 취소하는 학생의 번호를 입력하세요 : ");
				int studentNo = sc.nextInt();
				System.out.print("* 신청을 취소하려는 과정 번호를 입력하세요 : ");
				int courseNo = sc.nextInt();
				
				repo.cancelRegistration(studentNo, courseNo);
				
			} else if(menuNo == 4) {
				System.out.println("[수강신청 내역 조회]");
				System.out.print("* 신청 내역을 조회할 학생 번호를 입력하세요 : ");
				int studentNo = sc.nextInt();
				
				repo.displayMyResistrations(studentNo);
				
			} else if(menuNo == 5) {
				System.out.println("[학생 등록하기]");
				System.out.print("* 학생번호를 입력하세요 : ");
				int no = sc.nextInt();
				System.out.print("* 학생이름을 입력하세요 : ");
				String name = sc.next();
				System.out.print("* 학생학과를 입력하세요 : ");
				String dept = sc.next();
				System.out.print("* 학생학년을 입력하세요 : ");
				int grade = sc.nextInt();

				Student student = new Student(no, name, dept, grade);
				repo.addStudent(student);
				
			} else if(menuNo == 6) {
				System.out.println("[과정 개설하기]");
				System.out.print("* 과정번호를 입력하세요 : ");
				int no = sc.nextInt();
				System.out.print("* 과정이름을 입력하세요 : ");
				String name = sc.next();
				System.out.print("* 과정정원을 입력하세요 : ");
				int quota = sc.nextInt();

				Course course = new Course(no, name, quota);
				repo.addCourse(course);
				
			} else if(menuNo == 0) {
				System.out.println("[ 프로그램 종료 ]");
				break;
			} else {
				System.out.println("[안내] 잘못된 번호입니다.");
			}
			
			System.out.println();
			System.out.println();
			System.out.println();
		}
		sc.close();
	}
}
