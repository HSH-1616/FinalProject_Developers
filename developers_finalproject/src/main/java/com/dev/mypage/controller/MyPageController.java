package com.dev.mypage.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/mypage")
public class MyPageController {
	
	@RequestMapping("/mypage")
	public String mypage() {
		
		return "mypage/mypage";
	}
	
	@RequestMapping("/mypageDetail.do")
	public String mypageDeatil() {
		
		return "mypage/mypageDetail";
	}
	
}
