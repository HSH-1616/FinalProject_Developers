package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.Accommodation;

public interface AcDao {
	
	List<Accommodation> acListAll(SqlSessionTemplate session);
	
	List<Accommodation> acSearchAll(SqlSessionTemplate session,Map param);
	
	Accommodation acDetail(SqlSessionTemplate session, int no);
	
	Accommodation acPay(SqlSessionTemplate session, int no);
	
	AcPay checkPayRefund(SqlSessionTemplate session, int no);
	
	int insertPay(SqlSessionTemplate session, AcPay ap);
	
	int insertReservation(SqlSessionTemplate session, Map param);
	
	int deletePay(SqlSessionTemplate session,int acId);
	
	List<Accommodation> acRefundTest(SqlSessionTemplate session);
	
	int insertHeart(SqlSessionTemplate session,Map param);
	
	int deleteHeart(SqlSessionTemplate session,Map param);
	
	int insertAc(SqlSessionTemplate session,Accommodation ac);
	
	int insertAcFile(SqlSessionTemplate session,Accommodation ac);
}
