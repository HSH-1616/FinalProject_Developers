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

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import com.google.gson.stream.MalformedJsonException;


@Controller
public class FoodController {
	
	@GetMapping("/testFood")
	public String testFood() {
		return "/main";
	}
	
	@GetMapping("/foodInfoApi")
	public String foodInfoApi(String contentId, Model m) throws IOException {
		
		//필요한거 : 음식종류(ex:이탈리안), 
		//교려중인거 : 문의 및 안내, 포장가능, 주차시설, 
		//소개정보조회(영업시간:opentimefood(쉬는날 포함:restdatefood), 취급메뉴:treatmenu, 좌석수:seat, )
		StringBuilder result2 = new StringBuilder();
		
		String urlStr2 = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?"
				+ "&" + "serviceKey"+"="+"Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D"
				+ "&" + "MobileOS"+"="+"ETC"
				+ "&" + "MobileApp"+"="+"foodTest"
				+ "&" + "numOfRows"+"="+ "10" //"17055"
				+ "&" + "pageNo"+"="+"1"
				+ "&" + "contentId"+"="+contentId
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
		for (JsonElement jsonElement2 : arr2) {
			JsonObject item2 = jsonElement2.getAsJsonObject();
			System.out.println("===========================가게정보===========================");
			System.out.println("음식점Id : "+contentId);
			System.out.println("연락처 : "+item2.get("infocenterfood"));
			System.out.println("오픈시간 : "+item2.get("opentimefood"));	
			System.out.println("휴무일 : "+item2.get("restdatefood"));
			System.out.println("메뉴 : "+item2.get("treatmenu"));
			System.out.println("좌석 : "+item2.get("seat"));
			System.out.println("===============================================================");
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
		
		String urlStr3 = "http://apis.data.go.kr/B551011/KorService1/detailImage1?"
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
		
		JsonObject obj3 = JsonParser.parseString(result3.toString()).getAsJsonObject();
		JsonArray arr3 = obj3.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
				.getAsJsonObject().get("item").getAsJsonArray();
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
			
			//값 넣을 때 굳이 encode없이도 가능함
//			String urlStr = "http://apis.data.go.kr/B551011/KorService1/areaBasedList1?"
//					+ URLEncoder.encode("serviceKey", "UTF-8") + "="
//					+ URLEncoder.encode(
//							"0906O7Vl32hAkLceKylOGOAzJuIESMtXTXESfLV++obF/XFUtduY0IZn4KnJnwSMB3L5HTj7oRuH8PqFhVAQ6w==",
//							"UTF-8")
//					+ "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&"
//					+ URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("12337", "UTF-8") + "&"
//					+ URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8") + "&"
//					+ URLEncoder.encode("areaCode", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&"
//					+ URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8") + "&"
//					+ URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8") + "&"
//					+ URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
			
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
			List<String> contentId = new ArrayList<String>();
			for (JsonElement jsonElement : arr) {
				JsonObject item = jsonElement.getAsJsonObject();
				System.out.println("주소 : "+item.get("addr1"));
				System.out.println("우편번호 : "+item.get("zipcode"));
				//System.out.println(item.get("areaCode")); //잘 안나옴
				//System.out.println(item.get("sigungucode"));
				//System.out.println("대표 이미지 : "+item.get("firstimage"));
				System.out.println("x좌표 : "+item.get("mapx"));
				System.out.println("y좌표 : "+item.get("mapy"));
				System.out.println("음식Id : "+item.get("contentid"));
				System.out.println("이름 : "+item.get("title"));
				
				String StringType = item.get("contentid").toString().replaceAll("\"", "");
				System.out.println("형변환 결과 : "+StringType);
				//int intType = Integer.parseInt(StringType);
				contentId.add(StringType);
				System.out.println("반복횟수("+count+") , "+contentId.size());
				m.addAttribute("contentId", contentId);
				count++;
				System.out.println("===============================================================");
			}
//			ModelAndView mav = new ModelAndView("food/foodList");
//			return mav;
			return "food/foodList";
			
						
			

			//여기서부터 상세내용에 대한 api주소 요청
//			StringBuilder commoncontent = new StringBuilder();
//
//			String urlStr2 = "http://apis.data.go.kr/B551011/KorService1/detailCommon1?"
//					+ URLEncoder.encode("serviceKey", "UTF-8") + "="
//					+ URLEncoder.encode(
//							"0906O7Vl32hAkLceKylOGOAzJuIESMtXTXESfLV++obF/XFUtduY0IZn4KnJnwSMB3L5HTj7oRuH8PqFhVAQ6w==",
//							"UTF-8")
//					+ "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&"
//					+ URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("12337", "UTF-8") + "&"
//					+ URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8") + "&"
//					+ URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8") + "&"
//					+ URLEncoder.encode("contentId", "UTF-8") + "="
//					+ URLEncoder.encode(temp.get("contentid").getAsString(), "UTF-8") + "&"
//					+ URLEncoder.encode("defaultYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8") + "&"
//					+ URLEncoder.encode("addrinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8") + "&"
//					+ URLEncoder.encode("firstImageYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8") + "&"
//					+ URLEncoder.encode("areacodeYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8") + "&"
//					+ URLEncoder.encode("mapinfoYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8") + "&"
//					+ URLEncoder.encode("overviewYN", "UTF-8") + "=" + URLEncoder.encode("Y", "UTF-8") + "&"
//					+ URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8") + "&"
//					+ URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
//			URL url2 = new URL(urlStr2);
//
//			HttpURLConnection urlConnection2 = (HttpURLConnection) url2.openConnection();
//			urlConnection2.setRequestMethod("GET");
//
//			BufferedReader br2 = new BufferedReader(
//					new InputStreamReader(urlConnection2.getInputStream(), Charset.forName("UTF-8")));
//
//			String returnLine2;
//
//			while ((returnLine2 = br2.readLine()) != null) {
//				commoncontent.append(returnLine2 + "\n\r");
//			}
//			urlConnection2.disconnect();
//			System.out.println(commoncontent.toString());

//			파싱할 객체 생성
//				JsonObject obj2 = JsonParser.parseString(commoncontent.toString()).getAsJsonObject();
//				JsonArray arr2 = obj2.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//						.getAsJsonObject().get("item").getAsJsonArray();
//				for (JsonElement jsonElement2 : arr2) {
//					JsonObject temp2 = jsonElement2.getAsJsonObject();

//						System.out.println("=============================");
//						System.out.println("관광지ID : " + temp2.get("contentid"));
//						System.out.println("관광지명 : " + temp2.get("title"));
//						System.out.println("관광지주소 : " + temp2.get("addr1"));
//						System.out.println("전화번호 : " + temp2.get("tel"));
//						System.out.println("상세내용 : " + temp2.get("overview"));
//						System.out.println("대표이미지 : " + temp2.get("firstimage"));
//						System.out.println("홈페이지주소 : " + temp2.get("hmpg"));
//						System.out.println("위도 : " + temp2.get("mapy"));
//						System.out.println("경도 : " + temp2.get("mapx"));
//						System.out.println("맵레벨 : " + temp2.get("mlevel"));
//						System.out.println("지역코드ID : " + temp2.get("areacode"));

//					여기서 부터는 관광지에 대한 상세정보드들을 불러올 api 주소
//					StringBuilder details = new StringBuilder();
//
//					String urlStr3 = "http://apis.data.go.kr/B551011/KorService1/detailIntro1?"
//							+ URLEncoder.encode("serviceKey", "UTF-8") + "="
//							+ URLEncoder.encode(
//									"0906O7Vl32hAkLceKylOGOAzJuIESMtXTXESfLV++obF/XFUtduY0IZn4KnJnwSMB3L5HTj7oRuH8PqFhVAQ6w==",
//									"UTF-8")
//							+ "&" + URLEncoder.encode("pageNo", "UTF-8") + "=" + URLEncoder.encode("1", "UTF-8") + "&"
//							+ URLEncoder.encode("numOfRows", "UTF-8") + "=" + URLEncoder.encode("12337", "UTF-8") + "&"
//							+ URLEncoder.encode("MobileOS", "UTF-8") + "=" + URLEncoder.encode("ETC", "UTF-8") + "&"
//							+ URLEncoder.encode("MobileApp", "UTF-8") + "=" + URLEncoder.encode("AppTest", "UTF-8")
//							+ "&" + URLEncoder.encode("contentId", "UTF-8") + "="
//							+ URLEncoder.encode(temp2.get("contentid").getAsString(), "UTF-8") + "&"
//							+ URLEncoder.encode("contentTypeId", "UTF-8") + "=" + URLEncoder.encode("12", "UTF-8") + "&"
//							+ URLEncoder.encode("_type", "UTF-8") + "=" + URLEncoder.encode("json", "UTF-8");
//					URL url3 = new URL(urlStr3);
//
//					HttpURLConnection urlConnection3 = (HttpURLConnection) url3.openConnection();
//					urlConnection3.setRequestMethod("GET");
//
//					BufferedReader br3 = new BufferedReader(
//							new InputStreamReader(urlConnection3.getInputStream(), Charset.forName("UTF-8")));
//
//					String returnLine3;
//
//					while ((returnLine3 = br3.readLine()) != null) {
//						details.append(returnLine3 + "\n\r");
//					}
//					urlConnection3.disconnect();
//				 	여기서부터 파싱
//					파싱할 객체 생성
//					JsonObject obj3 = JsonParser.parseString(details.toString()).getAsJsonObject();
//					JsonArray arr3 = obj3.get("response").getAsJsonObject().get("body").getAsJsonObject().get("items")
//							.getAsJsonObject().get("item").getAsJsonArray();
//					for (JsonElement jsonElement3 : arr3) {
//						JsonObject temp3 = jsonElement3.getAsJsonObject();
//							System.out.println("체험안내 : " + temp3.get("expguide"));
//							System.out.println("문의및안내 : " + temp3.get("infocenter"));
//							System.out.println("이용시기 : " + temp3.get("useseason"));
//							System.out.println("이용시간 : " + temp3.get("usetime"));
//							System.out.println("주차시설 : " + temp3.get("parking"));
//							System.out.println("개장일 : " + temp3.get("opendate"));
//							System.out.println("쉬는날 : " + temp3.get("restdate"));
						
//						Touris touris = Touris.builder()
//				                .tourisId(Integer.parseInt((temp2.get("contentid").getAsString())))
//				                .tourisName(temp2.get("title").getAsString())
//				                .tourisAddress(temp2.get("addr1")==null?"-":temp2.get("addr1").getAsString())
//				                .tourisContent(temp2.get("overview")==null?"-":temp2.get("overview").getAsString())
//				                .tourislatitude(temp2.get("mapy")==null?"-":temp2.get("mapy").getAsString())
//				                .tourisLongitude(temp2.get("mapx")==null?"-":temp2.get("mapx").getAsString())
//				                .tourisLevel(temp2.get("mlevel")==null?"-":temp2.get("mlevel").getAsString())
//				                .tourismainImge(temp2.get("firstimage").getAsString())
//				                .tourispage(temp2.get("hmpg")==null?"-":temp2.get("hmpg").getAsString())
//				                .tourisPhone(temp2.get("tel")==null?"-":temp2.get("tel").getAsString())
//				                .tourisExperience(temp3.get("expguide")==null?"-":temp3.get("expguide").getAsString())
//				                .tourisTel(temp3.get("infocenter")==null?"-":temp3.get("infocenter").getAsString())
//				                .tourisUsemonth(temp3.get("useseason")==null?"-":temp3.get("useseason").getAsString())
//				                .tourisUsetime(temp3.get("usetime")==null?"-":temp3.get("usetime").getAsString())
//				                .tourisParking(temp3.get("parking")==null?"-":temp3.get("parking").getAsString())
//				                .tourisStartday(temp3.get("opendate")==null?"-":temp3.get("opendate").getAsString())
//				                .tourisDayoff(temp3.get("restdate")==null?"-":temp3.get("restdate").getAsString())
//				                .areaId(Integer.parseInt((temp2.get("areacode").getAsString())))
//				                .build();
//			            tourisMapper.insertTouris(touris);						
					//}
				//}
			//}
		//return ResponseEntity.status(HttpStatus.OK).body("Data saved successfully.");
	}
	
}
