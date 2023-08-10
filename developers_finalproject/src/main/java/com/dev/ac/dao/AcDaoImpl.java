package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.ac.dto.AcFacilities;
import com.dev.ac.dto.AcFile;
import com.dev.ac.dto.AcHeart;
import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.AcReservation;
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;

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
	public List<AcHeart> acHeart(SqlSessionTemplate session, int no) {
		return session.selectList("accommodation.acHeart",no);
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
	public int insertAcFile(SqlSessionTemplate session, AcFile af) {
		return session.insert("accommodation.insertAcFile",af);
	}

	@Override
	public int insertAfa(SqlSessionTemplate session, AcFacilities afa) {
		return session.insert("accommodation.insertAfa",afa);
	}

	@Override
	public int insertAfal(SqlSessionTemplate session, AfaList afal) {
		return session.insert("accommodation.insertAfal",afal);
	}

	@Override
	public int insertArv(SqlSessionTemplate session, AcReservation arv) {
		return session.insert("accommodation.insertArv",arv);
	}

	@Override
	public List<AcFile>  deleteImage(SqlSessionTemplate session, int acId) {
		return session.selectList("accommodation.deleteImage",acId);
	}

	@Override
	public AcFacilities deleteAfa(SqlSessionTemplate session, int acId) {
		return session.selectOne("accommodation.deleteAfa",acId);
	}
	
	@Override
	public List<AfaList> deleteAfalImage(SqlSessionTemplate session, int afaId) {
		return session.selectList("accommodation.deleteAfalImage",afaId);
	}

	@Override
	public int deleteAc(SqlSessionTemplate session, int acId) {
		return session.delete("accommodation.deleteAc",acId);
	}

	@Override
	public Accommodation updateRegist(SqlSessionTemplate session, int acId) {
		return session.selectOne("accommodation.updateRegist",acId);
	}

	@Override
	public List<AcReservation> updateRegistArv(SqlSessionTemplate session, int acId) {
		return session.selectList("accommodation.updateRegistArv",acId);
	}

	@Override
	public List<AcFile> updateRegistCheckAf(SqlSessionTemplate session, int acId) {
		return session.selectList("accommodation.updateRegistCheckAf",acId);
	}

	@Override
	public int updateRegistDelAf(SqlSessionTemplate session, int acId) {
		return session.delete("accommodation.updateRegistDelAf",acId);
	}

	@Override
	public List<AfaList> updateRegistCheckAfal(SqlSessionTemplate session, int afaId) {
		return session.selectList("accommodation.updateRegistCheckAfal",afaId);
	}

	@Override
	public int updateRegistDelAfal(SqlSessionTemplate session, int afaId) {
		return session.delete("accommodation.updateRegistDelAfal",afaId);
	}
}
