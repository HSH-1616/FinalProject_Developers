package com.dev.food.controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.dev.common.PageFactory;
import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodBlackList;
import com.dev.food.model.dto.FoodPhoto;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodReview;
import com.dev.food.model.dto.FoodReviewPhoto;
import com.dev.food.model.dto.FoodTemp;
import com.dev.food.model.service.FoodService;
import com.dev.member.model.dto.Member;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Controller
@RequestMapping("/food")
public class FoodController {

	@Autowired
	private FoodService service;
	
	public FoodController(FoodService service) {
		this.service = service;
	}
	
	@GetMapping("/foodList.do")
	public String startFoodApi()throws IOException{
		//api 불러오기
		callApi();
		return "redirect:/food/foodList2.do";
	}
	
	@GetMapping("/foodList2.do")
	public String selectFoodAll( @RequestParam(value="cPage",defaultValue ="1") int cPage, 
			@RequestParam(value="numPerpage",defaultValue ="12") int numPerpage,Model m)throws IOException {
		
		//DB값 불러오기
		List<Food> foods=service.selectFoodAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectFoodCount();
		
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "foodList.do"));

		m.addAttribute("totalData",totalData);
		m.addAttribute("foods",foods);
		
		return "food/foodList";
	}
	
	@GetMapping("/searchFood.do")
	public String searchFood(@RequestParam("searchType") String searchType, @RequestParam("keyword") String keyword,
			@RequestParam(value="cPage",defaultValue ="1") int cPage, @RequestParam(value="numPerpage",defaultValue ="12") int numPerpage ,Model m) {
		
		Map<String, Object> searchPage=new HashMap<String, Object>();
		List<Food> foodList= service.searchFood(Map.of("cPage",cPage,"numPerpage",numPerpage,"searchType",searchType,"keyword",keyword));
		int totalData=service.selectFoodCount();
		
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData,"foodList.do"));

		m.addAttribute("foods",foodList);
		
		return "food/foodList";
	}
	
	@RequestMapping("/insertFood.do")
	public String insertFood() {
		
		return "food/foodUpdate";
	}
	
	@RequestMapping("/foodListTitle.do")
	public String foodListTitle(@RequestParam(value="cPage",defaultValue ="1") int cPage, 
			@RequestParam(value="numPerpage",defaultValue ="12") int numPerpage,Model m) {
		
		List<Food> foods=service.selectFoodAll(Map.of("cPage",cPage,"numPerpage",numPerpage));
		int totalData=service.selectFoodCount();
		
		m.addAttribute("pageBar",PageFactory.getPage(cPage, numPerpage, totalData, "foodList.do"));

		m.addAttribute("totalData",totalData);
		m.addAttribute("foods",foods);		
		
		return "food/foodListTitle";
	}
	
	
	//메소드
	//@GetMapping("/foodApi")
	public void callApi()throws IOException{
		
		//api개수 불러오기 로직
		StringBuilder result0 = new StringBuilder();
		String urlStr0 = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
				+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
				+ "&" + "MobileOS"+"="+"ETC"
				+ "&" + "MobileApp"+"="+"foodTest"
				+ "&" + "numOfRows"+"="+ "12" //"17055"
				+ "&" + "pageNo"+"="+"1"
				+ "&" + "contentTypeId"+"="+"39"
				+ "&" + "_type"+"="+"json"
				+ "&" + "listYN"+"="+"N"
				+ "&" + "arrange"+"="+"O";
		
		//URL로 서버와 통신하기
		
		//1. URL객체만들기
		URL url0 = new URL(urlStr0);
		//2. URL에서 URLConnection객체얻기(http://로 접근하면 HttpURLConnection)
		HttpURLConnection urlConnection0 = (HttpURLConnection) url0.openConnection();
		//3. URLConnection구성(setRequestMethod:URL요청을 받을 방식)
		urlConnection0.setRequestMethod("GET");
		//4. 입력스트림 가져오기 및 데이터 읽기(BufferedReader:데이터를 문자열로 읽기 가능,
		//		getInputStream():외부에서 데이터를 읽을 수 있게 된다, Charset.forName("UTF-8"):"UTF-8"설정)
		BufferedReader br0 = new BufferedReader(
				new InputStreamReader(urlConnection0.getInputStream(), Charset.forName("UTF-8")));

		String returnLine0;
		//readLine():받은 내용을 한줄씩 읽어들이기
		while ((returnLine0 = br0.readLine()) != null) {
			result0.append(returnLine0 + "\n\r");
		}
		urlConnection0.disconnect();
		
		//여기서 부터 파싱 코드

		//파싱할 객체 생성
		JsonObject obj0 = JsonParser.parseString(result0.toString()).getAsJsonObject();
		//System.out.println(obj0);
		JsonArray arr0 = obj0.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
				.getAsJsonObject().get("item").getAsJsonArray();
		
		int apiCount = 0;
		for (JsonElement jsonElement : arr0) {
			JsonObject item0 = jsonElement.getAsJsonObject();
			apiCount = Integer.parseInt(item0.get("totalCnt").toString().replaceAll("\"", ""));
			//System.out.println("apiCount : "+apiCount);
		}
		
		int result = service.selectFoodCount();
		int blackList = service.selectFoodBlackListCount();
		//System.out.println("음식점 수 : "+result);
		
		//api와 DB가 동일한 숫자이면 업데이트 x
		//if(result+blackList != apiCount) {
		if(true) {
			//api -> DB
			//System.out.println("api -> DB");

			StringBuilder result1 = new StringBuilder();
			String urlStr = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
					+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
					+ "&" + "MobileOS"+"="+"ETC"
					+ "&" + "MobileApp"+"="+"foodTest"
					+ "&" + "numOfRows"+"="+ 12 //"17055" apiCount
					+ "&" + "pageNo"+"="+"1"
					+ "&" + "contentTypeId"+"="+"39"
					+ "&" + "_type"+"="+"json"
					+ "&" + "arrange"+"="+"O";
			
			//URL로 서버와 통신하기
			
			//1. URL객체만들기
			URL url = new URL(urlStr);
			//2. URL에서 URLConnection객체얻기(http://로 접근하면 HttpURLConnection)
			HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
			//3. URLConnection구성(setRequestMethod:URL요청을 받을 방식)
			urlConnection.setRequestMethod("GET");
			//4. 입력스트림 가져오기 및 데이터 읽기(BufferedReader:데이터를 문자열로 읽기 가능,
			//		getInputStream():외부에서 데이터를 읽을 수 있게 된다, Charset.forName("UTF-8"):"UTF-8"설정)
			BufferedReader br = new BufferedReader(
					new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));

			String returnLine;
			//readLine():받은 내용을 한줄씩 읽어들이기
			while ((returnLine = br.readLine()) != null) {
				result1.append(returnLine + "\n\r");
			}
			urlConnection.disconnect();
			
			//여기서 부터 파싱 코드

			//파싱할 객체 생성
			JsonObject obj = JsonParser.parseString(result1.toString()).getAsJsonObject();
			JsonArray arr = obj.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
					.getAsJsonObject().get("item").getAsJsonArray();
			
			for (JsonElement jsonElement : arr) {
				JsonObject item = jsonElement.getAsJsonObject();
				if(!item.get("firstimage").toString().replaceAll("\"", "").isEmpty()) {
					
					String StringType = item.get("contentid").toString().replaceAll("\"", "");
					
					//null이 없는 값만 객체저장
					if(item.get("title")!=null&&item.get("addr1")!=null&&item.get("firstimage")!=null) {
						
						FoodTemp food = FoodTemp.builder()
								.foodNo(Integer.parseInt(StringType))
								.foodName(item.get("title").toString().replaceAll("\"", ""))
								.foodAddress(item.get("addr1").toString().replaceAll("\"", ""))
								.build();
						FoodPhotoTemp fp = FoodPhotoTemp.builder()
								.foodNo(Integer.parseInt(StringType))
								.fpName(item.get("firstimage").toString().replaceAll("\"", ""))
								.fpMain(1)
								.fpId(item.get("firstimage").toString().replaceAll("\"", ""))
								.build();
						
//						System.out.println("foodNo : "+food);
//						System.out.println("foodPhoto : "+fp);
//						System.out.println();
						
						service.insertFood(food);
						service.mergeFood();
						service.insertFoodPhoto(fp);
						service.mergeFoodPhoto();
					}else {
						//조건을 충족하지 못하는 아이디만 모아서 분기처리에 사용
						System.out.println("불충분 조건 맛집리스트");
						System.out.println("foodNo : "+Integer.parseInt(StringType)+", foodName : "+
								item.get("title").toString().replaceAll("\"", "")+", foodAddress : "+
								item.get("addr1").toString().replaceAll("\"", ""));
						FoodBlackList fb = FoodBlackList.builder().foodNo(Integer.parseInt(StringType)).build();
						service.insertFoodBlackList(fb);
					}
					
				}
			}
		}
//		//DB 불러오는 과정
//		int count = 50;
//		List<Food> foods = service.selectFoodAllTest(count); //FOOD + FOODPHOTO
//		System.out.println("flag : "+foods);
//		m.addAttribute("foods", foods);
//		return "food/foodList2";
	}
	
	@RequestMapping("/foodDetail.do")
	public String selectFoodByNo(Model m, int no) throws IOException {
//		m.addAttribute("food",service.selectFoodById(no));
		foodInfoApi(no,m);
		System.out.println("flag");
		return "food/foodDetail";
	}

	//@GetMapping("/foodInfoApi")
	public void foodInfoApi(int foodNo, Model m) throws IOException{
		
		String result = service.searchByFoodNo(foodNo);
		System.out.println("상세정보 유무 : "+result);
		if(result == null) {
			//api -> DB
			System.out.println("api -> DB");
			StringBuilder result2 = new StringBuilder();
			String urlStr2 = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?"
					+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
					+ "&" + "MobileOS"+"="+"ETC"
					+ "&" + "MobileApp"+"="+"foodTest"
					+ "&" + "numOfRows"+"="+ "10" //"결과가 1개 나오면 지워도 됨"
					+ "&" + "pageNo"+"="+"1"
					+ "&" + "contentId"+"="+foodNo
					+ "&" + "contentTypeId"+"="+"39"
					+ "&" + "_type"+"="+"json";
			
			//1. URL객체만들기
			URL url2 = new URL(urlStr2);
			HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
			urlConnection2.setRequestMethod("GET");
			BufferedReader br2 = new BufferedReader(
					new InputStreamReader(urlConnection2.getInputStream(), Charset.forName("UTF-8")));
			
			String returnLine2;
			while ((returnLine2 = br2.readLine()) != null) {
				result2.append(returnLine2 + "\n\r");
			}
			
			urlConnection2.disconnect();
			
			//여기서 부터 파싱 코드
			
			JsonObject obj2 = JsonParser.parseString(result2.toString()).getAsJsonObject();
			JsonArray arr2 = obj2.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
					.getAsJsonObject().get("item").getAsJsonArray();
			
//		Gson gson = new Gson();
//		JsonObject objo = gson.fromJson(result2.toString(), JsonObject.class);
//		System.out.println(objo);
//		JsonObject obj2 = objo.getAsJsonObject("response").getAsJsonObject("body")
//				.getAsJsonObject("items");
//		JsonArray arr2 = obj2.getAsJsonArray("item");
			
			JsonElement jsonElement2 = arr2.get(0);
			JsonObject item2 = jsonElement2.getAsJsonObject();
			String opentimefood = item2.get("opentimefood").toString().replaceAll("\"", "");
			String restdatefood = item2.get("restdatefood").toString().replaceAll("\"", "");
			String treatmenu = item2.get("treatmenu").toString().replaceAll("\"", "");
			String infocenterfood = item2.get("infocenterfood").toString().replaceAll("\"", "");
			
			System.out.println("===========================가게정보===========================");
			System.out.println("음식점Id : "+foodNo);
			System.out.println("오픈시간 : "+opentimefood);	
			System.out.println("휴무일 : "+restdatefood);
			System.out.println("메뉴 : "+treatmenu);
			System.out.println("연락처 : "+infocenterfood);
			System.out.println("===============================================================");
			
			//String StringType = item2.get("contentid").toString().replaceAll("\"", "");
			
			//null이 없는 값만 객체저장================================================================================================
			if(!opentimefood.isEmpty()&&!restdatefood.isEmpty()&&!treatmenu.isEmpty()&&!infocenterfood.isEmpty()) {
				
				FoodTemp food = FoodTemp.builder()
						.foodNo(foodNo)
						.foodOpenTime("오픈시간 : "+opentimefood+"\n\r"+"휴무일 : "+restdatefood)
						.foodMenu(treatmenu)
						.foodPhone(infocenterfood)
						.build();
				
				service.updateFood(food); //TEMP에 UPDATE
				//System.out.println("updateflag : "+service.selectFoodTempByFoodNo(foodNo));
				
				//opentime, menu, phone이 없으면 update
				Food f = service.selectFoodByNo(foodNo);
				//System.out.println("flag : "+f);
				if(f == null) {
					service.updateFoodOnNull(food);
				}
				service.mergeFood(); //FOOD에 없으면 INSERT
				//System.out.println("mergeflag : "+service.selectFoodByFoodNo(foodNo));
				//foodImgApi(foodNo);
				
			}else {
				//조건을 충족하지 못하는 아이디만 모아서 분기처리에 사용
				System.out.println("foodNo : "+foodNo+", "+opentimefood+", "+
				restdatefood+", "+treatmenu+", "+infocenterfood);
				FoodBlackList fb = FoodBlackList.builder().foodNo(foodNo).build();
				service.insertFoodBlackList(fb);
			}

			//사용할 일 없으니 삭제
			//service.deleteFoodTemp(Integer.parseInt(StringType));
			//service.deleteFoodPhotoTemp(Integer.parseInt(StringType));
		}
		foodImgApi(foodNo);
		//음식점의 리뷰 불러오기
		//System.out.println(foodNo);
		//List<FoodReview> reviews = service.selectFoodReviewByFoodNo(foodNo);
		//System.out.println("reviews : "+reviews);
		//m.addAttribute("reviews", reviews);
		
		//DB에서 불러오는 과정(리뷰)
		List<Food> foods = service.selectFoodByFoodNo(foodNo); //FOOD + FOODPHOTO + FOODREVIEW
		System.out.println("flagS : "+foods);
		m.addAttribute("foods", foods);
//		return "/food/foodDetail";
	}
	
//	@GetMapping("/foodImgApi")
	public void foodImgApi(int foodNo) throws IOException{

		StringBuilder result3 = new StringBuilder();
		//System.out.println(foodNo);
		String urlStr3 = "https://apis.data.go.kr/B551011/KorService1/detailImage1?"
				+ "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
				+ "&" + "MobileOS"+"="+"ETC"
				+ "&" + "MobileApp"+"="+"foodTest"
				+ "&" + "numOfRows"+"="+ "5"
				+ "&" + "pageNo"+"="+"1"
				+ "&" + "contentId"+"="+foodNo
				+ "&" + "_type"+"="+"json"
				+ "&" + "imageYN"+"="+"Y"
				+ "&" + "subImageYN"+"="+"Y";
		
		//URL로 서버와 통신하기
		URL url3 = new URL(urlStr3);
		HttpURLConnection urlConnection3 = (HttpURLConnection) url3.openConnection();
		urlConnection3.setRequestMethod("GET");
		BufferedReader br3 = new BufferedReader(
				new InputStreamReader(urlConnection3.getInputStream(), Charset.forName("UTF-8")));
		
		String returnLine3;
		while ((returnLine3 = br3.readLine()) != null) {
			result3.append(returnLine3 + "\n\r");
		}
		
		urlConnection3.disconnect();
		
		//여기서 부터 파싱 코드
//		JsonObject obj3 = JsonParser.parseString(result3.toString()).getAsJsonObject();
		
//		JsonObject obj3 = JsonParser.parseString(result3.toString()).getAsJsonObject();
//		JsonArray arr3 = obj3.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//				.getAsJsonObject().get("item").getAsJsonArray();
		
		Gson gson = new Gson();
		JsonObject objo = gson.fromJson(result3.toString(), JsonObject.class);
		//System.out.println(objo);
		JsonObject obj3 = objo.getAsJsonObject("response").getAsJsonObject("body")
				.getAsJsonObject("items");
		JsonArray arr3 = obj3.getAsJsonArray("item");
		
		List<String> originimgurl = new ArrayList<String>();
		for (JsonElement jsonElement3 : arr3) {
			JsonObject item3 = jsonElement3.getAsJsonObject();
			System.out.println("===========================가게 이미지===========================");
			System.out.println("음식점Id : "+foodNo);	
			System.out.println("원본이미지 : "+item3.get("originimgurl"));
			System.out.println("===============================================================");
			
			//null이 없는 값만 객체저장================================================================================================
			if(!item3.get("originimgurl").toString().replaceAll("\"", "").isEmpty()) {
				
				FoodPhotoTemp fp = FoodPhotoTemp.builder()
						.foodNo(foodNo)
						.fpName(item3.get("originimgurl").toString().replaceAll("\"", ""))
						.fpMain(0)
						.fpId(item3.get("originimgurl").toString().replaceAll("\"", ""))
						.build();
				
				service.insertFoodPhoto(fp); //temp 에 insert
				//System.out.println("updateflag : "+service.selectFoodTempByFoodNo(foodNo));
				
//				//FOODPhoto에 INSERT
				service.mergeFoodPhoto();
				
				//fpName, fpMain, fpId가 없으면 update
				FoodPhoto f = service.selectFoodPhotoByNo(item3.get("originimgurl").toString().replaceAll("\"", "")); //photo에 조회
				System.out.println("null-flag : "+f);
				if(f == null) {
					service.updateFoodPhotoOnNull(fp); //selectOne인데 2개이상 나온다 함
				}
				System.out.println("mergeflag : "+service.selectFoodPhotoByNo(item3.get("originimgurl").toString().replaceAll("\"", "")));
				
			}else {
				//조건을 충족하지 못하는 아이디만 모아서 분기처리에 사용
				System.out.println("foodNo : "+foodNo+", "+item3.get("originimgurl").toString().replaceAll("\"", ""));
				FoodBlackList fb = FoodBlackList.builder().foodNo(foodNo).build();
				service.insertFoodBlackList(fb);
			}

		}
	}
	
	@PostMapping("/insertFoodReview.do")
	@ResponseBody
	public void insertFoodReview(HttpSession session, MultipartFile[] upFile, FoodReview fr) {
		
		//session에서 직접 받아오기
		Member member = (Member)session.getAttribute("loginMember");
		//member에서 객체를 생성해서 int 주입
		fr.setMember(Member.builder().memberId(member.getMemberId()).build());
		fr.setMemberId(member.getMemberId());
		System.out.println("dto : "+fr);
		System.out.println("FRGRADE CHECK : "+fr.getFrGrade());
		
		//파일을 저장할경로 가져오기
		String path = session.getServletContext().getRealPath("/images/upload/food/");
		System.out.println("path : "+path);
		System.out.println("upFile : "+upFile);
		//파일 업로드+dto에 추가
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				if(!mf.isEmpty()) {
					//원래이름 + 개명된 이름 설정
					String rpName = mf.getOriginalFilename();
					String ext = rpName.substring(rpName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int)(Math.random()*10000)+1;
					String rename = sdf.format(today)+"_"+rdn+ext;
					
					try {
						mf.transferTo(new File(path+rename));				
					}catch(IOException e) {
						e.printStackTrace();
					}
	
					FoodReviewPhoto rp = FoodReviewPhoto.builder()
							.rpName(rpName)
							.rpRename(rename)
							.build();
					System.out.println("photo : "+rp);
					fr.getFoodReviewPhoto().add(rp);
				}
			}
		}
		try {
			service.insertFoodReview(fr);
		}catch(RuntimeException e) {
			e.printStackTrace();
			//실패시 DB에는 값이 없지만 upload파일은 남는 문제가 생겨 같이 제거해주는 과정이 필요하다.		
			for(FoodReviewPhoto p : fr.getFoodReviewPhoto()) {
				File delFile=new File(path+p.getRpRename());
				delFile.delete();
			}
			
		}
	}
	
	@PostMapping("/updateFoodReview.do")
	@ResponseBody
	public void updateFoodReview(HttpSession session, MultipartFile[] upFile, FoodReview fr) {
		
		//session에서 직접 받아오기
		Member member = (Member)session.getAttribute("loginMember");
		//member에서 객체를 생성해서 int 주입
		fr.setMember(Member.builder().memberId(member.getMemberId()).build());
		fr.setMemberId(member.getMemberId());
		System.out.println("dto : "+fr);
		System.out.println("FRGRADE CHECK : "+fr.getFrGrade());
		
		//파일을 저장할경로 가져오기
		String path = session.getServletContext().getRealPath("/images/upload/food/");
		System.out.println("path : "+path);
		System.out.println("upFile : "+upFile);
		
		//upload파일 삭제하는 로직 
		List<FoodReviewPhoto> photoList = service.selectFoodReviewPhotoByFoodNo(fr.getFrNo());
		System.out.println("photoList : "+photoList);
		
		if(!photoList.isEmpty()) {
			//upload삭제
			for(FoodReviewPhoto p : photoList) {
				System.out.println("p : "+p.getRpName());
				File delFile=new File(path+p.getRpRename());
				delFile.delete();
			}
			//DB삭제
			service.deleteFoodReviewPhoto(fr.getFrNo());
		}
		
		//파일 업로드+dto에 추가
		if(upFile!=null) {
			for(MultipartFile mf:upFile) {
				if(!mf.isEmpty()) {
					//원래이름 + 개명된 이름 설정
					String rpName = mf.getOriginalFilename();
					String ext = rpName.substring(rpName.lastIndexOf("."));
					Date today = new Date(System.currentTimeMillis());
					SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd_HHmmssSSS");
					int rdn = (int)(Math.random()*10000)+1;
					String rename = sdf.format(today)+"_"+rdn+ext;
					
					try {
						mf.transferTo(new File(path+rename));				
					}catch(IOException e) {
						e.printStackTrace();
					}
					FoodReviewPhoto rp = FoodReviewPhoto.builder()
						.rpName(rpName)
						.rpRename(rename)
						.build();
					System.out.println("photo : "+rp);
					fr.getFoodReviewPhoto().add(rp);
					//service.updateFoodReviewPhoto(rp);
				}
			}
		}
		try {
			//update구문
			service.updateFoodReview(fr);
		}catch(RuntimeException e) {
			e.printStackTrace();
			//실패시 DB에는 값이 없지만 upload파일은 남는 문제가 생겨 같이 제거해주는 과정이 필요하다.		
			for(FoodReviewPhoto p : fr.getFoodReviewPhoto()) {
				File delFile=new File(path+p.getRpRename());
				delFile.delete();
			}
			
		}
	}
	
	@PostMapping("/deleteFoodReview.do")
	@ResponseBody
	public void deleteFoodReview(HttpSession session, int frNo) {
		//db에서 값 지우면 upload파일도 삭제하는 로직
		String path = session.getServletContext().getRealPath("/images/upload/food/");
		List<FoodReviewPhoto> rp = service.selectFoodReviewPhotoByFoodNo(frNo);
		System.out.println("rp : "+rp);
		
		if(!rp.isEmpty()) {
			//upload삭제
			for(FoodReviewPhoto p : rp) {
				System.out.println("p : "+p.getRpName());
				File delFile=new File(path+p.getRpRename());
				delFile.delete();
			}
			//DB삭제
			service.deleteFoodReview(frNo);			
		}
	}

	@GetMapping("/insertHeart")
	@ResponseBody
	public int insertHeart(@RequestParam Map param, HttpSession session, int foodNo) {
		Member loginMember=(Member)session.getAttribute("loginMember");
		param.put("memberId", loginMember.getMemberId());
		param.put("foodNo", foodNo);
		int result = service.insertHeart(param);
		return result;
	}

	@GetMapping("/deleteHeart")
	@ResponseBody
	public int deleteHeart(@RequestParam Map param,HttpSession session, int foodNo) {
		Member loingMember=(Member)session.getAttribute("loginMember");
		param.put("memberId", loingMember.getMemberId());
		param.put("foodNo", foodNo);
		int result = service.deleteHeart(param);
		return result;
	}
	
	/*
	 * @GetMapping("/add") public String showAddForm(Model model) {
	 * model.addAttribute("food", new Food()); return "food/foodUpdate"; }
	 */
	
	@PostMapping("/add")
    public String addFood(Food food) {
        service.addFood(food);
        
        return "redirect:/food/add";
    }
	
	/*
	 * @GetMapping("/mypage/foodheart")
	 * 
	 * @ResponseBody public List<Food> foodHeartList(@RequestParam int memberId){
	 * 
	 * 
	 * }
	 */
	
}
