<%@page import="com.sample.school.dao.ProfessorDao"%>
<%@page import="com.sample.school.vo.Student"%>
<%@page import="java.util.List"%>
<%@page import="com.sample.school.dao.StudentDao"%>
<%@page import="com.sample.school.vo.LoginUser"%>
<%@page import="jakarta.websocket.Session"%>
<%@ page contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%
	/*
		체크된 유저타입에 따라서 학생 혹은 교수로 구분해서 로그인처리를 수행한다.
		
		1. 요청파라미터를 조회한다.
		2. 유저타입이 student인지, professor인지 구분해서 
		   student인 경우 StudentDao로 학생정보를 조회해서 로그인처리를 수행한다.
		   professor인 경우 ProfessorDao로 교수정보를 조회해서 로그인처리를 수행한다.
		   - 학생정보나 교수정보가 존재하지 않으면 입력폼에 에러 메세지를 표시한다.
		   - 비밀번호가 일치하지 않으면 입력폼에 에러 메세지를 표시한다.
		3. 인증이 완료되면 세션객체에 
			LoginUser객체를 생성해서 교수나 학생의 정보를 저장하고, 
			세션객체에 "LOGINED_USER"라는 속성명으로 LoginUser객체를 저장한다.
		4. 홈페이지를 재요청하는 URL을 응답으로 보낸다.
	*/
	
	String userId = request.getParameter("userid");
	String password = request.getParameter("password");
	String userType = request.getParameter("usertype");
	
	if("student".equals(userType)){
		StudentDao studentDao = StudentDao.getInstance();
		List<Student> students = studentDao.getStudentById(userId);
		for(Student student : students){
			if(userId.equals(student.getId())){
				LoginUser loginUser = new LoginUser();
				loginUser.setNo(student.getGrade());
				loginUser.setName(student.getName());
				loginUser.setUserType("student");
				loginUser.setDepartmentNo(student.getDepartmentNo());
				
				session.setAttribute("LOGINED_USER", loginUser);
			}
		}
	}else if("professor".equals(userType)){
		ProfessorDao professorDao = ProfessorDao.getInstance();
		List<Student> students = professorDao.getProfessorById(userId);
		for(Student student : students){
			if(userId.equals(student.getId())){
				LoginUser loginUser = new LoginUser();
				loginUser.setNo(student.getGrade());
				loginUser.setName(student.getName());
				loginUser.setUserType("student");
				loginUser.setDepartmentNo(student.getDepartmentNo());
				
				session.setAttribute("LOGINED_USER", loginUser);
			}
		}
	}
	
	
	response.sendRedirect("/sample-school/index.jsp");
%>