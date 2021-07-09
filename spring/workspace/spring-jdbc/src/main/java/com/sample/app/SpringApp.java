package com.sample.app;

import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.sample.dao.UserDao;
import com.sample.vo.User;

public class SpringApp {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("context-jdbc.xml");
		
		UserDao userDao = context.getBean(UserDao.class);
		
		List<User> users = userDao.getAllUsers();
		for(User user : users) {
			System.out.println(user.getId() + ", " + user.getName());
		}
		
//		User user = new User();
//		user.setId("hong1234");
//		user.setPassword("zxcv1234");
//		user.setName("홍길동");
//		user.setEmail("hong1234@naver.com");
//		user.setPhone("010-1111-1111");
//		
//		userDao.insertUser(user);
	}
}
