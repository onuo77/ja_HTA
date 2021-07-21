package com.sample.service;

import com.sample.vo.User;

public interface UserService {

	/**
	 * 지정된 사용자정보를 회원가입시킨다.
	 * @param user 사용자정보
	 */
	void registerUser(User user);
	
	/**
	 * 로그인 정보
	 * @param id 사용자 아이디
	 * @param password 사용자 비밀번호
	 */
	void login(String id, String password);
}
