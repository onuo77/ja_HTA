package com.sample.web;

import java.io.IOException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/core")
public class CoreTagSampleServlet extends HttpServlet{

	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String text1 = "택배 배송이 너무 지연됩니다. 확인부탁드려요";
		String text2 = "<script>function a() { alert('해피해킹');}</script><button onclick='a()'>클릭</button>";		
		request.setAttribute("text1", text1);
		request.setAttribute("text2", text2);
		
		//core.jsp 내부이동 시키기
		request.getRequestDispatcher("jstl/core.jsp").forward(request, response);
	}
}
