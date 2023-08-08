package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.Accommodation;

@Repository
public class AcDaoImpl implements AcDao {

	@Override
	public List<Accommodation> acListAll(SqlSessionTemplate session) {
		return session.selectList("accommodation.acListAll");
	}

	@Override
	public List<Accommodation> acSearchAll(SqlSessionTemplate session, Map param) {
		return session.selectList("accommodation.searchListAll",param);
	}

	@Override
	public Accommodation acDetail(SqlSessionTemplate session, int no) {
		return session.selectOne("accommodation.acDetail",no);
	}

	@Override
	public Accommodation acPay(SqlSessionTemplate session, int no) {
		return session.selectOne("accommodation.acPay",no);
	}
	
	@Override
	public AcPay checkPayRefund(SqlSessionTemplate session, int no) {
		return session.selectOne("accommodation.checkPayRefund",no);
	}
	
	@Override
	public int insertPay(SqlSessionTemplate session, AcPay ap) {
		return session.insert("accommodation.insertPay",ap);
	}
	
	@Override
	public int insertReservation(SqlSessionTemplate session, Map param) {
		return session.insert("accommodation.insertReservation",param);
	}

	@Override
	public int deletePay(SqlSessionTemplate session, int acId) {
		return session.delete("accommodation.deletePay",acId);
	}
	
	@Override
	public List<Accommodation> acRefundTest(SqlSessionTemplate session) {
		return session.selectList("accommodation.acRefundTest");
	}

	@Override
	public int insertHeart(SqlSessionTemplate session,Map param) {
		return session.insert("accommodation.insertHeart",param);
	}
	
	@Override
	public int deleteHeart(SqlSessionTemplate session,Map param) {
		return session.delete("accommodation.deleteHeart",param);
	}

	@Override
	public int insertAc(SqlSessionTemplate session, Accommodation ac) {
		return session.insert("accommodation.insertAc",ac);
	}

	@Override
	public int insertAcFile(SqlSessionTemplate session, Accommodation ac) {
		System.out.println(ac);
		return session.insert("accommodation.insertAcFile",ac);
	}

	

	

	

}
