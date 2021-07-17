package com.sample.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

	@GetMapping(path = {"/", "/home", "/main"})
	public String home() {
		return "/WEB-INF/views/home.jsp";
	}
}
