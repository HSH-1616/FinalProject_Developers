package com.dev.touris.model.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.Charset;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class PublicData {

	@GetMapping("/api")
	public String callApi() throws IOException {
		StringBuilder result = new StringBuilder();
//		String servicekey= "0906O7Vl32hAkLceKylOGOAzJuIESMtXTXESfLV++obF/XFUtduY0IZn4KnJnwSMB3L5HTj7oRuH8PqFhVAQ6w==";
//		String decodeservicekey = servicekey.removingPercentEncoding;
		
		String urlStr = "http://apis.data.go.kr/B551011/KorService1/areaBasedSyncList1?"+
		"serviceKey=0906O7Vl32hAkLceKylOGOAzJuIESMtXTXESfLV++obF/XFUtduY0IZn4KnJnwSMB3L5HTj7oRuH8PqFhVAQ6w=="+
		"pageNo=1"+
		"numOfRows=100"+
		"&MobileOS=ETC"+
		"&MobileApp=AppTest"+
		"&type=json";
		URL url = new URL(urlStr);
		
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		urlConnection.setRequestMethod("GET");
		
		BufferedReader br = new BufferedReader(new InputStreamReader(urlConnection.getInputStream(), Charset.forName("UTF-8")));
		
		String returnLine;
		
		while((returnLine = br.readLine()) != null) {
			result.append(returnLine+"\n\r");
		}
		urlConnection.disconnect();
		System.out.println(result.toString());
//		model.addAttribute("result", result.toString());
		
		return result.toString();
	}
	    
	
}
