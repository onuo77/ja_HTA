package com.sample.web;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.commons.io.IOUtils;

import com.sample.dao.FileItemDao;
import com.sample.vo.FileItem;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.MultipartConfig;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;

/*
 * @WebServlet
 * 		- 이 클래스가 클라이언트의 HTTP 요청을 처리하는 서블릿 클래스임을 나타낸다.
 * 		- ("/upload")는 이 서블릿 클래스와 매핑된 URI를 설정한다. (uri는 식별자 /url은 locaiton)
 * 		  위의 설정은 http://localhost/upload라는 요청이 접수되면 이 서블릿의
 * 		  void service(request, response) 메소드가 실행되게 한다.
 */
@WebServlet("/upload")
/*
 * @MultipartConfig
 * 		- 이 클래스가 멀티파트요청을 처리하는 서블릿 클래스임을 나타낸다.
 * 		- 멀티파트요청은 폼 입력요소에 첨부파일 업로드가 포함되어 있는 요청이다.
 * 		- 멀티파트요청은 <form />태그의 enctype="multipart/form-data"로 설정되어 있는 것이다.
 * 		- @MultipartConfig 어노테이션이 부착되어 있지 않은 서블릿 클래스는
 * 		  멀티파트요청을 처리할 수 없다.
 */
@MultipartConfig
public class FileUploadServlet extends HttpServlet{

	private static final String saveDirectory = "C:\\workspace\\donghui\\java-web\\upload";
	
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//요청 파라미터 조회하기
		String title = request.getParameter("title");
		String description = request.getParameter("description");
		//System.out.println("제목 : " + title);
		//System.out.println("설명 : " + description);
		
		//업로드된 첨부파일 처리하기
		//Part는 업로드된 첨부파일의 정보를 가지고 있는 객체다.
		//Part로부터 파일명, 파일종류(ContentType), 파일크기, 파일을 읽어오는 스트림을 획득할 수 있다.
		Part part = request.getPart("upfile");
		
		//1. 업로드된 첨부파일 정보 획득하기
		String filename = System.currentTimeMillis() + part.getSubmittedFileName();
		//System.out.println("파일이름 : " + part.getSubmittedFileName());
		//System.out.println("파일사이즈 : " + part.getSize());
		//System.out.println("파일타입 : " + part.getContentType());
		
		//2. 업로드된 첨부파일을 지정된 폴더에 복사하기
		//	 * 서버로 업로드된 첨부파일은 서버의 임시디렉토리에 임시파일(xxxxxxxxxx.tmp)의 형태로 저장되어 있음
		// File객체를 생성 : 지정된 디렉토리와 파일명에 대한 정보를 가지는 객체다.
		//				  File객체는 실제로 존재하지 않는 파일에 대해서도 객체 생성이 가능하다.
		OutputStream out = new FileOutputStream(new File(saveDirectory, filename));
		InputStream in = part.getInputStream();
		IOUtils.copy(in, out);
		out.close();
		
		//3. 업로드된 파일정보(제목, 설명, 파일명)을 데이터베이스에 저장하기
		FileItem fileItem = new FileItem();
		fileItem.setTitle(title);
		fileItem.setDescription(description);;
		fileItem.setFilename(filename);
		
		FileItemDao fileItemDao = FileItemDao.getInstance();
		fileItemDao.insertFileItem(fileItem);
		
		//클라이언트에게 index.jsp를 재요청하게하는 응답을 보내기
		response.sendRedirect("/index.jsp");
		
		//String filename = this.getFilename(part); // 아래 주석처리한 메소드 사용해서 filename가져오기 
		//System.out.println("파일이름 : " + filename);
	}
	
	
	/*
	 * 원래 사용하던 파일이름(filename)가져오는 방식
	 * Part객체가 가지고 있는 파일이름 정보
	 * 		Content-Disposition: form-data; name="upfile"; filename="휴가신청서.hwp"
	private String getFilename(Part part) {
		String contentDisposition = part.getHeader("content-disposition");
		//contentDisposition <-- form-data; name="upfile"; filename="휴가신청서.hwp"
		
		String[] items = contentDisposition.split(";");
		//item <-- {form-data, name="upfile", filename="휴가신청서.hwp"}
		
		for(String item : items) {
			if(item.trim().startsWith("filename")) {
				//item <-- filename="휴가신청서.hwp"
				return item.substring(item.indexOf("=")+2, item.length()-1);
				//=을 기준(인덱스)으로 1칸2칸가면 휴가신청서.hwp"고, 길이에서 -1을 빼면 맨뒤에 "가 빠져서 파일이름만 가져옴
			}
		}
		return "";
	}
	*/
}
