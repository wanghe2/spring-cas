package com.wang.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("pay")
public class PayController {
	
	@RequestMapping("list")
	public String list() {
		return "paylist";
	}
	
}
