package com.dev.ac.service;

import java.util.List;
import java.util.Map;

import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.Accommodation;

public interface AcService {
	
	List<Accommodation> acListAll();
	
	List<Accommodation> searchListAll(Map param);
	
	Accommodation acDetail(int no);
	
	Accommodation acPay(int no);
	
	AcPay checkPayRefund(int no);
	
	int insertPay(AcPay ap,String checkIn, String checkOut);
	
	int deletePay(int apId);
	
	List<Accommodation> acRefundTest();
	
	int insertHeart(Map param);
	
	int deleteHeart(Map param);
}
