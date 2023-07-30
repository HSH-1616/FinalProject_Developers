package com.dev.ac.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.ac.dto.Accommodation;
import com.dev.ac.service.AcService;

@RequestMapping("/ac")
@Controller
public class AcController {

	private AcService service;

	public AcController(AcService service) {
		this.service = service;
	}

	@RequestMapping("/acList")
	public String acListAll(Model m) {
		List<Accommodation> ac = service.acListAll();
		m.addAttribute("ac", service.acListAll());
//		for (Accommodation aa : ac) {
//			System.out.println(aa);
//		}
		return "/accommodation/acList";
	}

	@GetMapping("/acSearch")
	public String acSearch(Map param, String loc, String checkIn, String checkOut, String people, Model m) {
		param.put("loc", loc);
		param.put("checkIn", checkIn);
		param.put("checkOut", checkOut);
		param.put("people", people.substring(0,people.length()-1));
		System.out.println(people);
		m.addAttribute("as", service.searchListAll(param));
		return "/accommodation/acSearch";
	}

//	@GetMapping("/acSearch")
//	@ResponseBody
//	public List<Accommodation> acSearch(Map param, String loc, String checkIn, String checkOut, String people) {
//		param.put("loc", loc);
//		param.put("checkIn", checkIn);
//		param.put("checkOut", checkOut);
//		param.put("people", people);
//
//		return service.searchListAll(param);
//	}

}
