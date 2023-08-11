package com.dev.food.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.dev.common.PageFactory;
import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodHeart;
import com.dev.food.model.dto.FoodPhotoTemp;
import com.dev.food.model.dto.FoodTemp;
import com.dev.food.model.service.FoodService;
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
	public String selectFoodAll( @RequestParam(value="cPage",defaultValue ="1") int cPage, 
			@RequestParam(value="numPerpage",defaultValue ="12") int numPerpage,Model m) {
		
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
	
	@GetMapping("/foodApi")
	public String callApi(Model m) throws Exception {
		
		int result = service.selectFoodCount();
		System.out.println("음식점 수 : "+result);
		if(result == 0) {
			//api -> DB
			System.out.println("api -> DB");

			StringBuilder result1 = new StringBuilder();
			String urlStr = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
					+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
					+ "&" + "MobileOS"+"="+"ETC"
					+ "&" + "MobileApp"+"="+"foodTest"
					+ "&" + "numOfRows"+"="+ "10000" //"17055"
					+ "&" + "pageNo"+"="+"1"
					+ "&" + "contentTypeId"+"="+"39"
					+ "&" + "_type"+"="+"json";
			
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
				//System.out.println(!item.get("firstimage").toString().replaceAll("\"", "").isEmpty());
				if(!item.get("firstimage").toString().replaceAll("\"", "").isEmpty()) {
					//System.out.println("음식Id : "+item.get("contentid"));
					//System.out.println("대표 이미지 : "+item.get("firstimage").toString().replaceAll("\"", ""));
					//System.out.println("이름 : "+item.get("title"));
					//System.out.println("주소 : "+item.get("addr1"));
					//System.out.println("우편번호 : "+item.get("zipcode"));
					//System.out.println(item.get("areaCode")); //잘 안나옴
					//System.out.println(item.get("sigungucode"));
					//System.out.println("x좌표 : "+item.get("mapx"));
					//System.out.println("y좌표 : "+item.get("mapy"));
					//System.out.println("===============================================================");
					
					String StringType = item.get("contentid").toString().replaceAll("\"", "");
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
//					System.out.println(food);
//					System.out.println(fp);
					service.insertFood(food,fp);
					service.mergeFood();
					service.mergeFoodPhoto();
					service.deleteFoodTemp();
					service.deleteFoodPhotoTemp();
				}
			}
		}
		
		//DB 불러오는 과정
		List<Food> foods = service.selectFoodAllTest(); //FOOD + FOODPHOTO
		System.out.println("flag : "+foods);
		m.addAttribute("foods", foods);			
		return "food/foodList2";
	}
	
	@RequestMapping("/foodDetail.do")
	public String selectFoodByNo(Model m, int no) {
//		m.addAttribute("food",service.selectFoodById(no));
		return "food/foodDetail";
	}

	@GetMapping("/foodInfoApi")
	public String foodInfoApi(int foodNo, Model m) throws IOException,Exception {
		
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
			System.out.println("===========================가게정보===========================");
			System.out.println("음식점Id : "+foodNo);
			System.out.println("오픈시간 : "+item2.get("opentimefood"));	
			System.out.println("휴무일 : "+item2.get("restdatefood"));
			System.out.println("메뉴 : "+item2.get("treatmenu"));
			System.out.println("연락처 : "+item2.get("infocenterfood"));
			System.out.println("===============================================================");
			
			String StringType = item2.get("contentid").toString().replaceAll("\"", "");
			FoodTemp food = FoodTemp.builder()
					.foodNo(Integer.parseInt(StringType))
					.foodOpenTime("오픈시간 : "+item2.get("opentimefood")+"\n\r"+"휴무일 : "+item2.get("restdatefood").toString().replaceAll("\"", ""))
					.foodMenu(item2.get("treatmenu").toString().replaceAll("\"", ""))
					.foodPhone(item2.get("infocenterfood").toString().replaceAll("\"", ""))
					.build();
			service.updateFood(food); //TEMP에 UPDATE
			service.mergeFood(); //FOOD에 없으면 MERGE
			//다하면 TEMP는 지워야할까?
			foodImgApi(foodNo);
			service.deleteFoodTemp();
			service.deleteFoodPhotoTemp();
		}
		
		//DB에서 불러오는 과정
		List<Food> foods = service.selectFoodByFoodNo(foodNo); //FOOD + FOODPHOTO
		System.out.println("flagS : "+foods);
		m.addAttribute("foods", foods);
		return "/food/foodView";
	}
	
//	@GetMapping("/foodImgApi")
	public void foodImgApi(int foodNo) throws IOException,Exception {

		StringBuilder result3 = new StringBuilder();
		System.out.println(foodNo);
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
		System.out.println("====================flag========================");
//		JsonObject obj3 = JsonParser.parseString(result3.toString()).getAsJsonObject();
		
//		JsonObject obj3 = JsonParser.parseString(result3.toString()).getAsJsonObject();
//		JsonArray arr3 = obj3.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//				.getAsJsonObject().get("item").getAsJsonArray();
		
		Gson gson = new Gson();
		JsonObject objo = gson.fromJson(result3.toString(), JsonObject.class);
		System.out.println(objo);
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
			FoodPhotoTemp fp = FoodPhotoTemp.builder()
				.foodNo(foodNo)
				.fpName(item3.get("originimgurl").toString().replaceAll("\"", ""))
				.fpMain(0)
				.fpId(item3.get("originimgurl").toString().replaceAll("\"", ""))
				.build();
			service.insertFoodPhoto(fp);
			service.mergeFoodPhoto();
			System.out.println("이미지 list 결과 : "+fp);
		}
	}
	
	//좋아요 기능
	@ResponseBody
    @PostMapping("/food/toggleHeart")
    public int toggleHeart(@RequestParam Map params, int fhNo, String memberId, int foodNo) {
       
		params.put("fhNo", fhNo);
        params.put("memberId", memberId);
        params.put("foodNo", foodNo);

        int updatedCount = service.toggleHeartAndGetCount(params);
        return updatedCount;
    }
	
	/*
	 * //분류기능(제목)
	 * 
	 * @GetMapping("/foodListTitle.do") public String
	 * foodListTitle(@RequestParam("sortFilter") String sortFilter, Model model) {
	 * // 여기에서 sortFilter를 기준으로 필요한 데이터를 가져오는 로직을 구현 // 데이터를 모델에 담아서 JSP로 전달
	 * model.addAttribute("foods", sortedFoods); // foods는 JSP에서 사용하는 변수명, 가져온 데이터를
	 * 할당해야 합니다.
	 * 
	 * return "foodList	"; }
	 */
	
	
	
}
