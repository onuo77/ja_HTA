<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//로그아웃 시키기 (1이나 2 둘중 하나만 있어야함)

	//1. HttpSession객체를 폐기시키기
	//session.invalidate();

	//2. HttpSession객체에 "LOGINED_USER_INFO"이름으로 저장된 사용자정보를 삭제하기
	session.removeAttribute("LOGINED_USER_INFO");
	
	//브라우저에게 재요청 URL을 응답으로 보내기
	response.sendRedirect("/sample-mybatis/index.jsp");
%>