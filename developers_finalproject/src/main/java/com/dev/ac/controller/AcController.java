package com.dev.ac.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.ac.dto.AcFacilities;
import com.dev.ac.dto.AcFile;
import com.dev.ac.dto.AcReservation;
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;
import com.dev.ac.service.AcService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

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
		if (people.equals("인원추가")) {
			people = "";
		} else {
			people = people.substring(0, people.length() - 1);
		}
		param.put("loc", loc);
		param.put("checkIn", checkIn);
		param.put("checkOut", checkOut);
		param.put("people", people);
		m.addAttribute("as", service.searchListAll(param));
		return "/accommodation/acSearch";
	}

	@GetMapping("/acDetail")
	public String acDetail(int no, Model m) {
		m.addAttribute("ad", service.acDetail(no));
		return "/accommodation/acDetail";
	}

	@PostMapping("/acPay")
	public String acPay(int no, String checkIn, String checkOut, String people, int resultPrice, Model m) {

		Accommodation ac = service.acPay(no);

		// 개발도구에서 가격변경 위조 방지
		int checkPrice = ac.getAcPrice();

		people = people.substring(0, people.length() - 1);
		int maxPeople = Integer.parseInt(people);

		// 날짜 차이 계산
		Date format1 = null;
		try {
			format1 = new SimpleDateFormat("yyyy-MM-dd").parse(checkIn);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		Date format2 = null;
		try {
			format2 = new SimpleDateFormat("yyyy-MM-dd").parse(checkOut);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		long diffSec = (format2.getTime() - format1.getTime()) / 1000; // 초 차이
		long diffDays = diffSec / (24 * 60 * 60); // 일자수 차이

		if (maxPeople > 2) {
			for (int i = 2; i < maxPeople; i++) {
				checkPrice += 20000;
			}
		}

		// db가격과 비교
		if (checkPrice * diffDays == resultPrice) {
			m.addAttribute("checkIn", checkIn);
			m.addAttribute("checkOut", checkOut);
			m.addAttribute("people", people);
			m.addAttribute("ap", ac);
			m.addAttribute("resultPrice", resultPrice);
			m.addAttribute("diff", diffDays);
			return "/accommodation/acPay";
		} else {
			return "/accommodation/errorPage";
		}

	}

//	@GetMapping("/insertPay")
//	// @RequestMapping(value="/insertPay", method=RequestMethod.POST)
//	public String insertPay(Map param, HttpServletRequest req) {
//
//		// flashMap에 담겨있는 object 가져오기
//		Map redirectParam = RequestContextUtils.getInputFlashMap(req);
//		if (redirectParam != null) {
//			param = (Map) redirectParam.get("param"); // 오브젝트 타입이라 캐스팅해줌
//		}
//		int result = service.insertPay(param);
//
//		if (result > 0) {
//			return "redirect:/ac/acPayResult";
//		} else {
//			return "redirect:/ac/acError";
//		}
//	}
//
//	@PostMapping("/deletetPay")
//	// @RequestMapping(value="/insertPay", method=RequestMethod.POST)
//	public String deletePay(Map param, HttpServletRequest req) {
//
//		// flashMap에 담겨있는 object 가져오기
//		Map redirectParam = RequestContextUtils.getInputFlashMap(req);
//		if (redirectParam != null) {
//			param = (Map) redirectParam.get("param"); // 오브젝트 타입이라 캐스팅해줌
//		}
//		int result = service.insertPay(param);
//
//		if (result > 0) {
//			return "/accommodation/acPayResult";
//		} else {
//			return "/accommodation/acError";
//		}
//	}

	@GetMapping("/acPayResult")
	public String acPayResult() {
		return "/accommodation/acPayResult";
	}

	@GetMapping("/acError")
	public String acError() {
		return "/accommodation/acError";
	}

	@GetMapping("/acRefundTest")
	public String acRefundTest(Model m) {
		m.addAttribute("apl", service.acRefundTest());
		return "/accommodation/acRefundTest";
	}

	@GetMapping("/insertHeart")
	@ResponseBody
	public int insertHeart(@RequestParam Map param) {
		int result = service.insertHeart(param);
		return result;
	}

	@GetMapping("/deleteHeart")
	@ResponseBody
	public int deleteHeart(@RequestParam Map param) {
		int result = service.deleteHeart(param);
		return result;
	}

	@GetMapping("/acRegist")
	public String acRegist() {

		return "/accommodation/acRegist";
	}

	@PostMapping("/insertRegist")
	public String insertRegist(String[] checkIn, String[] checkOut, Accommodation ac, AcFacilities afa,
			String[] afalName, MultipartFile[] afImage, MultipartFile[] afalImage, HttpSession session) {

		String acPath = session.getServletContext().getRealPath("/images/upload/accommodation/");
		String afaPath = session.getServletContext().getRealPath("/images/upload/accommodation/afal/");
		List<AcReservation> arv = new ArrayList();
		if (checkIn != null && checkOut != null) {
			for (int i = 0; i < checkIn.length; i++) {
				System.out.println(checkIn[i]);
				java.sql.Date checkInDate = java.sql.Date.valueOf(checkIn[i]);
				java.sql.Date checkOutDate = java.sql.Date.valueOf(checkOut[i]);
				arv.add(AcReservation.builder().checkIn(checkInDate).checkOut(checkOutDate).build());
				ac.setArv(arv);
			}
		}

		if (afImage != null) {
			for (MultipartFile mf : afImage) {
				if (!mf.isEmpty()) {
					String oriName = mf.getOriginalFilename();
					String ext = oriName.substring(oriName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int) (Math.random() * 10000) + 1;
					String rename = "ac" + sdf.format(today) + "_" + rdn + ext;

					try {
						mf.transferTo(new File(acPath + rename));
					} catch (IOException e) {
						e.printStackTrace();
					}

					AcFile file = AcFile.builder().afName(rename).build();
					System.out.println(file);
					ac.getAcFiles().add(file);
				}
			}
		}
		System.out.println(afalImage);
		if (afalImage != null && afalName != null) {
			for (int i = 0; i < afalImage.length; i++) {
				String oriName = afalImage[i].getOriginalFilename();
				String ext = oriName.substring(oriName.lastIndexOf("."));
				Date today = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rdn = (int) (Math.random() * 10000) + 1;
				String rename = "afa" + sdf.format(today) + "_" + rdn + ext;

				try {
					afalImage[i].transferTo(new File(afaPath + rename));
				} catch (IOException e) {
					e.printStackTrace();
				}

				AfaList file = AfaList.builder().afalName(afalName[i]).afalImg(rename).build();
				System.out.println(file);
				afa.getAfal().add(file);
			}
		}

		ac.setAfa(afa);
		System.out.println(ac);

		return "/";
	}

	@PostMapping("/insertRegist2")
	@ResponseBody
	public String insertRegist2(String acData, MultipartFile[] afImage, MultipartFile[] afalImg, HttpSession session,
			String[] afalName, String[] afMain) {
		Accommodation ac = null;
		AcFacilities afa = new AcFacilities();
		ObjectMapper mapper = new ObjectMapper();
		try {
			ac = mapper.readValue(acData, Accommodation.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

		String acPath = session.getServletContext().getRealPath("/images/upload/accommodation/");
		String afaPath = session.getServletContext().getRealPath("/images/upload/accommodation/afal/");
		if (afImage != null && afMain != null) {
			for (int i = 0; i < afImage.length; i++) {
				String oriName = afImage[i].getOriginalFilename();
				String ext = oriName.substring(oriName.lastIndexOf("."));
				Date today = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rdn = (int) (Math.random() * 10000) + 1;
				String rename = "ac" + sdf.format(today) + "_" + rdn + ext;

				try {
					afImage[i].transferTo(new File(acPath + rename));
				} catch (IOException e) {
					e.printStackTrace();
				}
				AcFile file = AcFile.builder().afName(rename).afMain(afMain[i].charAt(0)).build();
				ac.getAcFiles().add(file);
			}
		}

		if (afalImg != null && afalName != null) {
			for (int i = 0; i < afalImg.length; i++) {
				String oriName = afalImg[i].getOriginalFilename();
				String ext = oriName.substring(oriName.lastIndexOf("."));
				Date today = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rdn = (int) (Math.random() * 10000) + 1;
				String rename = "afa" + sdf.format(today) + "_" + rdn + ext;

				try {
					afalImg[i].transferTo(new File(afaPath + rename));
				} catch (IOException e) {
					e.printStackTrace();
				}

				AfaList file = AfaList.builder().afalName(afalName[i]).afalImg(rename).build();
				afa.getAfal().add(file);
			}
		}
		ac.setAfa(afa);

		int result=service.insertAc(ac);
		
		return "/";
	}

}
