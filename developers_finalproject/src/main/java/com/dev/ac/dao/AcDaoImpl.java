package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.stereotype.Repository;

import com.dev.ac.dto.AcFacilities;
import com.dev.ac.dto.AcFile;
import com.dev.ac.dto.AcHeart;
import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.AcPayList;
import com.dev.ac.dto.AcReservation;
import com.dev.ac.dto.AcReview;
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;
import com.dev.ac.dto.ArFile;

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
		return session.update("accommodation.deletePay",acId);
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
	public List<Accommodation> selectAcAll(SqlSessionTemplate session,Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("accommodation.selectAcAll",null,rb);
	}

	@Override
	public int selectAcAllCount(SqlSessionTemplate session) {
		return session.selectOne("accommodation.selectAcAllCount");
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
	public AcFacilities updateRegistAfa(SqlSessionTemplate session, int acId) {
		return session.selectOne("accommodation.updateRegistAfa",acId);
	}
	
	@Override
	public List<AcFile> updateRegistAf(SqlSessionTemplate session, int acId) {
		return session.selectList("accommodation.updateRegistAf",acId);
	}
	
	@Override
	public List<AfaList> updateRegistAfal(SqlSessionTemplate session, int afaId) {
		return session.selectList("accommodation.updateRegistAfal",afaId);
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

	@Override
	public int updateAc(SqlSessionTemplate session, Accommodation ac) {
		return session.update("accommodation.updateAc",ac);
	}

	@Override
	public int updateInAcFile(SqlSessionTemplate session, AcFile af) {
		return session.insert("accommodation.updateInAcFile",af);
	}

	@Override
	public int updateAfa(SqlSessionTemplate session, AcFacilities afa) {
		return session.update("accommodation.updateAfa",afa);
	}

	@Override
	public int updateInAfal(SqlSessionTemplate session, AfaList afal) {
		return session.insert("accommodation.updateInAfal",afal);
	}

	@Override
	public int updateDelArv(SqlSessionTemplate session, int acId) {
		return session.delete("accommodation.updateDelArv",acId);
	}

	@Override
	public int updateInArv(SqlSessionTemplate session, AcReservation arv) {
		return session.insert("accommodation.updateInArv",arv);
	}

	@Override
	public List<AcPayList> acMyPage(SqlSessionTemplate session, String memberId) {
		return session.selectList("accommodation.acMyPage",memberId);
	}

	@Override
	public AcPayList acRefundApply(SqlSessionTemplate session, String orderId) {
		return session.selectOne("accommodation.acRefundApply",orderId);
	}
	
	@Override
	public int updateRefund(SqlSessionTemplate session, Map param) {
		return session.update("accommodation.updateRefund",param);
	}
	
	@Override
	public int insertRefund(SqlSessionTemplate session, Map param) {
		return session.insert("accommodation.insertRefund",param);
	}

	@Override
	public int insertReview(SqlSessionTemplate session, AcReview ar) {
		return session.insert("accommodation.insertReview",ar);
	}

	@Override
	public int insertArf(SqlSessionTemplate session, ArFile arf) {
		return session.insert("accommodation.insertArf",arf);
	}

	@Override
	public List<AcReview> acReview(SqlSessionTemplate session, int no) {
		return session.selectList("accommodation.acReview",no);
	}

	@Override
	public List<ArFile> acArf(SqlSessionTemplate session, int arId) {
		return session.selectList("accommodation.acArf",arId);
	}

	@Override
	public List<AcReview> checkReview(SqlSessionTemplate session, String memberId) {
		return session.selectList("accommodation.checkReview",memberId);
	}

	@Override
	public List<AcPayList> paymentList(SqlSessionTemplate session, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("accommodation.paymentList",null,rb);
	}

	@Override
	public int paymentListCount(SqlSessionTemplate session) {
		return session.selectOne("accommodation.paymentListCount");
	}
	
	@Override
	public List<AcPayList> refundList(SqlSessionTemplate session, Map param) {
		int cPage=(int)param.get("cPage");
		int numPerpage=(int)param.get("numPerpage");
		RowBounds rb=new RowBounds((cPage-1)*numPerpage,numPerpage);
		return session.selectList("accommodation.refundList",null,rb);
	}

	@Override
	public int refundListCount(SqlSessionTemplate session) {
		return session.selectOne("accommodation.refundListCount");
	}
	
	@Override
	public int refundListCount2(SqlSessionTemplate session) {
		return session.selectOne("accommodation.refundListCount2");
	}

	@Override
	public int rejectRefund(SqlSessionTemplate session, Map param) {
		return session.update("accommodation.rejectRefund",param);
	}

	@Override
	public int rejectComment(SqlSessionTemplate session, Map param) {
		return session.update("accommodation.rejectComment",param);
	}

	@Override
	public int updateRefundAdmin(SqlSessionTemplate session, Map param) {
		return session.update("accommodation.updateRefundAdmin",param);
	}

	@Override
	public int insertRefundAdmin(SqlSessionTemplate session, Map param) {
		return session.insert("accommodation.insertRefundAdmin",param);
	}
	
}
