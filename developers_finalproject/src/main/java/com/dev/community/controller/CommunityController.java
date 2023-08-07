package com.dev.community.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.service.CommunityService;

@Controller
@RequestMapping("/community")
public class CommunityController {
	private CommunityService service;

	public CommunityController(CommunityService service) {
		this.service=service;
	}

	@RequestMapping("/communityList.do")
	public String communityList() {
		return "/community/community";
	}
	
	@GetMapping("/communityView.do")
	public String communityView() {
		return "/community/communityView";
	}
	
	@GetMapping("/communityWritePage.do")
	public String communityWritePage() {
		return "/community/communityWrite";
	}
	
	@PostMapping("/communityWrite.do")
	@ResponseBody
	public int communityWrite(@RequestParam("memberId") int id ,@RequestParam("communityTitle") String title, @RequestParam("communityContent") String content, @RequestParam("files") String files,HttpSession session) {
		
		String[] file=files.split(" ");
		
		Community communityBoard=Community.builder().memberId(id).communityTitle(title).communityContent(content).build();
		int result=service.insertCommunity(communityBoard);
		if(result>0) {
			for(String f:file) {
				CommunityFile cf=CommunityFile.builder().fileName(f).build();
				service.communitySaveFileDB(cf);
			};
			return 1;
		}else {
			for(String f:file) {
				service.removeCommunityFile(f, session);
			};
		}
		
		return 0;
	}
}
