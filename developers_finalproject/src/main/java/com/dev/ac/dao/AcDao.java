package com.dev.ac.dao;

import java.util.List;
import java.util.Map;

import org.mybatis.spring.SqlSessionTemplate;

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

public interface AcDao {

	List<Accommodation> acListAll(SqlSessionTemplate session);

	List<Accommodation> acSearchAll(SqlSessionTemplate session, Map param);

	Accommodation acDetail(SqlSessionTemplate session, int no);

	List<AcHeart> acHeart(SqlSessionTemplate session, int no);

	Accommodation acPay(SqlSessionTemplate session, int no);

	AcPay checkPayRefund(SqlSessionTemplate session, int no);

	int insertPay(SqlSessionTemplate session, AcPay ap);

	int insertReservation(SqlSessionTemplate session, Map param);

	int deletePay(SqlSessionTemplate session, int acId);

	List<Accommodation> acRefundTest(SqlSessionTemplate session);

	int insertHeart(SqlSessionTemplate session, Map param);

	int deleteHeart(SqlSessionTemplate session, Map param);

	List<Accommodation> selectAcAll(SqlSessionTemplate session, Map param);

	int selectAcAllCount(SqlSessionTemplate session);

	int insertAc(SqlSessionTemplate session, Accommodation ac);

	int insertAcFile(SqlSessionTemplate session, AcFile af);

	int insertAfa(SqlSessionTemplate session, AcFacilities afa);

	int insertAfal(SqlSessionTemplate session, AfaList afal);

	int insertArv(SqlSessionTemplate session, AcReservation arv);

	List<AcFile> deleteImage(SqlSessionTemplate session, int acId);

	AcFacilities deleteAfa(SqlSessionTemplate session, int acId);

	List<AfaList> deleteAfalImage(SqlSessionTemplate session, int afaId);

	int deleteAc(SqlSessionTemplate session, int acId);

	Accommodation updateRegist(SqlSessionTemplate session, int acId);

	List<AcReservation> updateRegistArv(SqlSessionTemplate session, int acId);

	AcFacilities updateRegistAfa(SqlSessionTemplate session, int acId);

	List<AcFile> updateRegistAf(SqlSessionTemplate session, int acId);

	List<AfaList> updateRegistAfal(SqlSessionTemplate session, int afaId);

	List<AcFile> updateRegistCheckAf(SqlSessionTemplate session, int acId);

	int updateRegistDelAf(SqlSessionTemplate session, int acId);

	List<AfaList> updateRegistCheckAfal(SqlSessionTemplate session, int afaId);

	int updateRegistDelAfal(SqlSessionTemplate session, int afaId);

	int updateAc(SqlSessionTemplate session, Accommodation ac);

	int updateInAcFile(SqlSessionTemplate session, AcFile af);

	int updateAfa(SqlSessionTemplate session, AcFacilities afa);

	int updateInAfal(SqlSessionTemplate session, AfaList afal);

	int updateDelArv(SqlSessionTemplate session, int acId);

	int updateInArv(SqlSessionTemplate session, AcReservation arv);

	List<AcPayList> acMyPage(SqlSessionTemplate session, String memberId);

	AcPayList acRefundApply(SqlSessionTemplate session, String orderId);

	int updateRefund(SqlSessionTemplate session, Map param);

	int insertRefund(SqlSessionTemplate session, Map param);

	int insertReview(SqlSessionTemplate session, AcReview ar);

	int insertArf(SqlSessionTemplate session, ArFile arf);

	List<AcReview> acReview(SqlSessionTemplate session, int no);

	List<ArFile> acArf(SqlSessionTemplate session, int arId);

	List<AcReview> checkReview(SqlSessionTemplate session, String memberId);

	List<AcPayList> paymentList(SqlSessionTemplate session, Map param);

	int paymentListCount(SqlSessionTemplate session);

	List<AcPayList> refundList(SqlSessionTemplate session, Map param);

	List<AcPayList> refundOkList(SqlSessionTemplate session, Map param);

	int refundListCount(SqlSessionTemplate session);

	int refundListCount2(SqlSessionTemplate session);

	int rejectRefund(SqlSessionTemplate session, Map param);

	int rejectComment(SqlSessionTemplate session, Map param);

	int updateRefundAdmin(SqlSessionTemplate session, Map param);

	int insertRefundAdmin(SqlSessionTemplate session, Map param);

	List<Accommodation> mypageAcHeart(SqlSessionTemplate session, String memberId);

	List<Accommodation> mypageAcReview(SqlSessionTemplate session, int memberId, Map params);

	int mypageAcReviewCount(SqlSessionTemplate session, int memberId);

	List<Accommodation> searchAc(SqlSessionTemplate session, Map param);

	int searchAcCount(SqlSessionTemplate session, Map param);

	List<AcPayList> paymentSearchList(SqlSessionTemplate session, Map param);

	int paymentSearchListCount(SqlSessionTemplate session,Map param);

	List<AcPayList> refundSearchList(SqlSessionTemplate session, Map param);

	List<AcPayList> refundOkSearchList(SqlSessionTemplate session, Map param);

	int refundSearchListCount(SqlSessionTemplate session,Map param);

	int refundSearchListCount2(SqlSessionTemplate session,Map param);
}
