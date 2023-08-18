package com.dev.ac.pay.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.dev.ac.pay.dto.KaKaoPayApproval;
import com.dev.ac.pay.dto.KaKaoPayReady;
import com.dev.ac.pay.dto.KaKaoRefund;

import lombok.extern.java.Log;

@Service
@Log
public class AcPayServiceImpl {

	static final String cid = "TC0ONETIME";
	static final String admin_Key = "19c261fb3c167b0763a82b7daa9e2dba";
	private static final String HOST = "https://kapi.kakao.com";
	@Autowired
	HttpServletRequest request;
	private KaKaoPayReady KaKaoPayReady;
	private KaKaoPayApproval KaKaoPayApproval;
	private KaKaoRefund KaKaoRefund;

	public KaKaoPayReady kakaoPayReady(Map<String, String> param) {
		String path=request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+request.getContextPath();
		// 카카오페이 요청 양식
		System.out.println(path);
		// 주문번호 생성
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		Calendar c = Calendar.getInstance();
		
		String rdm = String.valueOf(Math.round((Math.random() * 89999999) + 10000000));
		String orderId = "K" + sdf.format(c.getTime()) + rdm;
		//변수
		String memberId=param.get("memberId");
		String acTitle=param.get("acTitle");
		String apPrice=param.get("apPrice");
		String acId=param.get("acId");
		String checkIn=param.get("checkIn");
		String checkOut=param.get("checkOut");
		String people=param.get("apPeople");

		MultiValueMap<String, String> params = new LinkedMultiValueMap<String, String>();
		params.add("cid", cid);
		params.add("partner_order_id", orderId);
		params.add("partner_user_id", memberId);
		params.add("item_name", acTitle);
		params.add("quantity", "1");
		params.add("total_amount", apPrice);
		params.add("tax_free_amount", "0");
		params.add("approval_url", path+"/pay/kakaoPayOk?acId=" + acId + "&checkIn=" + checkIn+ "&memberId=" + memberId
				+ "&checkOut=" + checkOut + "&apPeople=" + people + "&apPrice=" + apPrice + "&apOrderId=" + orderId);
		params.add("cancel_url", path+"/pay/kakaoPayCancel");
		params.add("fail_url", path+"/pay/kakaoPayFail");

		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		RestTemplate restTemplate = new RestTemplate();

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
	public KaKaoPayApproval kakaoPayApproval(String pgToken, String memberId, String orderId) {
		// 카카오 요청
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", cid);
		params.add("tid", KaKaoPayReady.getTid());
		params.add("partner_order_id", orderId);
		params.add("partner_user_id", memberId);
		params.add("pg_token", pgToken);
		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		RestTemplate restTemplate = new RestTemplate();

		try {
			KaKaoPayApproval = restTemplate.postForObject(new URI(HOST + "/v1/payment/approve"), body,
					KaKaoPayApproval.class);
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

	public KaKaoRefund kakaoRefund(String keyId, String refundPrice) {

		// 카카오페이 요청
		MultiValueMap<String, String> params = new LinkedMultiValueMap<>();
		params.add("cid", cid);
		params.add("tid", keyId);
		params.add("cancel_amount", refundPrice);
		params.add("cancel_tax_free_amount", "0");
		params.add("cancel_vat_amount", "0");

		// 파라미터, 헤더
		HttpEntity<MultiValueMap<String, String>> body = new HttpEntity<>(params, this.getHeaders());

		RestTemplate restTemplate = new RestTemplate();

		try {
			KaKaoRefund = restTemplate.postForObject(new URI(HOST + "/v1/payment/cancel"), body, KaKaoRefund.class);
		} catch (RestClientException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block 
			e.printStackTrace();
		}
		return KaKaoRefund;

	}

	private HttpHeaders getHeaders() {
		HttpHeaders httpHeaders = new HttpHeaders();
		httpHeaders.set("Authorization", "KakaoAK " + admin_Key);
		httpHeaders.set("Content-type", "application/x-www-form-urlencoded;charset=utf-8");
		return httpHeaders;
	}

}
