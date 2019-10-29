package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IdentifyController {

	@RequestMapping("login")
	public String login() {
		return "index";
	}
	
}
