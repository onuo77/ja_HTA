<%@page import="com.sample.dao.BoardDao"%>
<%@page import="com.sample.vo.Board"%>
<%@page import="com.sample.util.CommonUtils"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int no = CommonUtils.stringToInt(request.getParameter("no"));
	int pageNo = CommonUtils.stringToInt(request.getParameter("page"));
	
	BoardDao boardDao = BoardDao.getInstance();
	Board board = boardDao.getBoardByNo(no);
	board.setViewCount(board.getViewCount()+1);
	
	boardDao.updateBoard(board);
	
	response.sendRedirect("detail.jsp?no="+no+"&page="+pageNo);
%>