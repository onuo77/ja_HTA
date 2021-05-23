package day3;

public class PersonApp {

	public static void main(String[] args) {
		Student student = new Student("홍길동","hong@gmail.com","010-1111-1111",3,"컴퓨터공학과");
		student.displayInfo();
		
		Professor professor = new Professor("김유신","kim@gmail.com","010-2222-2222","컴퓨터공학과","정교수");
		professor.displayInfo();
	}
}
