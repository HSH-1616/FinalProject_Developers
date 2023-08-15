package com.dev.admin.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dev.admin.common.PageFactory;
import com.dev.admin.model.dto.Admin;
import com.dev.admin.service.AdminService;
import com.dev.food.model.dto.Food;
import com.dev.food.model.service.FoodService;
import com.dev.member.model.dto.Black;
import com.dev.member.model.dto.Member;
import com.dev.touris.model.vo.Touris;


@Controller
@SessionAttributes({"memberId","loginAdmin"})
@RequestMapping("/admin")
public class AdminController {
	
	private AdminService service;
	
	private FoodService foodService;
	
	public AdminController(AdminService service, FoodService foodService) {
		this.service=service;
		this.foodService=foodService;
	}
	
	
	@GetMapping
	public String adminPage() {
		return "admin/loginadmin";
	}
	@GetMapping("/adminMain")
	public String adminMain() {
		return "admin/adminMain";
	}
	@PostMapping("/adminlogin")
	public String adminLogin(@RequestParam Map param,Model m) {
		System.out.println(param);
		Admin a=service.adminLogin(param);
		System.out.println(a);
		if(a!=null) {
			m.addAttribute("loginAdmin",a);
			return "redirect:/admin/adminMain";
		}else {
			return "admin/loginadmin";			
		}
	}
	@GetMapping("/selectMemberAll")
	public String selectMemberAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="1") int numPerpage,Model m){
		Map<String,Object> param=new HashMap();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		Map<String,String> type=new HashMap();
		List<Member> members=service.selectMemberAll(param);
		int totalData=service.selectMemberAllCount();
		List<Black> blacks=service.selectBlackAll();
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"selectMemberAll",type));
		m.addAttribute("totalData",totalData);
		m.addAttribute("members",members);
		m.addAttribute("blacks",blacks);
		return "admin/memberList";
	}
	@GetMapping("/blackpage")
	public String blackpage(String memberId,Model m) {
		m.addAttribute("memberId",memberId);
		return "admin/common/black";
	}
	@GetMapping("/black")
	@ResponseBody
	public void insertBlack(@RequestParam Map param) {
		service.insertBlack(param);
		service.updateBlack(param);
	}
	@GetMapping("/blackClear")
	@ResponseBody
	public void blackClear(@RequestParam Map param) {
		service.blackClear(param);
		service.updateClear(param);
	}
	
	@GetMapping("/selectBySocial")
	public String selectBySocial(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="2") int numPerpage, @RequestParam String memberCategory, Model m) {
		Map<String,Object> param=new HashMap();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("memberCategory", memberCategory);
		Map<String,String> type=new HashMap();
		type.put("typeId", "memberCategory");
		type.put("value", memberCategory);
		List<Member> members=service.selectBySocial(param);
		int totalData=service.selectBySocialCount(memberCategory);
		List<Black> blacks=service.selectBlackAll();
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"selectBySocial",type));
		m.addAttribute("members",members);
		m.addAttribute("blacks",blacks);
		m.addAttribute("memberCategory",memberCategory);
		return "admin/memberList";
	}
	@GetMapping("/selectTourisAll")
	public String selectTourisAll(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="10") int numPerpage,Model m){
		Map<String,Object> param=new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		Map<String,Object> type=new HashMap<>();
		List<Touris> tourises=service.selectTourisAll(param);
		int totalData=service.selectTourisAllCount();
		int tourisTempCount=service.selectTourisTempAllCount();
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"selectTourisAll",type));
		m.addAttribute("totalData",totalData);
		m.addAttribute("tourisTempCount",tourisTempCount);
		m.addAttribute("tourises",tourises);
		return "admin/tourisList";
	}
	@GetMapping("/searchTouris")
	public String searchTouris(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="10") int numPerpage, @RequestParam Map data, Model m) {
		Map<String,Object> param=new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("tourisName", data.get("tourisName"));
		Map<String,Object> type=new HashMap<>();
		type.put("typeId", "tourisName");
		type.put("value", data.get("tourisName"));
		List<Touris> tourises=service.searchTouris(param);
		int totalData=service.searchTourisCount(param);
		m.addAttribute("totalData",totalData);
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"searchTouris",type));
		m.addAttribute("tourises",tourises);
		m.addAttribute("tourisName",data.get("tourisName"));
		return "admin/tourisList";
	}
	@GetMapping("/selectBytourisAreaId")
	public String selectBytourisAreaId(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="10") int numPerpage, @RequestParam Map data, Model m) {
		Map<String,Object> param=new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("tourisAreaId", data.get("tourisAreaId"));
		
		Map<String,Object> type=new HashMap<>();
		type.put("typeId", "tourisAreaId");
		type.put("value", data.get("tourisAreaId"));
		
		List<Touris> tourises=service.selectBytourisAreaId(param);
		int totalData=service.selectBytourisAreaIdCount(param);
		
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"selectBytourisAreaId",type));
		m.addAttribute("totalData",totalData);
		m.addAttribute("tourises",tourises);
		m.addAttribute("tourisAreaId",data.get("tourisAreaId"));
		return "admin/tourisList";
	}
	
	@GetMapping("/tourisUpdate")
	@ResponseBody
	public void tourisUpdate() {
		service.tourisUpdate();
	}
	
//	===========================지환=========================
	@GetMapping("/selectFoodList")
	public String selectFoodList(@RequestParam(value="cPage",defaultValue="1") int cPage,
			@RequestParam(value="numPerpage",defaultValue="10") int numPerpage, Model m){
		//페이지처리용 Map
		Map<String,Object> param=new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		
		//검색처리 Map
		Map<String,Object> type=new HashMap<>();
//		type.put("typeId", "tourisAreaId");
//		type.put("value", data.get("tourisAreaId"));
		
		List<Food> foodList=service.searchFood(param);
		int totalData=service.selectFoodCount();
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"selectFoodList",type));
		m.addAttribute("totalData",totalData);
		m.addAttribute("foods",foodList);
		return "admin/foodList";
	}
	
	@GetMapping("/selectFoodByFoodNo")
	public String selectFoodByFoodNo(int foodNo, Model m) {
		//foodNo로 food,foodPhoto dto불러오고 출력(출력페이지에서 승인,미승인 여부 네비게이션으로) //리뷰는 어쩌지?
		List<Food> foods = foodService.selectFoodByFoodNo(foodNo);
		m.addAttribute("foods", foods);
		return "admin/foodDetail";
	}
	
	@GetMapping("/updateFoodByFoodNo")
	public String updateFoodByFoodNo(Food f) {
		System.out.println("flag");
		return "/admin/adminMain";
	}
}

