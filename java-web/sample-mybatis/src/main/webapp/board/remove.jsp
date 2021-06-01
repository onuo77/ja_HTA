<%@page import="com.sample.vo.User"%>
<%@page import="java.util.Date"%>
<%@page import="com.sample.vo.Board"%>
<%@page import="com.sample.util.CommonUtils"%>
<%@page import="com.sample.dao.BoardDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	int no = CommonUtils.stringToInt(request.getParameter("no"));
	int pageNo = CommonUtils.stringToInt(request.getParameter("page"));
	Date deletedDate = new Date();
	
	User loginedUser = (User) session.getAttribute("LOGINED_USER_INFO");
	
	BoardDao boardDao = BoardDao.getInstance();
	Board board = boardDao.getBoardByNo(no);

	if(loginedUser == null || !loginedUser.getId().equals(board.getUserId())){
		response.sendRedirect("detail.jsp?no="+no+"&page="+pageNo+"&fail=deny");
		return;				
	}
	
	board.setDeleted("Y");
	board.setDeletedDate(deletedDate);
	
	boardDao.updateBoard(board);
	response.sendRedirect("list.jsp");
%>