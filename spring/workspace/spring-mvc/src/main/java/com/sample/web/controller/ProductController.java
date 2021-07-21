package com.sample.web.controller;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.sample.service.ProductService;
import com.sample.vo.CartItem;
import com.sample.vo.Product;
import com.sample.vo.User;
import com.sample.web.utils.SessionUtils;

@Controller
@RequestMapping("/product")
public class ProductController {
	private static Logger logger = LogManager.getLogger(ProductController.class);
	
	/*
	 * ProductService 인터페이스의 구현객체(ProductServiceImpl)가 주입된다.
	 */
	@Autowired
	private ProductService productService;
	
	/**
	 * 모든 상품목록 요청을 처리하는 요청핸들러 메소드 정의
	 * @param model 뷰페이지에 전달할 데이터를 담는 객체, HandlerAdapter객체가 Model을 생성해서 전달함
	 * @return 뷰페이지의 이름
	 */
	@GetMapping("/list")
	public String products(Model model) {
		List<Product> productList = productService.getAllProducts();
		
		// 뷰 페이지에 상품정보 목록 전달하기
		model.addAttribute("products", productList);
		
		// 뷰페이지로 내부이동하기
		// WEB-INF/views/product/list.jsp로 내부이동해서 JSP 실행시키기
		return "product/list";
	}
	
	//실제 요청 URL : localhost/spring-mvc/detail?no=100
	@GetMapping("/detail")
	public String detail(int no, Model model) {
		logger.info("조회할 상품번호 : " + no);
		
		//상품번호에 해당하는 상품만 조회
		Product product = productService.getProductDetatil(no);
		
		//뷰에 조회된 정보 전달하기
		model.addAttribute("product", product);
		
		return "product/detail";
	}
	
	//실제 요청 URL : localhost/spring-mvc/product/addCart?no=32
	@GetMapping("/addCart")
	public String addCartItem(@RequestParam("no") int productNo) {
		logger.debug("addCartItem() 실행됨");
		logger.info("장바구니에 저장할 상품번호 : " + productNo);
		
		User user = (User) SessionUtils.getAttribute("LOGINED_USER");
		logger.info("로그인된 사용자정보 : " + user);
		if(user == null) {
			throw new RuntimeException("장바구니 담기는 로그인 후 사용가능한 서비스입니다.");
		}
		
		CartItem cartItem = new CartItem();
		cartItem.setUserId(user.getId());
		cartItem.setProductNo(productNo);
		
		productService.addCartItem(cartItem);
		
		logger.debug("addCartItem() 종료됨");
		return "redirect:listCart";
	}
}
