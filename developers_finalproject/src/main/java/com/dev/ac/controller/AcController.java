package com.dev.ac.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.dev.ac.dto.Accommodation;
import com.dev.ac.service.AcService;

@RequestMapping("/ac")
@Controller
public class AcController {
	
	private AcService service;
	
	public AcController(AcService service) {
		this.service=service;
	}
	
	@RequestMapping("/acList")
	public String acListAll(Model m) {
		List<Accommodation> ac=service.acListAll();
		m.addAttribute("ac",service.acListAll());
		for (Accommodation aa : ac) {
			System.out.println(aa);
		}
		return "/accommodation/acList";
	}

}
