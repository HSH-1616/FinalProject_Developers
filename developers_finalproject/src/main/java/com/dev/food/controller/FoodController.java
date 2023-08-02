package com.dev.food.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.dev.common.PageFactory;
import com.dev.food.model.dto.Food;
import com.dev.food.model.service.FoodService;

@Controller
@RequestMapping("/food")
public class FoodController {

	private FoodService service;
	
	public FoodController(FoodService service) {
		this.service=service;
	}
	
	@RequestMapping("/foodList.do")
	public String selectFoodAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="12") int numPerpage, Model m) {
		
		List<Food> foods=service.selectFoodAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectFoodCount();
		
		//페이징
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "foodList.do"));
		
		m.addAttribute("totalData",totalData);
		m.addAttribute("foods",foods);
		
		return "/food/foodList";
	}
	
	@RequestMapping("/insertFood.do")
	public String insertFood() {
		
		return "/food/foodUpdate";
	}
	
	@RequestMapping("/FoodView.do")
	public String selectFoodByNo(Model m, int no) {
		m.addAttribute("board",service.selectFoodById(no));
		return "board/boardView";
	}
	
	@RequestMapping("/foodBackList.do")
	public String foodBackList(){
		
		return "/food/foodList";
	}
	
	
	
	/*
	 * @RequestMapping("/paging") public String
	 * paging(@RequestParam(value="cPage",defaultValue="1") int cPage,
	 * 
	 * @RequestParam(value="numPerpage", defaultValue="5") int numPerpage) {
	 * 
	 * List<Food> foods=service.selectPage(cPage,numPerpage);
	 * 
	 * return ""; }
	 */
}
