package com.dev.notice.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/notice")
public class NoticeController {

	@GetMapping("/noticeList.do")
	public String noticeList() {
		return "/notice/noticeList";
	}
	
	@GetMapping("/noticeWrite.do")
	public String noticeWrite() {
		return "/notice/noticeWrite";
	}
	
	@PostMapping("/insertNotice.do")
	public String insertNotice() {
		return "redirect:/notice/noticeList.do";
	}
}
