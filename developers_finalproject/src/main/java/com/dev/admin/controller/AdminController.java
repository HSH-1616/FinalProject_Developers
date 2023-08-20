package com.dev.admin.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.dev.ac.dto.AcPayList;
import com.dev.ac.service.AcService;
import com.dev.admin.common.PageFactory;
import com.dev.admin.model.dto.Admin;
import com.dev.admin.service.AdminService;
import com.dev.food.controller.FoodController;
import com.dev.food.model.dto.Food;
import com.dev.food.model.service.FoodService;
import com.dev.member.model.dto.Black;
import com.dev.member.model.dto.Member;
import com.dev.member.service.MemberService;
import com.dev.touris.model.service.TourisService;
import com.dev.touris.model.vo.Touris;

@Controller
@SessionAttributes({ "memberId", "loginAdmin" })
@RequestMapping("/admin")
public class AdminController {

	private TourisService tourisService;
	private FoodService foodService;
	private AcService acService;
	private MemberService memberService;
	private AdminService service;

	public AdminController(AdminService service, AcService acService, FoodService foodService,
			TourisService tourisService, MemberService memberService) {
		this.tourisService = tourisService;
		this.foodService = foodService;
		this.acService = acService;
		this.service = service;
		this.memberService = memberService;
	}

	@GetMapping
	public String adminPage() {
		return "admin/loginadmin";
	}

	@GetMapping("/adminMain")
	public String adminMain(Model m) {
		int tour = tourisService.tourisListCount();
		int foodCount = foodService.selectFoodCount();
		int ac = acService.selectAcAllCount();
		int member = service.selectMemberAllCount();

		m.addAttribute("tour", tour);
		m.addAttribute("foodCount", foodCount);
		m.addAttribute("ac", ac);
		m.addAttribute("member", member);
		return "admin/adminMain";
	}

	@GetMapping("/main")
	public String main(Model m) {
//		int tour = tourisService.tourisListCount();
//		int food = foodService.selectFoodCount();
//		int ac = acService.selectAcAllCount();
//		int member = service.selectMemberAllCount();
//		
//		m.addAttribute("tour", tour);
//		m.addAttribute("food", food);
//		m.addAttribute("ac", ac);
//		m.addAttribute("member", member);
		return "main";
	}

	@PostMapping("/adminlogin")
	public String adminLogin(@RequestParam Map param, Model m) {
		System.out.println(param);
		Admin a = service.adminLogin(param);
		System.out.println(a);
		if (a != null) {
			m.addAttribute("loginAdmin", a);
			return "redirect:/admin/adminMain";
		} else {
			return "admin/loginadmin";
		}
	}

	@GetMapping("/selectMemberAll")
	public String selectMemberAll(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "1") int numPerpage, Model m) {
		Map<String, Object> param = new HashMap();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		Map<String, String> type = new HashMap();
		List<Member> members = service.selectMemberAll(param);
		int totalData = service.selectMemberAllCount();
		List<Black> blacks = service.selectBlackAll();
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectMemberAll", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("members", members);
		m.addAttribute("blacks", blacks);
		return "admin/memberList";
	}

	@GetMapping("/blackpage")
	public String blackpage(String memberId, Model m) {
		m.addAttribute("memberId", memberId);
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
	public String selectBySocial(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "2") int numPerpage, @RequestParam String memberCategory,
			Model m) {
		Map<String, Object> param = new HashMap();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("memberCategory", memberCategory);
		Map<String, String> type = new HashMap();
		type.put("typeId", "memberCategory");
		type.put("value", memberCategory);
		List<Member> members = service.selectBySocial(param);
		int totalData = service.selectBySocialCount(memberCategory);
		List<Black> blacks = service.selectBlackAll();
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectBySocial", type));
		m.addAttribute("members", members);
		m.addAttribute("blacks", blacks);
		m.addAttribute("memberCategory", memberCategory);
		return "admin/memberList";
	}

	@GetMapping("/selectTourisAll")
	public String selectTourisAll(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, Model m) {
		Map<String, Object> param = new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		Map<String, Object> type = new HashMap<>();
		List<Touris> tourises = service.selectTourisAll(param);
		int totalData = service.selectTourisAllCount();
		int tourisTempCount = service.selectTourisTempAllCount();
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectTourisAll", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("tourisTempCount", tourisTempCount);
		m.addAttribute("tourises", tourises);
		return "admin/tourisList";
	}

	@GetMapping("/searchTouris")
	public String searchTouris(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, @RequestParam Map data, Model m) {
		Map<String, Object> param = new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("tourisName", data.get("tourisName"));
		Map<String, Object> type = new HashMap<>();
		type.put("typeId", "tourisName");
		type.put("value", data.get("tourisName"));
		List<Touris> tourises = service.searchTouris(param);
		int totalData = service.searchTourisCount(param);
		m.addAttribute("totalData", totalData);
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "searchTouris", type));
		m.addAttribute("tourises", tourises);
		m.addAttribute("tourisName", data.get("tourisName"));
		return "admin/tourisList";
	}

	@GetMapping("/selectBytourisAreaId")
	public String selectBytourisAreaId(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, @RequestParam Map data, Model m) {
		Map<String, Object> param = new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("tourisAreaId", data.get("tourisAreaId"));

		Map<String, Object> type = new HashMap<>();
		type.put("typeId", "tourisAreaId");
		type.put("value", data.get("tourisAreaId"));

		List<Touris> tourises = service.selectBytourisAreaId(param);
		int totalData = service.selectBytourisAreaIdCount(param);

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectBytourisAreaId", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("tourises", tourises);
		m.addAttribute("tourisAreaId", data.get("tourisAreaId"));
		return "admin/tourisList";
	}

	@GetMapping("/tourisUpdate")
	@ResponseBody
	public void tourisUpdate() {
		service.tourisUpdate();
	}

//	===========================지환=========================
	// 미승인 리스트 페이지
	@GetMapping("/selectFoodListNon")
	public String selectFoodListNon(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, Model m) {

		// 페이지처리용 Map
		Map<String, Object> param = new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);

		// 검색처리 Map
		Map<String, Object> type = new HashMap<>();

		// 미승인 리스트 출력
		List<Food> nonApprovefoodList = service.searchFoodNonApprove(param);
		System.out.println("미승인리스트 : "+nonApprovefoodList);
		int totalDataNonApprove = service.selectFoodCountNonApprove();
		int totalData = service.selectFoodCount();
		m.addAttribute("npageBar",
				PageFactory.getPage(cPage, numPerpage, totalDataNonApprove, "selectFoodListNon", type));
		m.addAttribute("nd", totalDataNonApprove);
		m.addAttribute("totalData", totalData);
		m.addAttribute("nf", nonApprovefoodList);

		return "admin/foodListNon";
	}

	@GetMapping("/selectFoodListApprove")
	public String selectFoodListApprove(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, Model m) {

		// 페이지처리용 Map
		Map<String, Object> param = new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);

		// 검색처리 Map
		Map<String, Object> type = new HashMap<>();

		// 승인 리스트 출력
		List<Food> foodList = service.searchFood(param);
		int totalData = service.selectFoodCount();
		int totalDataNonApprove = service.selectFoodCountNonApprove();
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectFoodListApprove", type));
		m.addAttribute("nd", totalDataNonApprove);
		m.addAttribute("totalData", totalData);
		m.addAttribute("foods", foodList);
		return "admin/foodListApprove";
	}

	@GetMapping("/paymentList")
	public String paymentList(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		int totalData = acService.paymentListCount();
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "paymentList", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ap", acService.paymentList(param));

		return "admin/paymentList";
	}

	@GetMapping("/refundList")
	public String refundList(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		int totalData = acService.refundListCount();

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "refundList", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ap", acService.refundList(param));
		return "admin/refundList";
	}

	@GetMapping("/refundOkList")
	public String refundOkList(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		int totalData2 = acService.refundListCount2();

		m.addAttribute("pageBar2", PageFactory.getPage(cPage, numPerpage, totalData2, "refundOkList", type));
		m.addAttribute("ap", acService.refundOkList(param));
		return "admin/refundOkList";
	}

	@GetMapping("/selectAcAll")
	public String selectAcAll(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		int totalData = acService.selectAcAllCount();

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectAcAll", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ac", acService.selectAcAll(param));
		return "/accommodation/acAdmin";
	}

	@GetMapping("/searchAc")
	public String selectAcAll(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, String acId, String acTitle) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("acId", acId);
		param.put("acTitle", acTitle);
		int totalData = acService.searchAcCount(param);

		if (acId != "" && acId != null) {
			type.put("typeId", "acId");
			type.put("value", acId);
		} else if (acTitle != "" && acTitle != null) {
			type.put("typeId", "acTitle");
			type.put("value", acTitle);
		}

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "searchAc", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ac", acService.searchAc(param));
		return "/accommodation/acAdmin";
	}

	@GetMapping("/paymentSearchList")
	public String paymentSearchList(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, String orderId, String memberId) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("orderId", orderId);
		param.put("memberId", memberId);
		int totalData = acService.paymentSearchListCount(param);

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "paymentSearchList", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ap", acService.paymentSearchList(param));

		return "admin/paymentList";
	}

	@GetMapping("/refundSearchList")
	public String refundSearchList(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, String orderId, String memberId) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("orderId", orderId);
		param.put("memberId", memberId);
		int totalData = acService.refundSearchListCount(param);

		if (orderId != "" && orderId != null) {
			type.put("typeId", "orderId");
			type.put("value", orderId);
		} else if (memberId != "" && memberId != null) {
			type.put("typeId", "memberId");
			type.put("value", memberId);
		}

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "refundSearchList", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ap", acService.refundSearchList(param));
		return "admin/refundList";
	}

	@GetMapping("/refundOkSearchList")
	public String refundOkSearchList(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, String orderId, String memberId) {

		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		param.put("orderId", orderId);
		param.put("memberId", memberId);

		if (orderId != "" && orderId != null) {
			type.put("typeId", "orderId");
			type.put("value", orderId);
		} else if (memberId != "" && memberId != null) {
			type.put("typeId", "memberId");
			type.put("value", memberId);
		}

		int totalData2 = acService.refundSearchListCount2(param);

		m.addAttribute("pageBar2", PageFactory.getPage(cPage, numPerpage, totalData2, "refundOkSearchList", type));
		m.addAttribute("ap", acService.refundOkSearchList(param));
		return "admin/refundOkList";
	}

	@GetMapping("/paymentDetail")
	public String paymentDetail(String orderId, Model m) {
		AcPayList ra = acService.acRefundApply(orderId);
		m.addAttribute("ra", ra);
		return "/admin/paymentDetail";
	}

	@GetMapping("/refundDetail")
	public String refundDetail(String orderId, Model m) {
		AcPayList ra = acService.acRefundApply(orderId);
		m.addAttribute("ra", ra);
		return "/admin/refundDetail";
	}

	@GetMapping("/selectFoodByFoodNo")
	public String selectFoodByFoodNo(int foodNo, Model m) throws IOException {

		// foodNo로 food,foodPhoto dto불러오고 출력(출력페이지에서 승인,미승인 여부 네비게이션으로) //리뷰는 어쩌지?
		List<Food> foods = foodService.selectFoodByFoodNo(foodNo);

		// 출력할 값이 없으면
		if (foods.get(0).getFoodPhoto().get(1).getFpName() == null) {
			// 상세정보 api 불러오기
			FoodController.foodInfoApi(foodNo, m);
		}

		System.out.println("qweqwe" + foods);
		m.addAttribute("foods", foods);
		return "admin/foodDetail";
	}

	@GetMapping("/updateFoodByFoodNo")
	public String updateFoodByFoodNo(Food f, Model m) {
		System.out.println("allow가 업데이트가 안되는 문제 :" + f);
		int result = foodService.updateFoodOnAdmin(f);
		if (result > 0) {
			m.addAttribute("result", "true");
		} else {
			m.addAttribute("result", "false");
		}
		return "redirect:/admin/adminMain";
	}

	@GetMapping("/deleteFoodByFoodNo")
	public String deleteFoodByFoodNo(int foodNo, HttpServletRequest request) {
		System.out.println(foodNo);
		int flag = foodService.deleteFoodOnAdmin(foodNo);
		String result;
		if (flag > 0) {
			result = "true";
		} else {
			result = "false";
		}
		HttpSession session = request.getSession();
		session.setAttribute("result", result);
		return "redirect:/admin/adminMain";
	}

//	===========================장흠=========================

	@GetMapping("/foodRecommend")
	public String foodRecommend(@RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage, Model m) {
		Map<String, Object> param = new HashMap<>();
		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);

		Map<String, Object> type = new HashMap<>();

		List<Food> foodList = service.searchFoodNonApprove(param);
		int totalData = service.selectFoodCountNonApprove();
		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectFoodList", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("foods", foodList);
		return "admin/foodRecommend";

	}
}
