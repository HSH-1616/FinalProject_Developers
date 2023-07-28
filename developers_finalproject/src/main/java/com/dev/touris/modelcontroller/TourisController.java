package com.dev.touris.modelcontroller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/touris")
public class TourisController {
	
	
	@GetMapping("/tourismap")
	public String tourismap() {
		return "touris/tourismap";
	}
}

