package dao;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import exception.TodoException;
import utils.StringUtils;
import vo.Todo;

public class TodoDao {

	private List<Todo> db = new ArrayList<Todo>();
	
	/**
	 * 새 Todo을 db에 추가한다.
	 * @param todo 새 Todo
	 */
	public void insertTodo(Todo todo) {
		db.add(todo);
	}
	
	/**
	 * 모든 Todo 정보를 반환한다.
	 * @return Todo 리스트
	 */
	public List<Todo> getTodos() {
		return db;
	}
	
	/**
	 * 지정한 Todo 번호의 Todo 정보를 반환한다.
	 * @param no 일정번호
	 * @return Todo정보
	 */
	public Todo getTodoByNo(int no) {
		for (Todo todo : db) {
			if(no == todo.getNo()) {
				return todo;
			}
		}
		return null;
	}
	
	/**
	 * todos.csv에 기록된 Todo정보를 읽어서 db에 저장한다.
	 */
	public void loadData() {
		try (BufferedReader reader = new BufferedReader(new FileReader("todos.csv"))) {
			String value = null;
			while ((value=reader.readLine()) != null) {
				String[] items = value.split(",");
				int no = StringUtils.stringToInt(items[0]);
				String title = items[1];
				String writer = items[2];
				Date day = StringUtils.stringToDate(items[3]);
				String status = items[4];
				String text = items[5];
				Date createdDate = StringUtils.stringToDateTime(items[6]);
				Date completedDate = StringUtils.stringToDateTime(items[7]);
				Date deletedDate = StringUtils.stringToDateTime(items[8]);
				
				Todo todo = new Todo();
				todo.setNo(no);
				todo.setTitle(title);
				todo.setWriter(writer);
				todo.setDay(day);
				todo.setStatus(status);
				todo.setText(text);
				todo.setCreatedDate(createdDate);
				todo.setCompletedDate(completedDate);
				todo.setDeletedDate(deletedDate);
				
				db.add(todo);
			}
		} catch (IOException e) {
			throw new TodoException(e.getMessage());
		}
	}
	
	/**
	 * db에 저장된 Todo 정보를 totos.csv 파일에 저장한다.
	 */
	public void saveData() {
		try (PrintWriter writer = new PrintWriter("todos.csv")) {
			
			StringBuilder sb = new StringBuilder();
			for (Todo todo : db) {
				sb.append(todo.getNo())
					.append(",")
					.append(todo.getTitle())
					.append(",")
					.append(todo.getWriter())
					.append(",")
					.append(StringUtils.dateToString(todo.getDay()))
					.append(",")
					.append(todo.getStatus())
					.append(",")
					.append(todo.getText())
					.append(",")
					.append(StringUtils.dateTimeToString(todo.getCreatedDate()))
					.append(",")
					.append(StringUtils.dateTimeToString(todo.getCompletedDate()))
					.append(",")
					.append(StringUtils.dateTimeToString(todo.getDeletedDate()));
				writer.println(sb.toString());
				
				sb.setLength(0);
			}
			
		} catch (IOException e) {
			throw new TodoException(e.getMessage());
		}
	}
}
