package sample6;

import java.util.ArrayList;
import java.util.Iterator;

public class SchoolRepo {

	private ArrayList<Student> students = new ArrayList<Student>();
	private ArrayList<Course> courses = new ArrayList<Course>();
	private ArrayList<Registration> registrations = new ArrayList<Registration>();

	public SchoolRepo() {
		//학생등록
		students.add(new Student(10, "홍길동", "컴퓨터공학과", 2));
		students.add(new Student(11, "김유신", "컴퓨터공학과", 1));
		students.add(new Student(12, "강감찬", "컴퓨터공학과", 2));
		students.add(new Student(13, "이순신", "정보통신학과", 1));
		students.add(new Student(14, "유관순", "정보통신학과", 2));
		
		//과정등록
		courses.add(new Course(100, "컴퓨터개론", 5));
		courses.add(new Course(101, "소프트웨어공학", 5));
		courses.add(new Course(102, "데이터베이스", 5));
		courses.add(new Course(103, "운영체제", 5));
		courses.add(new Course(104, "컴퓨터구조", 5));
		courses.add(new Course(105, "이산수학", 5));
		courses.add(new Course(106, "프로그래밍 기초", 5));
	}

	//학생 등록 기능
	//학생정보를 전달받아서 students가 참조하는 ArrayList객체에 저장하는 기능
	public void addStudent(Student student) {
		students.add(student);
		System.out.println("* ["+student.getName()+"] 학생이 등록되었습니다.");
	}
	
	//과정 등록 기능
	//과정정보를 전달받아서 courses가 참조하는 ArrayList객체에 저장하는 기능
	public void addCourse(Course course) {
		courses.add(course);
		System.out.println("* ["+course.getName()+"] 과정이 등록되었습니다.");
	}
	
	//과정 조회 기능
	//courses가 참조하는 ArrayList객체에 저장된 모든 과정정보를 출력하는 기능
	public void displayAllCourses() {
		//과정번호, 과정명, 정원, 현재신청자수
		System.out.println("과정번호\t과정이름\t\t과정정원\t현재신청자수");
		for(Course course : courses) {
			System.out.printf("%-8s%-10s%5d%10d\n",course.getNo(),course.getName(),course.getQuota(),course.getRegisteredCount());
		}
	}
	
	//과정 신청 기능
	//학생번호, 과정번호를 전달받아서 registrations가 참조하는 ArrayList객체에 저장하는 기능
	//*이미 신청한 과목은 신청할 수 없음
	//*정원이 부족하면 신청할 수 없음
	//*신청자수를 1증가시킨다.
	public void addRegistration(int studentNo, int courseNo) {
		//1. 학생번호에 해당하는 학생이 그 과정을 신청했는지 확인하기
		//2. 그 과정에 정원이 충분한지 확인하기
		//3. registrations가 참조하는 ArrayList에 등록정보 추가하기
		//4. 해당 과정의 신청자수를 1증가시키기

		//올바르지 않은 경우 먼저 체크해
		//이미 신청한 과정이면 메소드 실행을 중단
		//정원이 다 채워져있으면 메소드 실행을 중단
		for(Registration reg : registrations) {
			if(reg.getStudNo() == studentNo && reg.getCourseNo() == courseNo) {
				System.out.println("[안내] 해당 과정은 이미 수강신청한 과정입니다.");
				return;//메소드 실행 즉시 중단
			}
		}
		
		Course foundCourse = null;
		for(Course course : courses) {
			if(course.getNo() == courseNo) {
				foundCourse = course;//과정번호에 해당하는 과정을 found
				break; //반복탈출
			}
		}
		
		if(foundCourse.getQuota() == foundCourse.getRegisteredCount()) {
			System.out.println("[안내] 해당 과정은 모집정원이 다 채워졌습니다.");
			return;
		}

		// 수강신청정보 생성, 학번, 과정번호 저장
		// 새 수강신청정보 ArrayList에 저장
		if(foundCourse != null) {
			registrations.add(new Registration(studentNo, courseNo));
			System.out.println("* 과정이 등록되었습니다.");
		}
		
		//해당과정의 신청자수 1 증가
		foundCourse.setRegisteredCount(foundCourse.getRegisteredCount()+1);
		
//		for(Course course : courses) {
//			if(course.getNo() == courseNo && course.getQuota() == course.getRegisteredCount()) {
//				System.out.println("정원이 다 채워졌습니다.");
//				return;//메소드 실행 즉시 중단
//			}
//		} //신청자수를 1증가시키려면 과정을 미리 찾아서 해야함(foundCourse)
	}
	
	//신청 취소 기능
	//학생번호, 과정번호를 전달받아서 registrations가 참조하는 ArrayList객체에서 해당 과정을 삭제하는 기능
	public void cancelRegistration(int studentNo, int courseNo) {
		//registerations에서 Resisteration 객체 삭제
		//Iterator를 이용해야 함
		Course foundCourse = null;
		
		Iterator<Registration> ir = registrations.iterator();
		while(ir.hasNext()) {
			Registration sn = ir.next();

			if(sn.getStudNo() == studentNo && sn.getCourseNo() == courseNo) {
				for(Course course : courses) {
					if(course.getNo() == courseNo) {
						foundCourse = course;
						break;
					}
				}
				ir.remove();
				System.out.println("* 수강철회가 완료 되었습니다.");
				foundCourse.setRegisteredCount(foundCourse.getRegisteredCount()-1);
			}
			
		}
		
	}
	
	//나의 수강신청과정 조회하기
	//학번을 전달받아서 그 학생이 수강신청한 과정을 출력하는 기능
	public void displayMyResistrations(int studentNo) {
		for(Registration regi : registrations) {
			if(studentNo == regi.getStudNo()) {
				System.out.println("학생번호 : " + regi.getStudNo());
				System.out.println("수강신청한 강의명 : " + regi.getCourseNo());
			}		
		}
	}
	
}
