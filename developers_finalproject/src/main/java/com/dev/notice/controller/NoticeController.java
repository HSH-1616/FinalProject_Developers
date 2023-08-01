package com.dev.notice.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.dev.notice.model.dto.Notice;
import com.dev.notice.model.service.NoticeService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/notice")
@Slf4j
public class NoticeController {
	
	private NoticeService service;
	
	public NoticeController(NoticeService service) {
		this.service=service;
	}
	
	@GetMapping("/noticeList.do")
	public String noticeList(Model m) {
		List<Notice> notices= service.noticeList();
		m.addAttribute("noticeList",notices);
		return "/notice/noticeList";
	}
	
	@GetMapping("/noticeWrite.do")
	public String noticeWrite() {
		return "/notice/noticeWrite";
	}
	
	@PostMapping("/insertNotice.do")
	public String insertNotice(Model m,Notice n) {
		int result=service.insertNotice(n);
		if(result==0){
			
			m.addAttribute("msg","등록실패");
			m.addAttribute("loc","/notice/noticeList.do");
			return "/common/msg";
		}
		
		return "redirect:/notice/noticeList.do";
	}
	
	@GetMapping("/noticeView.do")
	public String noticeView(int no,Model m) {
		Notice result=service.noticeView(no);
		m.addAttribute("notice",result);
		return "/notice/noticeView";
	}

	

}
