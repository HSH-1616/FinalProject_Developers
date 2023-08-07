package com.dev.food.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.dev.common.PageFactory;
import com.dev.food.model.dto.Food;
import com.dev.food.model.service.FoodService;

@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService service;
	
	public FoodController(FoodService service) {
		this.service=service;
	}
	
	/*
	 * @RequestMapping("/foodList.do") public String
	 * selectFoodAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
	 * 
	 * @RequestParam(value="numPerpage",defaultValue="12") int numPerpage, Model m)
	 * {
	 * 
	 * List<Food>
	 * foods=service.selectFoodAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
	 * int totalData=service.selectFoodCount();
	 * 
	 * //페이징 m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage,
	 * totalData, "foodList.do"));
	 * 
	 * m.addAttribute("totalData",totalData); m.addAttribute("foods",foods);
	 * 
	 * return "/food/foodList"; }
	 */
	
	@GetMapping("/foodList.do")
	@ResponseBody
	public ModelAndView selectFoodAll( @RequestParam(value="cPage",defaultValue ="1") int cPage, @RequestParam(value="numPerpage",defaultValue ="12") int numPerpage) {
		
		ModelAndView mav = new ModelAndView();
		
		Map<String, Object> paging=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<>();
		paging.put("cPage", cPage);
		paging.put("numPerpage", numPerpage);
		int totalData=service.selectFoodCount();
		
		String pageBar=PageFactory.getPage(cPage, numPerpage, totalData, "foodListEnd.do");
		
		List<Food> foods= service.selectFoodAll(paging);
		result.put("foodList", foods);
		result.put("pageBar", pageBar);
		
		mav.addObject("foods", foods);
		mav.setViewName("/food/foodList");

		return mav;
	}
	
	@RequestMapping("/insertFood.do")
	public String insertFood() {
		
		return "/food/foodUpdate";
	}
	
	@RequestMapping("/foodDetail.do")
	public String selectFoodByNo(Model m, int no) {
//		m.addAttribute("food",service.selectFoodById(no));
		return "food/foodDetail";
	}
	
	@GetMapping("/searchFood.do")
	@ResponseBody
	public Map<String,Object> searchFood(@RequestParam("type") String type, @RequestParam("keyword") String keyword,@RequestParam(value="cPage",defaultValue ="1") int cPage, @RequestParam(value="numPerpage",defaultValue ="12") int numPerpage ,Model m) {
		Map<String, Object> params=new HashMap<String, Object>();
		Map<String, Object> paging=new HashMap<String, Object>();
		Map<String,Object> result=new HashMap<>();
		params.put("type", type);
		params.put("keyword", keyword);
		paging.put("cPage", cPage);
		paging.put("numPerpage", numPerpage);
		List<Food> foodList= service.searchFood(params,paging);
		int totalData=service.selectFoodCount();
		String pageBar=PageFactory.getPage(cPage, numPerpage, totalData,"searchFood");
		result.put("foodList", foodList);
		result.put("pageBar", pageBar);
		return result;
	}
	
	
}
