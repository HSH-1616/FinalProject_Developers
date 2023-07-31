package com.dev.food.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
public class FoodController {

	@GetMapping("/foodList.do")
	public String foodList() {
		
		return "/food/foodList";
	}
	
	@GetMapping("/updateFood.do")
	public String updateFood() {
		
		return "/food/foodUpdate";
	}
	
	
}
