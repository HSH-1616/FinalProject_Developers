package com.dev.food.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.dev.food.model.dto.Food;
import com.dev.food.model.dto.FoodPhoto;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Controller
public class FoodController {
	
	@GetMapping("/testFood")
	public String testFood() {
		return "/main";
	}
	
	@GetMapping("/foodApi")
	public String callApi(Model m) throws IOException {

			//지역기반 관광정보조회(주소:addr1, zipcode, 지역코드:areaCode, 시군구코드:sigungucode
			//				대표이미지:firstimage, 좌표:mapx, mapy, 고유번호:contentid, 제목:title)
			StringBuilder result = new StringBuilder();

			String urlStr = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
					+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
					+ "&" + "MobileOS"+"="+"ETC"
					+ "&" + "MobileApp"+"="+"foodTest"
					+ "&" + "numOfRows"+"="+ "10" //"17055"
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
				result.append(returnLine + "\n\r");
			}
			urlConnection.disconnect();
			
			//여기서 부터 파싱 코드

			//파싱할 객체 생성
			JsonObject obj = JsonParser.parseString(result.toString()).getAsJsonObject();
			JsonArray arr = obj.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
					.getAsJsonObject().get("item").getAsJsonArray();
			//지역기반 관광정보조회(주소:addr1, zipcode, 좌표:mapx, mapy, 고유번호:contentid, 제목:title)
			int count = 0;
			List<Food> foods = new ArrayList<Food>();
			for (JsonElement jsonElement : arr) {
				JsonObject item = jsonElement.getAsJsonObject();
				System.out.println(!item.get("firstimage").toString().replaceAll("\"", "").isEmpty());
				if(!item.get("firstimage").toString().replaceAll("\"", "").isEmpty()) {
					System.out.println("음식Id : "+item.get("contentid"));
					System.out.println("대표 이미지 : "+item.get("firstimage").toString().replaceAll("\"", ""));
					System.out.println("이름 : "+item.get("title"));
					//System.out.println("주소 : "+item.get("addr1"));
					//System.out.println("우편번호 : "+item.get("zipcode"));
					//System.out.println(item.get("areaCode")); //잘 안나옴
					//System.out.println(item.get("sigungucode"));
					//System.out.println("x좌표 : "+item.get("mapx"));
					//System.out.println("y좌표 : "+item.get("mapy"));
					
					String StringType = item.get("contentid").toString().replaceAll("\"", "");
					Food food = Food.builder()
							.foodNo(Integer.parseInt(StringType))
							.foodName(item.get("title").toString().replaceAll("\"", ""))
							.FoodPhoto(FoodPhoto.builder()
									.FPName(item.get("firstimage").toString().replaceAll("\"", ""))
									.FPMain(1)
									.build())
							.foodAddress(item.get("addr1").toString().replaceAll("\"", ""))
							.build();
					foods.add(food);
					m.addAttribute("foods", foods);
					count++;
					System.out.println("===============================================================");
				}
			}
			
			
		return "food/foodList";
	}
	
	
	@GetMapping("/foodInfoApi")
	public String foodInfoApi(@RequestBody(required = false)List<Food> foods, Model m) throws IOException {
		
		System.out.println(foods);
		//필요한거 : 음식종류(ex:이탈리안), 
		//교려중인거 : 문의 및 안내, 포장가능, 주차시설, 
		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, )
		StringBuilder result2 = new StringBuilder();
		
		String urlStr2 = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?"
				+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
				+ "&" + "MobileOS"+"="+"ETC"
				+ "&" + "MobileApp"+"="+"foodTest"
				+ "&" + "numOfRows"+"="+ "10" //"결과가 1개 나오면 지워도 됨"
				+ "&" + "pageNo"+"="+"1"
				+ "&" + "contentId"+"="+foods.get(0).getFoodNo()
				+ "&" + "contentTypeId"+"="+"39"
				+ "&" + "_type"+"="+"json";
		
		//URL로 서버와 통신하기
		
		//1. URL객체만들기
		URL url2 = new URL(urlStr2);
		//2. URL에서 URLConnection객체얻기(http://로 접근하면 HttpURLConnection)
		HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
		//3. URLConnection구성(setRequestMethod:URL요청을 받을 방식)
		urlConnection2.setRequestMethod("GET");
		//4. 입력스트림 가져오기 및 데이터 읽기(BufferedReader:데이터를 문자열로 읽기 가능,
		//		getInputStream():외부에서 데이터를 읽을 수 있게 된다, Charset.forName("UTF-8"):"UTF-8"설정)
		BufferedReader br2 = new BufferedReader(
				new InputStreamReader(urlConnection2.getInputStream(), Charset.forName("UTF-8")));
		
		String returnLine2;
		//readLine():받은 내용을 한줄씩 읽어들이기
		while ((returnLine2 = br2.readLine()) != null) {
			result2.append(returnLine2 + "\n\r");
		}
		urlConnection2.disconnect();
		
		//여기서 부터 파싱 코드
		
		//파싱할 객체 생성
		JsonObject obj2 = JsonParser.parseString(result2.toString()).getAsJsonObject();
		JsonArray arr2 = obj2.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
				.getAsJsonObject().get("item").getAsJsonArray();
		//필요한거 : 음식종류(ex:이탈리안), 연락처, 
		//교려중인거 : 문의 및 안내, 포장가능, 주차시설, 
		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, 연락처:infocenterfood)
		
		//List<Food> foods = new ArrayList<Food>();
		for (JsonElement jsonElement2 : arr2) {
			JsonObject item2 = jsonElement2.getAsJsonObject();
			System.out.println("===========================가게정보===========================");
			System.out.println("음식점Id : "+foods.get(0).getFoodNo());
			System.out.println("연락처 : "+item2.get("infocenterfood"));
			System.out.println("오픈시간 : "+item2.get("opentimefood"));	
			System.out.println("휴무일 : "+item2.get("restdatefood"));
			System.out.println("메뉴 : "+item2.get("treatmenu"));
			System.out.println("좌석 : "+item2.get("seat"));
			System.out.println("===============================================================");
			
			String StringType = item2.get("contentid").toString().replaceAll("\"", "");
//			Food food = Food.builder()
//					.foodNo(Integer.parseInt(StringType))
//					//.foodName(item2.get("title").toString().replaceAll("\"", ""))
//					.foodOpenTime("오픈시간 : "+item2.get("opentimefood").toString().replaceAll("\"", "")
//							+"휴무일 : "+item2.get("restdatefood").toString().replaceAll("\"", ""))
//					.foodMenu(item2.get("treatmenu").toString().replaceAll("\"", ""))
//					.foodPhone(item2.get("infocenterfood").toString().replaceAll("\"", ""))
//					.build();
//			foods.add(food);
//			food.setFoodOpenTime(returnLine2);
//			m.addAttribute("foods", foods);
		}
		
//		//=============================================================================================
//		
//		
//		//필요한거 : 음식종류(ex:이탈리안), 
//		//교려중인거 : 문의 및 안내, 포장가능, 주차시설, 
//		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, )
//		StringBuilder result3 = new StringBuilder();
//		
//		String urlStr3 = "http://apis.data.go.kr/B551011/KorService1/detailImage1?"
//				+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
//				+ "&" + "MobileOS"+"="+"ETC"
//				+ "&" + "MobileApp"+"="+"foodTest"
//				+ "&" + "numOfRows"+"="+ "5"
//				+ "&" + "pageNo"+"="+"1"
//				+ "&" + "contentId"+"="+contentId
//				+ "&" + "_type"+"="+"json";
//		
//		//URL로 서버와 통신하기
//		
//		//1. URL객체만들기
//		URL url3 = new URL(urlStr3);
//		//2. URL에서 URLConnection객체얻기(http://로 접근하면 HttpURLConnection)
//		HttpURLConnection urlConnection3 = (HttpURLConnection) url3.openConnection();
//		//3. URLConnection구성(setRequestMethod:URL요청을 받을 방식)
//		urlConnection3.setRequestMethod("POST");
//		//4. 입력스트림 가져오기 및 데이터 읽기(BufferedReader:데이터를 문자열로 읽기 가능,
//		//		getInputStream():외부에서 데이터를 읽을 수 있게 된다, Charset.forName("UTF-8"):"UTF-8"설정)
//		BufferedReader br3 = new BufferedReader(
//				new InputStreamReader(urlConnection3.getInputStream(), Charset.forName("UTF-8")));
//		
//		String returnLine3;
//		//readLine():받은 내용을 한줄씩 읽어들이기
//		while ((returnLine3 = br3.readLine()) != null) {
//			result3.append(returnLine3 + "\n\r");
//		}
//		urlConnection3.disconnect();
//		
//		//여기서 부터 파싱 코드
//		
//		//파싱할 객체 생성
//		System.out.println("====================flag========================");
//		
//		JsonObject obj3 = JsonParser.parseString(result3.toString()).getAsJsonObject();
//		JsonArray arr3 = obj3.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//				.getAsJsonObject().get("item").getAsJsonArray();
//		//필요한거 : 음식종류(ex:이탈리안), 연락처,
//		//교려중인거 : 문의 및 안내, 포장가능, 주차시설,
//		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, 연락처:infocenterfood)
//		int imgcount = 0;
//		List<String> originimgurl = new ArrayList<String>();
//		for (JsonElement jsonElement3 : arr3) {
//			JsonObject item3 = jsonElement3.getAsJsonObject();
//			System.out.println("===========================가게 이미지===========================");
//			System.out.println("음식점Id : "+contentId);
//			System.out.println("이미지명 : "+item3.get("imgname"));	
//			System.out.println("원본이미지 : "+item3.get("originimgurl"));
//			System.out.println("===============================================================");
//
//			//Map.of("imgname",item3.get("imgname"),"originimgurl",item3.get("originimgurl"));
////			String imgType = item3.get("originimgurl").toString().replaceAll("\"", "");
////			System.out.println("형변환 결과 : "+imgType);
////			originimgurl.add(imgType);
////			System.out.println("반복횟수("+imgcount+") , "+originimgurl.size());
////			m.addAttribute("originimgurl", originimgurl);
////			imgcount++;
//		}
//
////		ModelAndView mav2 = new ModelAndView("/food/foodView");
////		return mav2;
		return "/food/foodView";
	}
	
	
	@GetMapping("/foodImgApi")
	public String foodImgApi(String contentId, Model m) throws IOException {
		//=============================================================================================
		
		
		//필요한거 : 음식종류(ex:이탈리안), 
		//교려중인거 : 문의 및 안내, 포장가능, 주차시설, 
		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, )
		StringBuilder result3 = new StringBuilder();
		System.out.println(contentId);
		String urlStr3 = "https://apis.data.go.kr/B551011/KorService1/detailImage1?"
				+ "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
				+ "&" + "MobileOS"+"="+"ETC"
				+ "&" + "MobileApp"+"="+"foodTest"
				+ "&" + "numOfRows"+"="+ "5"
				+ "&" + "pageNo"+"="+"1"
				+ "&" + "contentId"+"="+contentId
				+ "&" + "_type"+"="+"json"
				+ "&" + "imageYN"+"="+"Y"
				+ "&" + "subImageYN"+"="+"Y";
		
		//URL로 서버와 통신하기
		
		//1. URL객체만들기
		URL url3 = new URL(urlStr3);
		//2. URL에서 URLConnection객체얻기(http://로 접근하면 HttpURLConnection)
		HttpURLConnection urlConnection3 = (HttpURLConnection) url3.openConnection();
		//3. URLConnection구성(setRequestMethod:URL요청을 받을 방식)
		urlConnection3.setRequestMethod("GET");
		//4. 입력스트림 가져오기 및 데이터 읽기(BufferedReader:데이터를 문자열로 읽기 가능,
		//		getInputStream():외부에서 데이터를 읽을 수 있게 된다, Charset.forName("UTF-8"):"UTF-8"설정)
		BufferedReader br3 = new BufferedReader(
				new InputStreamReader(urlConnection3.getInputStream(), Charset.forName("UTF-8")));
		
		String returnLine3;
		//readLine():받은 내용을 한줄씩 읽어들이기
		while ((returnLine3 = br3.readLine()) != null) {
			result3.append(returnLine3 + "\n\r");
		}
		urlConnection3.disconnect();
		
		//여기서 부터 파싱 코드
		
		//파싱할 객체 생성
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
		
		
		//필요한거 : 음식종류(ex:이탈리안), 연락처,
		//교려중인거 : 문의 및 안내, 포장가능, 주차시설,
		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, 연락처:infocenterfood)
		int imgcount = 0;
		List<String> originimgurl = new ArrayList<String>();
		for (JsonElement jsonElement3 : arr3) {
			JsonObject item3 = jsonElement3.getAsJsonObject();
			System.out.println("===========================가게 이미지===========================");
			System.out.println("음식점Id : "+contentId);
			System.out.println("이미지명 : "+item3.get("imgname"));	
			System.out.println("원본이미지 : "+item3.get("originimgurl"));
			System.out.println("===============================================================");

			//Map.of("imgname",item3.get("imgname"),"originimgurl",item3.get("originimgurl"));
			String imgType = item3.get("originimgurl").toString().replaceAll("\"", "");
			System.out.println("형변환 결과 : "+imgType);
			originimgurl.add(imgType);
			System.out.println("반복횟수("+imgcount+") , "+originimgurl.size());
			m.addAttribute("originimgurl", originimgurl);
			imgcount++;
		}

//		ModelAndView mav2 = new ModelAndView("/food/foodView");
//		return mav2;
		return "/food/foodImg";
	}
}
