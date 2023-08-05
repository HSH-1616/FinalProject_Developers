package com.dev.food.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.json.simple.parser.JSONParser;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/food")
public class FoodController {

//	private FoodService service;
//	
//	public FoodController(FoodService service) {
//		this.service = service;
//	}
	
	@GetMapping("/foodList.do")
	public String foodList() {
		return "/food/foodList";
	}
	
//	@GetMapping("/foodTest.do")
//	public ResponseEntity<String> foodTest() throws IOException {
//		
//		StringBuffer result = new StringBuffer();
//		try{
//			URL url = new URL("http://apis.data.go.kr/B551011/KorService1");
//			HttpURLConnection urlConnection = (HttpURLConnection)url.openConnection();
//			urlConnection.setRequestMethod("GET");
//			urlConnection.setRequestProperty("Content-type", "application/json");
//			
//			BufferedReader bf = new BufferedReader(new InputStreamReader(url.openStream(),"UTF-8"));
//			result.append(bf.readLine());
//			
//			JSONParser jsonParser = new JSONParser();
//			JSONObject jsonObject = (JSONObject) jsonParser.parse(result.toString());
//			JSONArray jsonArray = (JSONArray) jsonObject.get("data");
//			for(int i=0; i<jsonArray.size(); i++){
//				JSONObject object = (JSONObject) jsonArray.get(i);
//				String longitude = (String) object.get("경도");
//				String latitude = (String) object.get("위도");
//				SaveAreaRequestDto saveAreaRequestDto = new SaveAreaRequestDto (longitude, latitude);
//				smokeAreaService.save(saveAreaRequestDto);
//			}
//			// return ApiResonse
//			return ApiResponse.success( code: null, httpStatus: null, message: null, response: null);
//		}catch(Exception e) {
//			// exceptionHandler -> ApiResonse error
//			return ApiResponse.error(code: null, httpStatus: null, message: null);
//		}
		
//		String link = "https://apis.data.go.kr/B551011/KorService1/areaBasedList1";
//        int numOfRows = 10;
//        int pageNo = 1;
//		String MobileOS = "ETC";
//        String MobileApp = "Test";
//        String _type = "json";
//        String listYN = "Y";
//        String arrange = "A";
//        String keyword = "고든램지버거";
//        int contentTypeId =39;
//        String serviceKey = "Qpncm3m%2Fbx1Ph6PQUHC4FT6%2BcaFJ1mEGs4R7vrqWvCOMp2lZBfGp2zHQ5A%2BWvuLj8R6IRfwTw43LBM%2F1FWGojA%3D%3D";
//
//        String url = link + "?" +
//        		"&numOfRows=" + numOfRows +
//        		"&pageNo=" + pageNo +
//                "&MobileOS=" + MobileOS +
//                "&MobileApp=" + MobileApp +
//                "&_type=" + _type +
//                "&listYN=" + listYN +
//                "&=arrange" + arrange +
//                "&=keyword" + keyword +
//                "&contentTypeId=" + contentTypeId +
//                "&serviceKey=" + serviceKey;
//
//        URI uri = new URI(url);
//        RestTemplate restTemplate = new RestTemplate();
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
//
//        String response = restTemplate.getForObject(
//                uri,
//                String.class
//        );
//
//        Map<String, Object> map = new ObjectMapper().readValue(response.toString(), Map.class);
//        Map<String, Object> responseMap = (Map<String, Object>) map.get("response");
//        Map<String, Object> bodyMap = (Map<String, Object>) responseMap.get("body");
//        Map<String, Object> itemsMap = (Map<String, Object>) bodyMap.get("items");
//        List<Map<String, Object>> itemMap = (List<Map<String, Object>>) itemsMap.get("item");

        //state에 있는 정보만 들고오기
//        List<Map<String, Object>> testItemMap = itemMap.stream()
//                .filter(item -> {
//                    Object value = item.get("addr1");
//                    return value != null && value.toString().contains(state);
//                })
//                .collect(Collectors.toList());
//
//
//        return ResponseEntity.status(HttpStatus.OK).body("Data saved successfully.");
//	}
}
