package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("article")
public class ArticleController {
	
	@RequestMapping("list")
	public String list(Model model) {
		return "articlelist";
	}
	
}
