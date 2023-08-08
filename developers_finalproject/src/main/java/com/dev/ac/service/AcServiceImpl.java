package com.dev.ac.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.ac.dao.AcDao;
import com.dev.ac.dto.AcFile;
import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.Accommodation;

@Service
public class AcServiceImpl implements AcService {
	
	@Autowired
	private AcDao dao;
	
	@Autowired
	private SqlSessionTemplate session;
	
	@Override
	public List<Accommodation> acListAll() {
		return dao.acListAll(session);
	}

	@Override
	public List<Accommodation> searchListAll(Map param) {
		return dao.acSearchAll(session,param);
	}

	@Override
	public Accommodation acDetail(int no) {
		return dao.acDetail(session,no);
	}

	@Override
	public Accommodation acPay(int no) {
		return dao.acPay(session,no);
	}
	
	@Override
	public AcPay checkPayRefund(int no) {
		return dao.checkPayRefund(session,no);
	}
	
	@Override
	public int insertPay(AcPay ap, String checkIn, String checkOut) {
		int result=dao.insertPay(session,ap);
		Map param = new HashMap();
		if(result>0) {
			param.put("acId",ap.getAcId());
			param.put("apId",ap.getApId());
			param.put("checkIn",checkIn);
			param.put("checkOut",checkOut);
			System.out.println(param);
			result+=dao.insertReservation(session, param);
		}

		return result;
	}
	
	@Override
	public int deletePay(int apId) {
		return dao.deletePay(session,apId);
	}
	
	@Override
	public List<Accommodation> acRefundTest() {
		return dao.acRefundTest(session);
	}

	@Override
	public int insertHeart(Map param) {	
		return dao.insertHeart(session,param);
	}
	
	@Override
	public int deleteHeart(Map param) {	
		return dao.deleteHeart(session,param);
	}

	@Override
	public int insertAc(Accommodation ac) {
		int result=dao.insertAc(session,ac);
		if(result>0) {
			for(int i=0;i<ac.getAcFiles().size();i++){
				AcFile no=AcFile.builder().acId(ac.getAcId()).build();
				System.out.println(ac);
				ac.getAcFiles().add(no);
			}
			result+=dao.insertAcFile(session,ac);
		}
		return 0;
	}
}
