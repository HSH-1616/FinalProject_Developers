package com.dev.community.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/community")
public class CommunityController {

	@GetMapping("/communityList.do")
	public String communityList() {
		return "/community/community";
	}
	
	@GetMapping("/communityView.do")
	public String communityView() {
		return "/community/communityView";
	}
	
	@GetMapping("/communityWrite.do")
	public String communityWrite() {
		return "/community/communityWrite";
	}
}
