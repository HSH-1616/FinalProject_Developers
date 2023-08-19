package com.dev.ac.service;

import java.util.List;
import java.util.Map;

import com.dev.ac.dto.AcFacilities;
import com.dev.ac.dto.AcFile;
import com.dev.ac.dto.AcHeart;
import com.dev.ac.dto.AcPay;
import com.dev.ac.dto.AcPayList;
import com.dev.ac.dto.AcReservation;
import com.dev.ac.dto.AcReview;
import com.dev.ac.dto.Accommodation;
import com.dev.ac.dto.AfaList;

public interface AcService {

	List<Accommodation> acListAll();

	List<Accommodation> searchListAll(Map param);

	Accommodation acDetail(int no);

	List<AcHeart> acHeart(int no);

	Accommodation acPay(int no);

	AcPay checkPayRefund(int no);

	int insertPay(AcPay ap, String checkIn, String checkOut);

	int deletePay(int apId);

	List<Accommodation> acRefundTest();

	int insertHeart(Map param);

	int deleteHeart(Map param);

	List<Accommodation> selectAcAll(Map param);

	int selectAcAllCount();

	Map insertAc(Accommodation ac);

	List<AcFile> deleteImage(int acId);

	List<AfaList> deleteAfalImage(int acId);

	int deleteAc(int acId);

	Accommodation updateRegist(int acId);

	List<AcReservation> updateRegistArv(int acId);

	AcFacilities updateRegistAfa(int acId);

	List<AcFile> updateRegistAf(int acId);

	List<AfaList> updateRegistAfal(int afaId);

	List<AcFile> updateRegistCheckAf(int acId);

	int updateRegistDelAf(int acId);

	List<AfaList> updateRegistCheckAfal(int afaId);

	int updateRegistDelAfal(int afaId);

	int updateAc(Accommodation ac);

	List<AcPayList> acMyPage(String memberId);

	AcPayList acRefundApply(String orderId);

	int updateRefund(Map param);

	int insertReview(AcReview ar);

	List<AcReview> acReview(int no);

	List<AcReview> checkReview(String memberId);

	List<AcPayList> paymentList(Map param);

	int paymentListCount();

	List<AcPayList> refundList(Map param);
	
	List<AcPayList> refundOkList(Map param);

	int refundListCount();

	int refundListCount2();

	int rejectRefund(Map param);
	
	int updateRefundAdmin(Map param);
	
	List<Accommodation> mypageAcHeart(String memberId);
}
