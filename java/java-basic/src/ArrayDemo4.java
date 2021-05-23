
public class ArrayDemo4 {

	public static void main(String[] args) {
		// 복습
		
		// ###배열 만들기
		// 학과명을 저장하는 배열을 생성하기
		// 학과명은 "컴퓨터공학과", "통계학과", "수학과", "전자공학과", "정보통신공학과"다.
		// 학과명 배열은 문자열 5개 저장하는 배열을 new 연산자를 사용해서 생성하고,
		// 0번부터 4번까지 순서대로 학과명을 배열에 저장하기
		String[] subjects = new String[5];
		subjects[0] = "컴퓨터공학과";
		subjects[1] = "통계학과";
		subjects[2] = "수학과";
		subjects[3] = "전자공학과";
		subjects[4] = "정보통신공학과";
		
		// 학생들의 이름을 저장하는 배열을 생성하기
		// 학생명은 "이순신", "김유신", "강감찬", "류관순"이다.
		// 학생명 배열은 배열표현식을 {값, 값, 값, ...}을 사용해서 배열을 생성하기
		String[] studentNames = {"이순신", "김유신", "강감찬", "류관순"};
		

		// 학생들의 학점을 저장하는 배열을 생성하기
		// 학생들의 성적은 3.1, 3.0, 4.0, 4.3이다.
		// 학생성적 배열은 배열표현식을 사용해서 배열을 생성하기
		double[] studentScores = {3.1, 3.0, 4.0, 4.3};
		
		// ### 배열의 값 출력하기
		// 학과명 배열의 모든 값을 for문을 사용해서 화면에 출력하기
		// 학생이름 배열의 모든 값을 향상된 for문을 사용해서 화면에 출력하기
		// 학생성적 배열의 모든 값을 향상된 for문을 사용해서 화면에 출력하기
		// 학생명 배열에서 0번째에 저장된 학생의 이름과, 학생성적 배열에서 0번째에 저장된 학생의 성적 출력하기
		
		// 학과명
		System.out.println("### 학과명 출력하기");
		for(int i=0; i<subjects.length; i++) {
			System.out.println("학과명 : " +subjects[i]);
		}
		
		System.out.println("-------------------------");
		
		// 학생이름
		System.out.println("### 학생이름 출력하기");
		for(String name : studentNames) {
			System.out.println("학생이름 : " + name);
		}		
		
		System.out.println("-------------------------");
		
		// 학생성적
		System.out.println("### 학생성적 출력하기");
		for(double score : studentScores) {
			System.out.println("학생성적 : " + score);
		}		
		
		System.out.println("-------------------------");
		
		// 0번째 학생이름, 학생성적
		System.out.println("### 지정된 위치의 값 출력하기");
		System.out.println("0번째 학생명 : " + studentNames[0]);
		System.out.println("0번째 학생성적 : " + studentScores[0]);
		
		System.out.println("-------------------------");
		
		// 성적에 대한 총점과 평균출력하기
		System.out.println("### 성적에 대한 총점과 평균 출력하기");
				
		double totalScore = 0.0;
		
		for(double score : studentScores) {
			totalScore += score * 10;
		}
		totalScore /= 10;		
		double avg = totalScore / studentNames.length;
		
		System.out.println("총점 : " + totalScore);
		System.out.println("평균 : " + avg);
	}
}
