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
import vo.User;

public class UserDao {

	private List<User> db = new ArrayList<User>();
	
	/**
	 * 새 User을 db에 추가한다.
	 * @param user 새 User
	 */
	public void insertUser(User user) {
		db.add(user);
	}
	
	/**
	 * 모든 User 정보를 반환한다.
	 * @return User 리스트
	 */
	public List<User> getUsers() {
		return db;
	}
	
	/**
	 * 지정한 사용자 아이디의 User 정보를 반환한다.
	 * @param id userId
	 * @return User 정보
	 */
	public User getUserById(String id) {
		for(User user : db) {
			if(id.equals(user.getId())) {
				return user;
			}
		}
		return null;
	}
	
	/**
	 * users.csv에 기록된 사용자정보를 읽어서 db에 저장한다.
	 */
	public void loadData() {
		try (BufferedReader reader = new BufferedReader(new FileReader("users.csv"))) {
			String value = null;
			while ((value=reader.readLine()) != null) {
				String[] items = value.split(",");
				String id = items[0];
				String password = items[1];
				String username = items[2];
				boolean isDisabled = StringUtils.stringToBoolean(items[3]);
				Date createdDate = StringUtils.stringToDate(items[4]);
				Date deletedDate = StringUtils.stringToDate(items[5]);
				
				User user = new User();
				user.setId(id);
				user.setPassword(password);
				user.setUsername(username);
				user.setDisabled(isDisabled);
				user.setCreatedDate(createdDate);
				user.setDeletedDate(deletedDate);
				
				db.add(user);
			}
		} catch (IOException e) {
			throw new TodoException(e.getMessage());
		}
	}
	
	/**
	 * db에 저장된 사용자 정보를 users.csv 파일에 저장한다.
	 */
	public void saveData() {
		try (PrintWriter writer = new PrintWriter("users.csv")) {
			StringBuilder sb = new StringBuilder();
			for (User user : db) {
				sb.append(user.getId())
					.append(",")
					.append(user.getPassword())
					.append(",")
					.append(user.getUsername())
					.append(",")
					.append(user.isDisabled())
					.append(",")
					.append(StringUtils.dateTimeToString(user.getCreatedDate()))
					.append(",")
					.append(StringUtils.dateToString(user.getDeletedDate()));
				writer.println(sb.toString());
				sb.setLength(0);
			}
		} catch (IOException e) {
			throw new TodoException(e.getMessage());
		}
	}
}
