package com.devopsbuddy.web.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class PayloadController {

	public static final String PLAYLOAD_VIEW_NAME = "payload/payload";
	
	@RequestMapping("/payload")
	public String playload() {
		return PLAYLOAD_VIEW_NAME;
	}
}
