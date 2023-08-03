package com.dev.touris.model.controller;

import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.touris.model.service.TourisService;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/touris")
public class TourisController {
	private TourisService service;
	
	public TourisController(TourisService service) {
		this.service=service;
	}
	
	//메인페이지에서 
	@RequestMapping("/tourismainpage")
	public String tourisMainSelectMapPage(Model model) {
		model.addAttribute("area", service.tourisMainSelectMapPage());
		return "touris/tourismainpage";
	}
	
	@RequestMapping("/tourismap")
	public String tourislist(String areaId, Model model) throws Exception{
		model.addAttribute("tourislist", service.selecttourislist(areaId));
		model.addAttribute("titletouris", service.selecttourisarealist(areaId));
		return "touris/tourismap";
	}
	
	
}

