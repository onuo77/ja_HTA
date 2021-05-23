package app;

import service.TodoService;
import vo.Todo;
import vo.User;

import static utils.KeyboardUtils.readInt;
import static utils.KeyboardUtils.readString;

import java.util.Date;
import java.util.List;

import static utils.KeyboardUtils.readDate;
import static utils.KeyboardUtils.readDateTime;

import static utils.StringUtils.dateTimeToString;

import exception.TodoException;

public class TodoApp {

	private TodoService service = new TodoService();
	
	public TodoApp() {
		service.프로그램시작서비스();
	}
	
	public void menu() {
		try {
		if (!service.로그인여부제공서비스()) {
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println("1.로그인 2.회원가입 0.종료");
			
			System.out.print("메뉴 선택 > ");
			int menuNo = readInt();
			System.out.println();
			
			if (menuNo == 1) {
				로그인();
			} else if (menuNo == 2) {
				회원가입();
			} else if(menuNo == 0) {
				종료();
			}
			
		} else {
			System.out.println("---------------------------------------------------------------------------------------------------------");
			System.out.println("1.일정조회 2.일정추가 3.일정변경 4.일정완료처리 5.일정삭제 6.비밀번호변경 7.로그아웃 8.회원탈퇴  0.종료");
			System.out.println("---------------------------------------------------------------------------------------------------------");
			
			System.out.print("메뉴 선택 > ");
			int menuNo = readInt();
			
			if (menuNo == 1) {
				일정조회();
			} else if (menuNo == 2) {
				일정추가();
			} else if (menuNo == 3) {
				일정변경();
			} else if (menuNo == 4) {
				일정완료처리();
			} else if (menuNo == 5) {
				일정삭제();
			} else if (menuNo == 6) {
				비밀번호변경();
			} else if (menuNo == 7) {
				로그아웃();
			} else if (menuNo == 8) {
				회원탈퇴();
			} else if(menuNo == 0) {
				종료();
			}
		}
		} catch (TodoException e) {
			System.out.println("[오류] " + e.getMessage());
		}
		System.out.println();
		System.out.println();
		System.out.println();
		
		menu();
	}
	
	// 아이디와 비밀번호를 입력받아서 로그인 서비스를 실행한다.
	private void 로그인() {
		System.out.println("[로그인]");
		
		System.out.print("아이디를 입력하세요 > ");
		String userId = readString();
		System.out.print("비밀번호를 입력하세요 > ");
		String password = readString();
		service.로그인서비스(userId, password);
	}
	
	// 로그아웃 서비스를 실행한다.
	private void 로그아웃() {
		service.로그아웃서비스();
	}
	
	// 아이디, 비밀번호, 사용자명을 입력받아서 회원가입 서비스를 실행한다.
	private void 회원가입() {
		System.out.println("[회원가입]");
		
		System.out.print("사용할 아이디를 입력하세요. > ");
		String userId = readString();
		System.out.print("사용할 비밀번호를 입력하세요 > ");
		String password = readString();
		System.out.print("사용자 이름을 입력하세요 > ");
		String username = readString();
		
		User newUser = new User();
		newUser.setId(userId);
		newUser.setPassword(password);
		newUser.setUsername(username);
		newUser.setDisabled(false);
		newUser.setCreatedDate(new Date());
		newUser.setDeletedDate(null);
		
		service.회원가입서비스(newUser);
	}
	
	// 종료 서비스를 실행한다.
	private void 종료() {
		service.프로그램종료서비스();
	}
	
	// 나의모든일정조회서비스를 실행한다.
	private void 일정조회() {
		System.out.println("[나의 모든 일정조회]");
		List<Todo> todoList = service.나의모든일정조회서비스();
		for(Todo value : todoList) {
			System.out.print("번호 : " + value.getNo()+"\t");
			System.out.print("작성자 : " + value.getWriter()+"\t");
			System.out.println("제목 : " + value.getTitle());
			System.out.print("상태 : " + value.getStatus()+"\t");
			System.out.print("생성날짜 : " + dateTimeToString(value.getCreatedDate())+"\t");
			System.out.println("완료예정일 : " + dateTimeToString(value.getDay()));
			System.out.println("----------------------------------------");
		}
		
		if(todoList.isEmpty()) {
			System.out.println("▶ 등록된 일정이 존재하지 않습니다.");
		}
		
		if(!todoList.isEmpty()) {
			while(true) {
				System.out.println("1.상세정보조회 2.나가기");
				int selectNo = readInt();
				
				if(selectNo == 1) {
					System.out.print("상세조회할 일정번호를 입력하세요 : ");
					int no = readInt();
					Todo todoListDetail = service.일정상세조회서비스(no);
					System.out.println();
					
					if(todoListDetail.getNo() == no) {
						System.out.println("번호 : " + todoListDetail.getNo());
						System.out.println("제목 : " + todoListDetail.getTitle());
						System.out.println("내용 : " + todoListDetail.getText());
						System.out.println("상태 : " + todoListDetail.getStatus());
						System.out.println("생성날짜 : " + dateTimeToString(todoListDetail.getCreatedDate()));
						System.out.println("완료예정일 : " + dateTimeToString(todoListDetail.getDay()));
						System.out.println("완료된날짜 : " + dateTimeToString(todoListDetail.getCompletedDate()));	
						System.out.println("삭제된날짜 : " + dateTimeToString(todoListDetail.getDeletedDate()));	
						System.out.println("----------------------------------------");
					}	
				} else if(selectNo == 2) {
					break;
				}			
			}
		}
	}
	
	// 일정제목, 내용, 예정일을 입력받아서 새일정추가서비스를 실행한다.
	private void 일정추가() {
		System.out.println("[새 일정 추가]");
		
		System.out.print("일정 제목을 입력하세요 > ");
		String title = readString();
		System.out.print("일정 내용을 입력하세요 > ");
		String text = readString();
		System.out.print("일정 실행일을 입력하세요(2021-04-30) > ");
		Date day = readDate();
		
		Todo newTodo = new Todo();
		newTodo.setTitle(title);
		newTodo.setText(text);
		newTodo.setDay(day);
		newTodo.setStatus(Todo.TODO_STATUS_ADDED);
		newTodo.setCreatedDate(new Date());
		
		service.새일정추가서비스(newTodo);
	} 
	
	// 변경할 일정번호, 제목, 내용을 입력받아서 일정변경서비스를 실행한다.
	private void 일정변경() {
		System.out.println("[일정변경]");
		System.out.print("변경할 일정번호를 입력하세요 > ");
		int no = readInt();
		System.out.print("변경할 제목을 입력하세요 > ");
		String title = readString();
		System.out.print("변경할 내용을 입력하세요 > ");
		String text = readString();
		
		service.일정변경서비스(no, title, text);
	}
	
	// 완료처리할 일정번호를 입력받아서 일정완료처리서비스를 실행한다.
	private void 일정완료처리() {
		System.out.println("[일정완료처리]");
		System.out.print("완료할 일정번호를 입력하세요 > ");
		int no = readInt();
		
		service.일정완료처리서비스(no);
	}
	
	// 삭제할 일정번호를 입력받아서 일정삭제서비스를 실행한다.
	private void 일정삭제() {
		System.out.println("[일정삭제]");
		System.out.print("삭제할 일정번호를 입력하세요 > ");
		int no = readInt();
		
		service.일정삭제서비스(no);
	}
	
	// 이전비밀번호, 새비밀번호를 입력받아서 비밀번호변경서비스를 실행한다.
	private void 비밀번호변경() {
		System.out.println("[비밀번호 변경]");
		System.out.print("이전 비밀번호 입력하세요 > ");
		String pw = readString();
		System.out.print("새 비밀번호 입력하세요 > ");
		String newPw = readString();
		
		service.비밀번호변경서비스(pw, newPw);
	}
	
	// 비밀번호를 입력받아서 회원탈퇴서비스를 실행한다.
	private void 회원탈퇴() {
		System.out.println("[회원탈퇴]");
		System.out.print("탈퇴할 계정의 비밀번호를 입력하세요 > ");
		String pw = readString();
		
		service.회원탈퇴서비스(pw);
	}
		
	public static void main(String[] args) {
		new TodoApp().menu();
	}
}
