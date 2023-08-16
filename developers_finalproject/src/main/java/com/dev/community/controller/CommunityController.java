package com.dev.community.controller;


import java.util.HashMap;
import java.util.List;
import java.util.Map;


import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;


import com.dev.community.model.dto.Community;
import com.dev.community.model.dto.CommunityFile;
import com.dev.community.model.dto.Reply;
import com.dev.community.model.service.CommunityService;
import com.dev.member.model.dto.Member;
import com.dev.nc.common.PageFactory;

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
	
	@PostMapping("/communityListEnd.do")
	@ResponseBody
	public List<Community> communityListEnd(@RequestParam(value="cPage",defaultValue ="1") int cPage, @RequestParam(value="numPerpage",defaultValue ="6") int numPerpage) {
		Map<String, Object> pasing=new HashMap<String, Object>();
		pasing.put("cPage", cPage);
		pasing.put("numPerpage", numPerpage);
		//int totalData=service.communityCount();
		 
		return service.communityList(pasing);
	}
	
	@GetMapping("/communityView.do")
	public String communityView(int no,Model m) {
		Community comuView=service.communityView(no);
		
		m.addAttribute("comuView",comuView);
		
		return "/community/communityView";
	}
	
	@GetMapping("/updateCommunity.do")
	public String updateCommunity(Model m, int no) {
		
		Community co=service.communityView(no);
		m.addAttribute("comuView",co);
		return "/community/communityUpdate";
	}
	
	@GetMapping("/communityWritePage.do")
	public String communityWritePage() {
		return "/community/communityWrite";
	}
	
	@PostMapping("/communityWrite.do")
	@ResponseBody
	public int communityWrite(@RequestParam("memberId") int id ,@RequestParam("communityTitle") String title, @RequestParam("communityContent") String content, @RequestParam("files") String files,HttpSession session) {
		
		String[] file=files.split(" ");
		Member m=Member.builder().memberId(id).build();
		Community communityBoard=Community.builder().memberId(m).communityTitle(title).communityContent(content).thumbnail(file[0]).build();
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
	
	@PostMapping("/communityLike.do")
	@ResponseBody
	public int communityLike(@RequestParam("memberId") int id,@RequestParam("communityNo") int no) {
		
		return service.communityLike(id, no);
	}
	
	@PostMapping("/communityLikeCheck.do")
	@ResponseBody
	public int communityLikeCheck(@RequestParam("memberId") int id, @RequestParam("communityNo") int no) {
		Map<String, Object>result= service.CommunityLikeCheck(id, no);
		if(result!=null) {
			return 1;
		}
		return 0;
	}
	
	@GetMapping("/replyList.do")
	@ResponseBody
	public List<Reply> replyList(int communityNo){
		
		
		
		
		return service.replyList(communityNo);
	}
	
	@PostMapping("/insertReply.do")
	@ResponseBody
	public int insertReply(int memberId,int communityNo,String replyContent,@RequestParam(value = "replyRef", defaultValue = "0") int replyRef) {
		Member m=new Member();
		m.setMemberId(memberId);
		Reply reply=Reply.builder().memberId(m).communityNo(communityNo).replyContent(replyContent).replyRef(replyRef).build();
		
		
		return service.insertReply(reply);
	}
	
	@PostMapping("/updateReply.do")
	@ResponseBody
	public int updateReply(int replyNo, String replyContent) {
		Reply r=Reply.builder().replyNo(replyNo).replyContent(replyContent).build();
		
		return service.updateReply(r);
	}
	
	@PostMapping("/deleteReply.do")
	@ResponseBody
	public int deleteReply(int replyNo, int replyLevel) {
		Reply r=Reply.builder().replyNo(replyNo).replyLevel(replyLevel).build();
		return service.deleteReply(r);
	}
	
	
	
	@PostMapping("/communityUpdateEnd.do")
	@ResponseBody
	public int communityUpdateEnd(@RequestParam("memberId") int id ,@RequestParam("communityTitle") String title, @RequestParam("communityContent") String content, @RequestParam(value = "files",required = false) String files, int communityNo, HttpSession session) {
		
		Member m=Member.builder().memberId(id).build();
		Community communityBoard=new Community();
		if(files!=null) {
			String[] file=files.split(" ");
			communityBoard=Community.builder().communityNo(communityNo).memberId(m).communityTitle(title).communityContent(content).thumbnail(file[0]).build();
		}else {
			communityBoard=Community.builder().communityNo(communityNo).memberId(m).communityTitle(title).communityContent(content).build();
		}
		
		return service.updateCommunity(communityBoard);
	}
	
	@PostMapping("/deleteCommunity.do")
	@ResponseBody
	public int deleteComuunity(int communityNo,HttpSession session) {
		return service.deleteCommunity(communityNo, session);
	}
	
	@GetMapping("/mypageCommunity.do")
	@ResponseBody
	public Map<String,Object> mypageCommunity(int memberId, @RequestParam(value = "cPage", defaultValue = "1") int cPage, @RequestParam(value = "numPerpage",defaultValue = "3") int numPerpage){
		Map<String, Object> params=new HashMap<>();
		Map<String, Object> result=new HashMap<>();
		params.put("cPage",cPage);
		params.put("numPerpage", numPerpage);
		int totalData=service.communityCount();
		String pageBar=PageFactory.getPage(cPage, numPerpage, totalData, "mypageCommunity");
		List<Community> list=service.mypageCommunity(memberId, params);
		result.put("mypageCommunity", list);
		result.put("pageBar", pageBar);
		return result;
	}
}
















