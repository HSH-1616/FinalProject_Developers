package com.dev.ac.pay.controller;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.ac.pay.dto.KaKaoPayApproval;
import com.dev.ac.pay.dto.KaKaoPayReady;
import com.dev.ac.pay.service.AcPayService;
import com.dev.member.model.dto.Member;

@RequestMapping("/pay")
@Controller
public class AcPayController {
	
	
	
	private AcPayService service;
	private HttpSession session;
	
	public AcPayController(AcPayService service,HttpSession session) {
		this.service = service;
		this.session=session;
	}
	
	@GetMapping("/kakao")
	@ResponseBody
	public KaKaoPayReady kakaoPayReady(String acId, String acTitle, String acPrice) {
		Member member=(Member)session.getAttribute("loginMember");
		String memberId=String.valueOf(member.getMemberId());
		return service.kakaoPayReady(acId,memberId,acTitle,acPrice);
	}
	
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
		return "redirect:";
	}
	
	@GetMapping("/kakaoPayOk")
    public String kakaoPayApporval(@RequestParam("pg_token") String pgToken,@ModelAttribute("tid") String tid) {
		Member member=(Member)session.getAttribute("loginMember");
		String memberId=String.valueOf(member.getMemberId());
		KaKaoPayApproval KaKaoPayApproval = service.KaKaoPayApproval(pgToken,memberId);
		return "/accommodation/acPayResult";
    }
}
