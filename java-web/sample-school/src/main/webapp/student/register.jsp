<%@page import="com.sample.school.dao.StudentDao"%>
<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.sample.school.vo.Student"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	/*
		학생정보를 등록한다.
		
		1. 폼입력으로 제출된 요청파라미터값을 조회한다.
		2. 조회된 아이디로 학생정보가 존재하는지 조회한다. 학생정보가 존재하면 입력폼에 에러메세지를 표시한다.
		3. 비밀번호를 암호화한다.
		4. Student객체를 생성해서 학생정보를 저장한다.
		5. ProfessorDao객체의 insertStudent(학생정보)로 데이터베이스에 저장한다.
		6. 홈페이지를 재요청하는 URL을 응답으로 보낸다.
	*/
	String userId = request.getParameter("userid");
	String password = request.getParameter("password");
	String name = request.getParameter("name");
	int departmentNo = Integer.parseInt(request.getParameter("departmentNo"));
	int grade = Integer.parseInt(request.getParameter("grade"));
	
	Student student = new Student();
	
	if(userId.equals(student.getId())){
		response.sendRedirect("registerForm.jsp?fail=id");
		return;
	}
	
	String sha256Password = DigestUtils.sha256Hex(password);
	
	student.setId(userId);
	student.setName(name);
	student.setPassword(sha256Password);
	student.setGrade(grade);
	student.setDepartmentNo(departmentNo);
	
	StudentDao studentDao = StudentDao.getInstance();
	studentDao.insertStudent(student);
	
	response.sendRedirect("/sample-school/index.jsp");
%>