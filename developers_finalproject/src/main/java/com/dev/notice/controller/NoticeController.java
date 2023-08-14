package com.dev.notice.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.nc.common.PageFactory;
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
	public String notice() {
		return "/notice/noticeList";
	}
	
	
	@GetMapping("/noticeListEnd.do")
	@ResponseBody
	public Map<String,Object> noticeList(@RequestParam(value="cPage",defaultValue ="1") int cPage, @RequestParam(value="numPerpage",defaultValue ="5") int numPerpage) {
		
		
		Map<String, Object> paging=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<>();
		paging.put("cPage", cPage);
		paging.put("numPerpage", numPerpage);
		int totalData=service.noticeCount();
		
		String pageBar=PageFactory.getPage(cPage, numPerpage, totalData, "nList");
		
		List<Notice> notices= service.noticeList(paging);
		result.put("noticeList", notices);
		result.put("pageBar", pageBar);
		
		return result;
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
	public String noticeView(int no,Model m,HttpServletRequest req, HttpServletResponse res) {
		Notice result=service.noticeView(no,req,res);
		m.addAttribute("notice",result);
		return "/notice/noticeView";
	}
	
	@GetMapping("/deleteNotice.do")
	public String deleteNotice(int no, Model m) {
		
		int result=service.deleteNotice(no);
		
		if(result>0) {
			m.addAttribute("msg","삭제성공");
			m.addAttribute("loc","/notice/noticeList.do");
		}else {
			m.addAttribute("msg","삭제실패");
			m.addAttribute("loc","/notice/noticeView.do?no="+no);
		}
		
		return "/common/msg";
	}

	@GetMapping("/updateNoticePage.do")
	public String updateNoticePage(int no,Model m,HttpServletRequest req, HttpServletResponse res) {
		Notice result=service.noticeView(no,req,res);
		m.addAttribute("notice",result);
		return "/notice/noticeUpdate";
	}
	
	@PostMapping("/updateNotice.do")
	public String updateNotice(Model m,Notice n,int no) {
		n.setNoticeNo(no);
	
		int result=service.updateNotice(n);
		if(result>0) {
			m.addAttribute("msg","수정성공");
			m.addAttribute("loc","/notice/noticeView.do?no="+no);
		}else {
			m.addAttribute("msg","수정실패");
			m.addAttribute("loc","/notice/noticeView.do?no="+no);
		}
		return "/common/msg";
	}
	
	@GetMapping("/searchNotice.do")
	@ResponseBody
	public Map<String,Object> searchNotice(@RequestParam("type") String type, @RequestParam("keyword") String keyword,@RequestParam(value="cPage",defaultValue ="1") int cPage, @RequestParam(value="numPerpage",defaultValue ="5") int numPerpage) {
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> paging=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<>();
		params.put("type", type);
		params.put("keyword", keyword);
		paging.put("cPage", cPage);
		paging.put("numPerpage", numPerpage);
		List<Notice> noticeList= service.searchNotice(params,paging);
		int totalData=service.noticeCount();
		String pageBar=PageFactory.getPage(cPage, numPerpage, totalData,"searchNotice");
		result.put("noticeList", noticeList);
		result.put("pageBar", pageBar);
		return result;
	}

}







