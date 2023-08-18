package com.dev.ac.pay.controller;

import java.io.IOException;
import java.math.BigDecimal;
import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.dev.ac.dto.AcPay;
import com.dev.ac.pay.dto.KaKaoPayApproval;
import com.dev.ac.pay.dto.KaKaoPayReady;
import com.dev.ac.pay.dto.KaKaoRefund;
import com.dev.ac.pay.service.AcPayServiceImpl;
import com.dev.ac.service.AcService;
import com.dev.member.model.dto.Member;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.request.CancelData;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

@RequestMapping("/pay")
@Controller
public class AcPayController {

	private AcService service;
	private AcPayServiceImpl serviceImpl;
	private HttpSession session;
	private IamportClient api;

	public AcPayController(AcService service, AcPayServiceImpl serviceImpl, HttpSession session) {
		this.service = service;
		this.serviceImpl = serviceImpl;
		this.session = session;
		this.api = new IamportClient("1316386156744513",
				"qRK7TjTXM7d8eMSMJ6VHCRFhBOUnfmE5zLYZiuunvsaIT9qODtziOmeJvq5bPcLjRWdv7I7g02kG4HG1");
	}

	@PostMapping("/kakaoPay")
	@ResponseBody
	public KaKaoPayReady kakaoPayReady(@RequestBody Map<String, String> param) {
		/*
		 * Member member = (Member) session.getAttribute("loginMember"); String memberId
		 * = String.valueOf(member.getMemberId()); param.put("memberId", memberId);
		 */
		System.out.println(param);
		return serviceImpl.kakaoPayReady(param);
	}
	
	@GetMapping("/kakaoPayOk")
	public String kakaoPayApporval(AcPay ap,@RequestParam("pg_token") String pgToken,
			@RequestParam("checkIn") String checkIn, @RequestParam("checkOut") String checkOut
			) {
		Member member = (Member) session.getAttribute("loginMember");
		String memberId = String.valueOf(member.getMemberId());
		KaKaoPayApproval KaKaoPayApproval = serviceImpl.kakaoPayApproval(pgToken, memberId, ap.getApOrderId());

		if (KaKaoPayApproval != null) {
//			ap.setAcId(acId);
//			ap.setMemberId(memberId);
//			ap.setApPrice(acPrice);
//			ap.setApPeople(people);
//			ap.setApOrderId(orderId);
			ap.setApKeyId(KaKaoPayApproval.getTid());

			service.insertPay(ap,checkIn,checkOut);

			return "accommodation/acPayResult";

		} else {
			return "";
		}
	}
	
	@GetMapping("/kakaoPayCancel")
	public String kakaoPayCancel() {
		return "/accommodation/acError";
	}
	
	@GetMapping("/kakaoPayFail")
	public String kakaoPayFail() {
		return "/accommodation/acError";
	}

	@PostMapping("/kakaoRefund")
	@ResponseBody
	public KaKaoRefund kakaoRefund(String refundPrice, int apId) {

		AcPay ap = service.checkPayRefund(apId);
		String keyId = ap.getApKeyId();
		KaKaoRefund kakaoRefund = serviceImpl.kakaoRefund(keyId, refundPrice);
		if (kakaoRefund != null) {
			int result = service.deletePay(apId);
		}
		return kakaoRefund;
	}
	
	@PostMapping("/kakaoRefundAdmin")
	@ResponseBody
	public KaKaoRefund kakaoRefundAdmin(String refundPrice, int apId) {

		AcPay ap = service.checkPayRefund(apId);
		String keyId = ap.getApKeyId();
		KaKaoRefund kakaoRefund = serviceImpl.kakaoRefund(keyId, refundPrice);
		if (kakaoRefund != null) {
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("apId",apId);
			param.put("refundPrice",refundPrice);
			
			int result = service.updateRefundAdmin(param);
		}
		return kakaoRefund;
	}
	
	

	public IamportResponse<Payment> checkIamport(String impUid) throws IamportResponseException, IOException {
		return api.paymentByImpUid(impUid);
	}

	@ResponseBody
	@PostMapping("/cardPay")
	public IamportResponse<Payment> verifyIamport(@RequestBody Map param) throws IamportResponseException, IOException {

		String impUid = (String) param.get("impUid");// 실제 결제금액 조회위한 아임포트 서버쪽에서 id
		// 아임포트 서버쪽에 결제된 정보 조회.
		IamportResponse<Payment> irsp = checkIamport(impUid);

		if (irsp != null) {
			AcPay ap=new AcPay();
			ap.setAcId((int) param.get("acId"));
			ap.setMemberId((String) param.get("memberId"));
			ap.setApPrice((int) param.get("apPrice"));
			ap.setApPeople((int) param.get("apPeople"));
			ap.setApOrderId((String) param.get("apOrderId"));
			ap.setApKeyId(impUid);
			String checkIn=(String)param.get("checkIn");
			String checkOut=(String)param.get("checkOut");
//			param.put("keyId", impUid);
			service.insertPay(ap,checkIn,checkOut);
		}

		return irsp;
	}

	@ResponseBody
	@PostMapping("refundIamport")
	public IamportResponse<Payment> refundIamport(String refundPrice, int apId)
			throws IamportResponseException, IOException {

		AcPay ap = service.checkPayRefund(apId);
		String keyId = ap.getApKeyId();
		System.out.println(1);
		CancelData data = new CancelData(keyId, true);
		data.setChecksum(new BigDecimal(refundPrice));
		IamportResponse<Payment> cancel = api.cancelPaymentByImpUid(data);// 취소
		if (cancel != null) {
			System.out.println(2);
			int result = service.deletePay(apId);
			if (result > 0) {
				System.out.println(3);
				return cancel;
			}
		}
		return cancel;
	}
	
	@ResponseBody
	@PostMapping("refundIamportAdmin")
	public IamportResponse<Payment> refundIamportAdmin(String refundPrice, int apId)
			throws IamportResponseException, IOException {

		AcPay ap = service.checkPayRefund(apId);
		String keyId = ap.getApKeyId();
		System.out.println(1);
		CancelData data = new CancelData(keyId, true);
		data.setChecksum(new BigDecimal(refundPrice));
		IamportResponse<Payment> cancel = api.cancelPaymentByImpUid(data);// 취소
		if (cancel != null) {
			System.out.println(2);
			Map<String,Object> param=new HashMap<String,Object>();
			param.put("apId",apId);
			param.put("refundPrice",refundPrice);
			
			int result = service.updateRefundAdmin(param);
			if (result > 0) {
				System.out.println(3);
				return cancel;
			}
		}
		return cancel;
	}

}
