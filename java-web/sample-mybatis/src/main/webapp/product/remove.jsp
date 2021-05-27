<%@page import="com.sample.dao.ProductDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	//요청객체에서 요청파라미터로 전달된 값을 조회한다.
	int no = Integer.parseInt(request.getParameter("no"));

	ProductDao pdDao = ProductDao.getInstance();
	pdDao.deleteProduct(no);
	
	//브라우저에게 재요청 URL을 응답으로 보낸다.
	response.sendRedirect("list.jsp");
%>