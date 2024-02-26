package org.vaadin.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PageController {

	@RequestMapping("/page1")
	public String h() {
		return "index3.html";
	}
}
