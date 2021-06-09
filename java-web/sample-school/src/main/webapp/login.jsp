<%@page import="org.apache.commons.codec.digest.DigestUtils"%>
<%@page import="com.sample.school.vo.Professor"%>
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
	
	if(userId.isBlank() || password.isBlank()){
		response.sendRedirect("loginForm.jsp?fail=blank");
		return;
	}

	String sha256Password = DigestUtils.sha256Hex(password);

	if("student".equals(userType)){
		StudentDao studentDao = StudentDao.getInstance();
		Student student = studentDao.getStudentById(userId);
		
		if(student == null) {
			response.sendRedirect("loginForm.jsp?fail=invalid");
			return;				
		}
		if(!sha256Password.equals(student.getPassword())){
			response.sendRedirect("loginForm.jsp?fail=password");
			return;		
		}

		LoginUser savedUser = new LoginUser();
		savedUser.setId(userId);
		savedUser.setName(student.getName());
		savedUser.setUserType("student");
		savedUser.setDepartmentNo(student.getDepartmentNo());
		
		session.setAttribute("LOGINED_USER", savedUser);	
		
	}else if("professor".equals(userType)){
		ProfessorDao professorDao = ProfessorDao.getInstance();
		Professor professor = professorDao.getProfessorById(userId);
		
		if(professor == null) {
			response.sendRedirect("loginForm.jsp?fail=invalid");
			return;				
		}
		if(!sha256Password.equals(professor.getPassword())){
			response.sendRedirect("loginForm.jsp?fail=password");
			return;		
		}
		
		LoginUser savedUser = new LoginUser();
		savedUser.setId(userId);
		savedUser.setName(professor.getName());
		savedUser.setUserType("professor");
		savedUser.setDepartmentNo(professor.getDepartmentNo());
		
		session.setAttribute("LOGINED_USER", savedUser);	
	}
	

	response.sendRedirect("/sample-school/index.jsp");
%>