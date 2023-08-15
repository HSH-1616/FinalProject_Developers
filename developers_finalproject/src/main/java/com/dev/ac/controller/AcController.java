package com.dev.ac.controller;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import com.dev.ac.dto.AcPayList;
import com.dev.ac.dto.AcReservation;
import com.dev.ac.dto.AcReview;
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;
import com.dev.ac.dto.ArFile;
import com.dev.ac.service.AcService;
import com.dev.admin.common.PageFactory;
import com.dev.member.model.dto.Member;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/ac")
@Controller
@Slf4j
public class AcController {

	private AcService service;
	private HttpSession session;

	public AcController(AcService service, HttpSession session) {
		this.service = service;
		this.session = session;
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
		Accommodation ad = service.acDetail(no);
		m.addAttribute("ad", ad);
		m.addAttribute("ah", service.acHeart(no));
		m.addAttribute("afal", service.updateRegistAfal(ad.getAfa().getAfaId()));
		m.addAttribute("ar", service.acReview(no));
		System.out.println(service.acReview(no));
		return "/accommodation/acDetail";
	}

	@PostMapping("/acPay")
	public String acPay(int no, String checkIn, String checkOut, String people, int resultPrice, Model m) {

		Accommodation ac = service.acPay(no);

		// 개발도구에서 가격변경 위조 방지
		int checkPrice = ac.getAcPrice();
		log.info("DB 가격 : " + checkPrice);
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

		log.info("인원수 측정 가격 : " + checkPrice);
		log.info("숙박일 : " + diffDays);
		log.info("총합 : " + resultPrice);
		// db가격과 비교
		if (checkPrice * diffDays == resultPrice) {
			m.addAttribute("checkIn", checkIn);
			m.addAttribute("checkOut", checkOut);
			m.addAttribute("people", people);
			m.addAttribute("checkPrice", checkPrice);
			m.addAttribute("ap", ac);
			m.addAttribute("resultPrice", resultPrice);
			m.addAttribute("diff", diffDays);
			return "/accommodation/acPay";
		} else {
			return "/accommodation/acError";
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

	@GetMapping("/selectAcAll")
	public String selectAcAll(Model m, @RequestParam(value = "cPage", defaultValue = "1") int cPage,
			@RequestParam(value = "numPerpage", defaultValue = "10") int numPerpage) {
		Map<String, Object> param = new HashMap<>();
		Map<String, Object> type = new HashMap<>();

		param.put("cPage", cPage);
		param.put("numPerpage", numPerpage);
		int totalData = service.selectAcAllCount();

		m.addAttribute("pageBar", PageFactory.getPage(cPage, numPerpage, totalData, "selectAcAll", type));
		m.addAttribute("totalData", totalData);
		m.addAttribute("ac", service.selectAcAll(param));
		return "/accommodation/acAdmin";

	}

	@GetMapping("/acRegist")
	public String acRegist() {
		return "/accommodation/acRegist";
	}

	@PostMapping("/insertRegist")
	@ResponseBody
	public int insertRegist(String acData, MultipartFile[] afImage, MultipartFile[] afalImg, HttpSession session,
			String[] afalName, String[] afMain) {
		Accommodation ac = null;

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
				ac.getAfa().getAfal().add(file);
			}
		}

		Map m = service.insertAc(ac);
		if ((int) m.get("result") > 1) {
			return (int) m.get("acId");
		} else {
			return 0;
		}
	}

	@GetMapping("/deleteRegist")
	@ResponseBody
	public int deleteRegist(int acId, HttpSession session) {

		String acPath = session.getServletContext().getRealPath("/images/upload/accommodation/");
		String afaPath = session.getServletContext().getRealPath("/images/upload/accommodation/afal/");
		List<AcFile> af = service.deleteImage(acId);
		List<AfaList> afal = service.deleteAfalImage(acId);
		File file;

		int result = service.deleteAc(acId);
		if (result > 1) {
			for (AcFile afImg : af) {
				file = new File(acPath + afImg.getAfName());
				file.delete();
				for (AfaList afalImg : afal) {
					file = new File(afaPath + afalImg.getAfalImg());
					file.delete();
				}
			}
		}
		return result;
	}

	@GetMapping("/updateRegist")
	public String updateRegist(int acId, HttpSession session, Model m) {
		Accommodation ac = service.updateRegist(acId);
		List<AcReservation> arv = service.updateRegistArv(acId);
		AcFacilities afa = service.updateRegistAfa(acId);
		List<AcFile> af = service.updateRegistAf(acId);
		List<AfaList> afal = null;
		if (afa != null) {
			afal = service.updateRegistAfal(afa.getAfaId());
		}
		m.addAttribute("ac", ac);
		m.addAttribute("arv", arv);
		m.addAttribute("afa", afa);
		m.addAttribute("afal", afal);
		m.addAttribute("af", af);

		return "accommodation/acUpdate";
	}

	@PostMapping("/updateAc")
	@ResponseBody
	public int updateAc(String acData, MultipartFile[] afImage, MultipartFile[] afalImage, HttpSession session,
			String[] afalName, String[] afMain) {
		Accommodation ac = null;
		File file;

		String acPath = session.getServletContext().getRealPath("/images/upload/accommodation/");
		String afaPath = session.getServletContext().getRealPath("/images/upload/accommodation/afal/");

		ObjectMapper mapper = new ObjectMapper();
		try {
			ac = mapper.readValue(acData, Accommodation.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}

//		1. 기존파일 이름을 js배열에 집어넣는다.
//		2. 삭제버튼을 누를시 배열에서 삭제
//		3. db에서 해당 숙박업소에 파일리스트를 가져온다
//		4. db와 가져온 배열과 비교해서 일치하지 않는 이름은  리스트에서 삭제 &&파일 삭제&& db전체 삭제
//		5. 새로 추가한 파일은 mulltifile로 따로 불러온다
//		6. 새로운 file리스트를 만들어서 db와비교한 리스트와 multifile리스트 추가
//		7. 메인 배열 file리스트에 추가

		// 기존 파일 이름 list
		log.info("////////////////////////////");
		log.info("js에서 가져온 list : " + ac.getAcFiles());
		// db에서 가져온 파일list
		List<AcFile> afDb = service.updateRegistCheckAf(ac.getAcId());
		List<String> requestFileName = ac.getAcFiles().stream().map(e -> e.getAfName()).toList();
		List<AcFile> resultFile = afDb.stream().filter(e -> !requestFileName.contains(e.getAfName())).toList();

		log.info("db와 비교 결과 : " + resultFile);
		// 파일이 삭제되면 db에서 전체 삭제
		for (AcFile afImg : resultFile) {
			file = new File(acPath + afImg.getAfName());
			file.delete();
		}
		// 새로운 파일리스트를 만든다
		List<AcFile> acFiles = new ArrayList();

		// js에서 가져온 리스트를 새로운 리스트에 추가
		for (int i = 0; i < ac.getAcFiles().size(); i++) {
			acFiles.add(ac.getAcFiles().get(i));
		}
		log.info("새로운 리스트에 비교 추가 : " + acFiles);
		// 새로 추가한 파일 업로드후 새로운 리스트에 추가

		if (afImage != null) {
			log.info("새로 추가된 파일 : " + afImage.length);
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
				AcFile files = AcFile.builder().afName(rename).build();
				acFiles.add(files);
			}
		}
		log.info("새로 추가한 파일 추가 : " + acFiles);

		// mainType 배열을 순서대로 추가 후 accommodation에 세팅
		if (afMain != null) {
			for (int i = 0; i < afMain.length; i++) {
				acFiles.get(i).setAcId(ac.getAcId());
				acFiles.get(i).setAfMain(afMain[i].charAt(0));
			}
		}
		ac.setAcFiles(acFiles);
		log.info("메인타입 추가 : " + acFiles);
		log.info("////////////////////////////");

		///////////////////////////////////////////////////////////////////////////////
		log.info("js로 불러온 편의시설 : " + ac.getAfa().getAfal());
		// db에서 가져온 list
		List<AfaList> afalDb = service.updateRegistCheckAfal(ac.getAfa().getAfaId());
		log.info("db에서 불러온 리스트 :" + afalDb);
		List<String> requesAfalFileName = ac.getAfa().getAfal().stream().map(e -> e.getAfalImg()).toList();
		List<AfaList> resultAfalFile = afalDb.stream().filter(e -> !requesAfalFileName.contains(e.getAfalImg()))
				.toList();

		log.info("db와 비교 결과 : " + resultAfalFile);

		// db값과 비교한 파일 삭제
		for (AfaList afalImg : resultAfalFile) {
			file = new File(afaPath + afalImg.getAfalImg());
			file.delete();
		}

		// 새로운 파일리스트와 객체를 만든다
		List<AfaList> afal = new ArrayList();

		// js에서 가져온 리스트 새로운 리스트에 추가
		if (ac.getAfa().getAfal() != null) {
			for (int i = 0; i < ac.getAfa().getAfal().size(); i++) {
				AfaList afa = AfaList.builder().afaId(ac.getAfa().getAfal().get(i).getAfaId())
						.afalImg(ac.getAfa().getAfal().get(i).getAfalImg())
						.afalName(ac.getAfa().getAfal().get(i).getAfalName()).build();
				afal.add(afa);
			}
		}
		log.info("새로운 리스트에 추가 : " + afal);

		// 업로드 한 파일 추가
		if (afalImage != null) {
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

				AfaList files = AfaList.builder().afalImg(rename).build();
				afal.add(files);
			}
		}
		log.info("업로드 파일 추가 : " + afal);
		// 편의시설 이름 배열을 순서대로 추가 후 accommodation에 세팅
		if (afalName != null) {
			for (int i = 0; i < afalName.length; i++) {
				afal.get(i).setAfaId(ac.getAfa().getAfaId());
				afal.get(i).setAfalName(afalName[i]);
			}
		}
		ac.getAfa().setAfal(afal);
		log.info("편의시설 이름 추가 : " + afal);
		log.info("////////////////////////////");

		int result = service.updateAc(ac);
		return ac.getAcId();
	}

	@GetMapping("/acMyPage")
	@ResponseBody
	public List<AcPayList> acMyPage() {
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = String.valueOf(member.getMemberId());

		List<AcPayList> am = service.acMyPage(memberId);
		return am;
	}

	@PostMapping("/acRefundApply")
	public String acRefundApply(String orderId, Model m) {
		AcPayList ra = service.acRefundApply(orderId);
		m.addAttribute("ra", ra);
		return "/accommodation/acRefundApply";
	}

	@PostMapping("/insertRefund")
	@ResponseBody
	public int insertRefund(String apId, String orderId, String refundReason, String refundContent) {
		Map<String, String> param = new HashMap<String, String>();
		param.put("apId", apId);
		param.put("orderId", orderId);
		param.put("refundReason", refundReason);
		param.put("refundContent", refundContent);
		int result = service.updateRefund(param);
		return 0;
	}

	@PostMapping("/insertReview")
	@ResponseBody
	public int insertReview(String reviewData, MultipartFile[] arfName) {		
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = String.valueOf(member.getMemberId());
		
		AcReview ar = new AcReview();

		String arPath = session.getServletContext().getRealPath("/images/upload/accommodation/review/");
		
		ObjectMapper mapper = new ObjectMapper();
		try {
			ar = mapper.readValue(reviewData, AcReview.class);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		ar.setMemberId(memberId);
		
		if (arfName != null) {
			for(MultipartFile mf:arfName) {
				String oriName = mf.getOriginalFilename();
				String ext = oriName.substring(oriName.lastIndexOf("."));
				Date today = new Date(System.currentTimeMillis());
				SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
				int rdn = (int) (Math.random() * 10000) + 1;
				String rename = "ar" + sdf.format(today) + "_" + rdn + ext;

				try {
					mf.transferTo(new File(arPath + rename));
				} catch (IOException e) {
					e.printStackTrace();
				}
				ArFile file = ArFile.builder().arfName(rename).build();
				ar.getArFiles().add(file);
			}
		}
		
		log.info("리뷰 : "+ar);				
						
		return service.insertReview(ar);
	}
	
	@GetMapping("/acCheckReview")
	@ResponseBody
	public List<AcReview> checkReview(){
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = String.valueOf(member.getMemberId());
		
		return service.checkReview(memberId);
	}
}
