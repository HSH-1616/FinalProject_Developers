package com.dev.ac.pay.service;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dev.ac.pay.dto.KaKaoPayApproval;
import com.dev.ac.pay.dto.KaKaoPayReady;

import lombok.extern.java.Log;

@Service
@Log
public class AcPayService {

	static final String cid = "TC0ONETIME"; 
	static final String admin_Key = "19c261fb3c167b0763a82b7daa9e2dba"; 
	private static final String HOST = "https://kapi.kakao.com";
	
	private KaKaoPayReady KaKaoPayReady;
	private KaKaoPayApproval KaKaoPayApproval;
	
	  public KaKaoPayReady kakaoPayReady(String acId,String memberId, String acTitle, String acPrice) {
		  
		  	RestTemplate restTemplate = new RestTemplate();
		  	
	        // 서버로 요청할 Header
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", "KakaoAK " + admin_Key);
	        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
	        headers.add("Content-Type", MediaType.APPLICATION_FORM_URLENCODED_VALUE + ";charset=UTF-8");
	         // 카카오페이 요청 양식
	   
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
	        params.add("cid", "TC0ONETIME");
	        params.add("partner_order_id", "1001");
	        params.add("partner_user_id", memberId);
	        params.add("item_name", acTitle);
	        params.add("quantity", "1");
	        params.add("total_amount", acPrice);
	        params.add("tax_free_amount", "0");
	        params.add("approval_url", "http://localhost:8888/pay/kakaoPayOk");
	        params.add("cancel_url", "http://localhost:8888/kakaoPayNo");
	        params.add("fail_url", "http://localhost:8888/kakaoPayFail");
	        
	        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params,this.getHeaders());
	        
	        try {
	        	KaKaoPayReady = restTemplate.postForObject(new URI(HOST + "/v1/payment/ready"), body, KaKaoPayReady.class);
	       
	            return KaKaoPayReady;
	 
	        } catch (RestClientException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        
	        return KaKaoPayReady;
	    }
	  
	// 결제 승인요청 메서드
	  public KaKaoPayApproval KaKaoPayApproval(String pgToken,String memberId) {	  	
	        // 카카오 요청
	        MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
	        params.add("cid", cid);
	        params.add("tid", KaKaoPayReady.getTid());
	        params.add("partner_order_id", "1001");
	        params.add("partner_user_id", memberId);
	        params.add("pg_token", pgToken);

	        // 파라미터, 헤더
	        HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());
	        // 외부에 보낼 url
	        RestTemplate restTemplate = new RestTemplate();
	        
	        try {
				KaKaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body, KaKaoPayApproval.class);
				 return KaKaoPayApproval;
	        } catch (RestClientException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        } catch (URISyntaxException e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	                
	        return KaKaoPayApproval;
	    }
	  
	  private HttpHeaders getHeaders() {
	        HttpHeaders httpHeaders = new HttpHeaders();

	        String auth = "KakaoAK " + admin_Key;

	        httpHeaders.set("Authorization", auth);
	        httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");

	        return httpHeaders;
	    }

}
